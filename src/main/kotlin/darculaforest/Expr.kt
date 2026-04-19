package darculaforest

import kotlin.math.abs
import kotlin.math.roundToInt

// Colors mirror CSS oklch() grammar directly. A color has an optional
// `from` source color and three channel expressions, just like:
//
//   oklch(55% 0.110 var(--main-hue))              — base
//   oklch(from var(--keyword) calc(l + 0.15) c h)  — derived

sealed class Expr {
    /** Literal value: 0.55, 0.110, 128 */
    data class Lit(val value: Double) : Expr()

    /** Channel identity — passthrough from parent */
    data object Ident : Expr()

    /** calc(ch ± delta) */
    data class Calc(val delta: Double) : Expr()

    /** var(--name) with pre-resolved value for hex computation */
    data class Var(val name: String, val resolved: Double) : Expr()

    operator fun plus(d: Double): Expr = when (this) {
        is Ident -> Calc(d)
        is Calc  -> Calc(delta + d)
        else     -> error("can only add to Ident or Calc, got $this")
    }
    operator fun minus(d: Double): Expr = plus(-d)
}

// Channel identity markers — used like CSS's l, c, h keywords
val l: Expr get() = Expr.Ident
val c: Expr get() = Expr.Ident
val h: Expr get() = Expr.Ident

private fun fmtDelta(value: Double, precision: Int): String {
    val s = "%.${precision}f".format(value)
    return if ('.' in s) s.trimEnd('0').trimEnd('.') else s
}

fun fmtL(expr: Expr): String = when (expr) {
    is Expr.Lit   -> "${(expr.value * 100).roundToInt()}%"
    is Expr.Ident -> "l"
    is Expr.Calc  -> "calc(l ${if (expr.delta >= 0) "+" else "-"} ${fmtDelta(abs(expr.delta), 2)})"
    is Expr.Var   -> "var(--${expr.name})"
}

fun fmtC(expr: Expr): String = when (expr) {
    is Expr.Lit   -> "%.3f".format(expr.value)
    is Expr.Ident -> "c"
    is Expr.Calc  -> "calc(c ${if (expr.delta >= 0) "+" else "-"} ${fmtDelta(abs(expr.delta), 3)})"
    is Expr.Var   -> "var(--${expr.name})"
}

fun fmtH(expr: Expr): String = when (expr) {
    is Expr.Lit   -> expr.value.roundToInt().toString()
    is Expr.Ident -> "h"
    is Expr.Calc  -> "calc(h ${if (expr.delta >= 0) "+" else "-"} ${fmtDelta(abs(expr.delta), 0)})"
    is Expr.Var   -> "var(--${expr.name})"
}
