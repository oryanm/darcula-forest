package darculaforest

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sin

// CSS declaration-value grammar, mirrored as a sealed hierarchy.
// Every palette value is an Expr:
//   Lit     — number literal (0.55, 0.110, 128)
//   Ident   — channel passthrough marker (l, c, h inside relative oklch)
//   Var     — var(--name); the bound value is itself an Expr
//   Calc    — calc(lhs op rhs)
//   Oklch   — oklch(l c h [/ a]) or oklch(from var(--x) l c h [/ a])

sealed class Expr {
    data class Lit(val value: Double) : Expr()

    data object Ident : Expr()

    data class Calc(val lhs: Expr, val op: Op, val rhs: Expr) : Expr()

    data class Var(val name: String, val value: Expr) : Expr() {
        constructor(name: String, value: Double) : this(name, Lit(value))

        /** Numeric resolution — only valid for scalar Vars (Lit / scalar-Var / Calc of those). */
        val resolved: Double get() = eval(value)

        private fun eval(e: Expr): Double = when (e) {
            is Lit   -> e.value
            is Var   -> eval(e.value)
            is Calc  -> {
                val l = eval(e.lhs)
                val r = eval(e.rhs)
                if (e.op == Op.Plus) l + r else l - r
            }
            is Ident -> error("Ident (channel passthrough) not allowed in a Var value")
            is Oklch -> error("cannot resolve color Var '$name' to a scalar")
        }
    }

    data class Oklch(val from: Var?, val l: Expr, val c: Expr, val h: Expr, val alpha: Expr? = null) : Expr()

    enum class Op(val sym: String) { Plus("+"), Minus("-") }

    operator fun plus(d: Double): Expr = addLit(d)
    operator fun minus(d: Double): Expr = addLit(-d)

    operator fun plus(v: Var): Expr {
        requireAddable()
        require(v.value !is Oklch) { "cannot add color Var '${v.name}' as operand" }
        return Calc(this, Op.Plus, v)
    }

    operator fun minus(v: Var): Expr {
        requireAddable()
        require(v.value !is Oklch) { "cannot subtract color Var '${v.name}' as operand" }
        return Calc(this, Op.Minus, v)
    }

