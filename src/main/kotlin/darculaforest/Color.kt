package darculaforest

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sin

class Color(
    val from: Color?,
    val lExpr: Expr,
    val cExpr: Expr,
    val hExpr: Expr,
) {
    val l: Double = resolve(lExpr, from?.l)
    val c: Double = resolve(cExpr, from?.c)
    val h: Double = resolve(hExpr, from?.h)

    private fun resolve(expr: Expr, parent: Double?): Double = when (expr) {
        is Expr.Lit   -> expr.value
        is Expr.Ident -> parent ?: error("Ident requires a parent color")
        is Expr.Var   -> expr.resolved
        is Expr.Calc  -> {
            val lhs = resolve(expr.lhs, parent)
            val rhs = resolve(expr.rhs, parent)
            if (expr.op == Expr.Op.Plus) lhs + rhs else lhs - rhs
        }
    }

    /** 6-digit hex (no #) */
    fun toHex(): String {
        val hRad = h * PI / 180.0
        val a = c * cos(hRad)
        val b = c * sin(hRad)

        val lLin = l + 0.3963377774 * a + 0.2158037573 * b
        val mLin = l - 0.1055613458 * a - 0.0638541728 * b
        val sLin = l - 0.0894841775 * a - 1.291485548 * b

        val lmsL = lLin * lLin * lLin
        val lmsM = mLin * mLin * mLin
        val lmsS = sLin * sLin * sLin

        val lr = +4.0767416621 * lmsL - 3.3077115913 * lmsM + 0.2309699292 * lmsS
        val lg = -1.2684380046 * lmsL + 2.6097574011 * lmsM - 0.3413193965 * lmsS
        val lb = -0.0041960863 * lmsL - 0.7034186147 * lmsM + 1.7076147010 * lmsS

        return "%02x%02x%02x".format(srgb(lr), srgb(lg), srgb(lb))
    }

    val hexCss: String get() = "#${toHex()}"

    /**
     * CSS value — structural serialization of expressions.
     * Base:    oklch(55% 0.110 var(--main-hue))
     * Derived: oklch(from var(--keyword) calc(l + 0.15) c h)
     */
    fun toCss(nameOf: (Color) -> String?): String {
        if (from != null) {
            val parentName = nameOf(from) ?: return absoluteCss()
            return "oklch(from var(--$parentName) ${fmtL(lExpr)} ${fmtC(cExpr)} ${fmtH(hExpr)})"
        }
        return "oklch(${fmtL(lExpr)} ${fmtC(cExpr)} ${fmtH(hExpr)})"
    }

    /** Fallback: absolute CSS using resolved values */
    private fun absoluteCss(): String {
        val lPct = "${(l * 100).roundToInt()}%"
        val cFmt = "%.3f".format(c)
        val hFmt = h.roundToInt().toString()
        return "oklch($lPct $cFmt $hFmt)"
    }

    private companion object {
        fun srgb(channel: Double): Int {
            val v = channel.coerceIn(0.0, 1.0)
            val s = if (v <= 0.0031308) 12.92 * v else 1.055 * v.pow(1.0 / 2.4) - 0.055
            return (s * 255).roundToInt()
        }
    }
}

/** Base color: oklch(L C H) — all literals */
fun oklch(l: Double, c: Double, h: Double): Color =
    Color(null, Expr.Lit(l), Expr.Lit(c), Expr.Lit(h))

/** Base color with hue expression: oklch(L C var(--hue)) */
fun oklch(l: Double, c: Double, h: Expr): Color =
    Color(null, Expr.Lit(l), Expr.Lit(c), h)

/** Derived color: oklch(from <color> L C H) */
fun oklch(from: Color, l: Expr, c: Expr, h: Expr): Color =
    Color(from, l, c, h)
