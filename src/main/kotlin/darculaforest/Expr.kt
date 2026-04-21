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

    /** var(--name) whose value is itself an expression (Lit, Var, or Calc) */
    data class Var(val name: String, val value: Expr) : Expr() {
        constructor(name: String, value: Double) : this(name, Lit(value))

        /** Numeric resolution for hex computation — evaluates the value expression */
        val resolved: Double get() = eval(value)

        private fun eval(e: Expr): Double = when (e) {
            is Lit   -> e.value
            is Var   -> eval(e.value)
            is Calc  -> {
                val l = eval(e.lhs)
                val r = eval(e.rhs)
                if (e.op == Op.Plus) l + r else l - r
            }
            is Ident -> error("Ident (channel passthrough) not allowed inside a Var value")
        }
    }

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
        require(this is Ident || this is Calc || this is Var) {
            "can only add/subtract from Ident, Calc, or Var, got $this"
        }
    }

    private fun addLit(d: Double): Expr {
        requireAddable()
        if (d == 0.0) return this
        return if (d >= 0) Calc(this, Op.Plus, Lit(d))
        else Calc(this, Op.Minus, Lit(-d))
    }
}

// Channel identity markers — used like CSS's l, c, h keywords
val l: Expr = Expr.Ident
val c: Expr = Expr.Ident
val h: Expr = Expr.Ident

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

// Formats a Var's value expression for a top-level declaration (e.g. `--name: <value>;`).
// Nested Calcs emit nested calc() — verbose but always correct; flattening would break
// precedence when a Calc sits on the RHS of a minus.
fun fmtVarValue(expr: Expr): String = when (expr) {
    is Expr.Lit   -> fmtNum(expr.value, 3)
    is Expr.Var   -> "var(--${expr.name})"
    is Expr.Calc  -> "calc(${fmtVarValue(expr.lhs)} ${expr.op.sym} ${fmtVarValue(expr.rhs)})"
    is Expr.Ident -> error("Ident not allowed in Var value")
}