    private fun requireAddable() {
        require(this is Ident || this is Calc || (this is Var && value !is Oklch)) {
            "cannot add/subtract from $this"
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

// ── oklch() factories ─────────────────────────────────────────────

fun oklch(l: Double, c: Double, h: Double, alpha: Double? = null): Expr.Oklch =
    Expr.Oklch(null, Expr.Lit(l), Expr.Lit(c), Expr.Lit(h), alpha?.let(Expr::Lit))

fun oklch(l: Double, c: Double, h: Expr, alpha: Double? = null): Expr.Oklch =
    Expr.Oklch(null, Expr.Lit(l), Expr.Lit(c), h, alpha?.let(Expr::Lit))

fun oklch(l: Double, c: Expr, h: Double, alpha: Double? = null): Expr.Oklch =
    Expr.Oklch(null, Expr.Lit(l), c, Expr.Lit(h), alpha?.let(Expr::Lit))

fun oklch(l: Double, c: Expr, h: Expr, alpha: Double? = null): Expr.Oklch =
    Expr.Oklch(null, Expr.Lit(l), c, h, alpha?.let(Expr::Lit))

fun oklch(from: Expr.Var, l: Expr, c: Expr, h: Expr, alpha: Double? = null): Expr.Oklch =
    Expr.Oklch(from, l, c, h, alpha?.let(Expr::Lit))

// ── Number formatting ─────────────────────────────────────────────

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

// Top-level literal formatting: lightness as %, hue as int, chroma with 3 decimals.
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
    is Expr.Oklch -> error("Oklch not allowed in channel expression")
}

private fun fmtChannel(expr: Expr, channel: Char): String = when (expr) {
    is Expr.Lit   -> fmtTopLit(expr.value, channel)
    is Expr.Ident -> channel.toString()
    is Expr.Var   -> "var(--${expr.name})"
    is Expr.Calc  -> "calc(${fmtInside(expr, channel)})"
    is Expr.Oklch -> error("Oklch not allowed in channel expression")
}

fun fmtL(expr: Expr): String = fmtChannel(expr, 'l')
fun fmtC(expr: Expr): String = fmtChannel(expr, 'c')
fun fmtH(expr: Expr): String = fmtChannel(expr, 'h')

private fun fmtAlpha(expr: Expr): String = when (expr) {
    is Expr.Lit   -> fmtNum(expr.value, 3)
    is Expr.Var   -> "var(--${expr.name})"
    is Expr.Calc  -> "calc(${cssOf(expr)})"
    is Expr.Ident -> error("Ident not allowed in alpha")
    is Expr.Oklch -> error("Oklch not allowed in alpha")
}

// ── CSS output for top-level var declarations ─────────────────────

/** Formats the value side of `--name: <value>;`. Dispatches on Expr type. */
fun cssOf(expr: Expr): String = when (expr) {
    is Expr.Lit   -> fmtNum(expr.value, 3)
    is Expr.Var   -> "var(--${expr.name})"
    is Expr.Calc  -> "calc(${cssOf(expr.lhs)} ${expr.op.sym} ${cssOf(expr.rhs)})"
    is Expr.Oklch -> {
        val head = expr.from?.let { "from var(--${expr.from.name}) " } ?: ""
        val body = "${fmtL(expr.l)} ${fmtC(expr.c)} ${fmtH(expr.h)}"
        val tail = expr.alpha?.let { " / ${fmtAlpha(it)}" } ?: ""
        "oklch($head$body$tail)"
    }
    is Expr.Ident -> error("Ident not allowed at top level")
}

// ── Hex computation (oklch → sRGB) ────────────────────────────────

fun hexOf(oklch: Expr.Oklch): String {
    val resolved = resolveOklch(oklch)
    val hRad = resolved.h * PI / 180.0
    val a = resolved.c * cos(hRad)
    val b = resolved.c * sin(hRad)

    val lLin = resolved.l + 0.3963377774 * a + 0.2158037573 * b
    val mLin = resolved.l - 0.1055613458 * a - 0.0638541728 * b
    val sLin = resolved.l - 0.0894841775 * a - 1.291485548 * b

    val lmsL = lLin * lLin * lLin
    val lmsM = mLin * mLin * mLin
    val lmsS = sLin * sLin * sLin

    val lr = +4.0767416621 * lmsL - 3.3077115913 * lmsM + 0.2309699292 * lmsS
    val lg = -1.2684380046 * lmsL + 2.6097574011 * lmsM - 0.3413193965 * lmsS
    val lb = -0.0041960863 * lmsL - 0.7034186147 * lmsM + 1.7076147010 * lmsS

    val rgb = "%02x%02x%02x".format(srgb(lr), srgb(lg), srgb(lb))
    val alpha = resolved.alpha?.let { "%02x".format((it.coerceIn(0.0, 1.0) * 255).roundToInt()) } ?: ""

    return "$rgb$alpha"
}

private data class ResolvedOklch(val l: Double, val c: Double, val h: Double, val alpha: Double?)

private fun resolveOklch(color: Expr.Oklch): ResolvedOklch {
    val parent = color.from?.let { resolveOklch(unwrapOklch(it)) }
    val alpha = color.alpha?.let { resolveChannel(it, parent?.alpha) } ?: parent?.alpha

    return ResolvedOklch(
        resolveChannel(color.l, parent?.l),
        resolveChannel(color.c, parent?.c),
        resolveChannel(color.h, parent?.h),
        alpha,
    )
}

private fun unwrapOklch(v: Expr.Var): Expr.Oklch = when (val x = v.value) {
    is Expr.Oklch -> x
    is Expr.Var   -> unwrapOklch(x)
    else          -> error("Var '${v.name}' does not wrap an Oklch")
}

private fun resolveChannel(expr: Expr, parent: Double?): Double = when (expr) {
    is Expr.Lit   -> expr.value
    is Expr.Ident -> parent ?: error("Ident requires parent channel")
    is Expr.Var   -> expr.resolved
    is Expr.Calc  -> {
        val lhs = resolveChannel(expr.lhs, parent)
        val rhs = resolveChannel(expr.rhs, parent)
        if (expr.op == Expr.Op.Plus) lhs + rhs else lhs - rhs
    }
    is Expr.Oklch -> error("Oklch not allowed in channel expression")
}

private fun srgb(channel: Double): Int {
    val v = channel.coerceIn(0.0, 1.0)
    val s = if (v <= 0.0031308) 12.92 * v else 1.055 * v.pow(1.0 / 2.4) - 0.055
    return (s * 255).roundToInt()
}
