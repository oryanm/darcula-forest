package darculaforest

import kotlin.math.abs
import kotlin.math.roundToInt

// Colors mirror CSS oklch() grammar directly. A color has an optional
// `from` source color and three channel expressions, just like:
//
//   oklch(55% 0.110 var(--main-hue))                      — base
//   oklch(from var(--keyword) calc(l + 0.15) c h)          — derived (literal delta)
//   oklch(from var(--keyword) calc(l + var(--offset)) c h) — derived (var delta)

sealed class Expr {
    /** Literal value: 0.55, 0.110, 128. Always non-negative when used as a Calc operand. */
    data class Lit(val value: Double) : Expr()

    /** Channel identity — passthrough from parent */
    data object Ident : Expr()

    /** calc(lhs op rhs) — recursive; lhs is typically Ident or nested Calc, rhs is Lit or Var */
    data class Calc(val lhs: Expr, val op: Op, val rhs: Expr) : Expr()

    /** var(--name) with pre-resolved value for hex computation */
    data class Var(val name: String, val resolved: Double) : Expr()

    enum class Op(val sym: String) { Plus("+"), Minus("-") }

    operator fun plus(d: Double): Expr = addLit(d)
    operator fun minus(d: Double): Expr = addLit(-d)

    operator fun plus(v: Var): Expr {
        requireAddable()
        return Calc(this, Op.Plus, v)
    }

    operator fun minus(v: Var): Expr {
        requireAddable()
        return Calc(this, Op.Minus, v)
    }

    private fun requireAddable() {
        require(this is Ident || this is Calc) { "can only add/subtract from Ident or Calc, got $this" }
    }

    private fun addLit(d: Double): Expr {
        if (d == 0.0) return this
        return when (this) {
            is Ident -> mkCalc(this, d)
            is Calc  -> foldOrNest(d)
            else     -> error("can only add literal to Ident or Calc, got $this")
        }
    }

    private fun Calc.foldOrNest(d: Double): Expr {
        // If rhs is a Lit, fold the new delta into it so `l + 0.15 + 0.05` collapses to `calc(l + 0.20)`.
        if (rhs is Lit) {
            val signed = (if (op == Op.Plus) rhs.value else -rhs.value) + d
            return when {
                signed == 0.0    -> lhs
                signed > 0       -> Calc(lhs, Op.Plus, Lit(signed))
                else             -> Calc(lhs, Op.Minus, Lit(-signed))
            }
        }
        // Otherwise chain: calc((...) + 0.05)
        return mkCalc(this, d)
    }

    private fun mkCalc(lhs: Expr, signedDelta: Double): Expr =
        if (signedDelta >= 0) Calc(lhs, Op.Plus, Lit(signedDelta))
        else Calc(lhs, Op.Minus, Lit(-signedDelta))
}

// Channel identity markers — used like CSS's l, c, h keywords
val l: Expr get() = Expr.Ident
val c: Expr get() = Expr.Ident
val h: Expr get() = Expr.Ident

private fun fmtNum(value: Double, precision: Int): String {
    val s = "%.${precision}f".format(value)
    return if ('.' in s) s.trimEnd('0').trimEnd('.') else s
}

// Precision per channel for literal deltas inside calc().
private fun calcPrecision(channel: Char): Int = when (channel) {
    'l' -> 2
    'c' -> 3
    'h' -> 0
    else -> error("unknown channel '$channel'")
}

// Top-level literal formatting (no calc wrapper): lightness as %, hue as int, chroma with 3 decimals.
private fun fmtTopLit(v: Double, channel: Char): String = when (channel) {
    'l' -> "${(v * 100).roundToInt()}%"
    'c' -> "%.3f".format(v)
    'h' -> v.roundToInt().toString()
    else -> error("unknown channel '$channel'")
}

private fun fmtInside(expr: Expr, channel: Char): String = when (expr) {
    is Expr.Lit   -> fmtNum(abs(expr.value), calcPrecision(channel))
    is Expr.Ident -> channel.toString()
    is Expr.Var   -> "var(--${expr.name})"
    is Expr.Calc  -> "${fmtInside(expr.lhs, channel)} ${expr.op.sym} ${fmtInside(expr.rhs, channel)}"
}

private fun fmtChannel(expr: Expr, channel: Char): String = when (expr) {
    is Expr.Lit   -> fmtTopLit(expr.value, channel)
    is Expr.Ident -> channel.toString()
    is Expr.Var   -> "var(--${expr.name})"
    is Expr.Calc  -> "calc(${fmtInside(expr, channel)})"
}

fun fmtL(expr: Expr): String = fmtChannel(expr, 'l')
fun fmtC(expr: Expr): String = fmtChannel(expr, 'c')
fun fmtH(expr: Expr): String = fmtChannel(expr, 'h')
