// palette.main.kts — Canonical color palette for Darcula Forest
//
// Generates:  darcula/palette.css
//             darcula/Darcula_Forest.icls
//             darcula/alacritty.toml
//
// Usage:  kotlin palette.main.kts

import java.io.File
import java.util.IdentityHashMap
import kotlin.math.*

// ── Color Model ─────────────────────────────────────────────────────
//
// Colors mirror CSS oklch() grammar directly. A color has an optional
// `from` source color and three channel expressions, just like:
//
//   oklch(55% 0.110 var(--main-hue))              — base
//   oklch(from var(--keyword) calc(l + 0.15) c h)  — derived

sealed class Expr {
    /** Literal value: 0.55, 0.110, 128 */
    data class Lit(val value: Double) : Expr()

    /** Channel identity — passthrough from parent */
    object Ident : Expr() { override fun toString() = "Ident" }

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

// ── Expression Formatting ───────────────────────────────────────────

fun fmtDelta(value: Double, precision: Int): String {
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

// ── Color ───────────────────────────────────────────────────────────

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
        is Expr.Calc  -> (parent ?: error("Calc requires a parent color")) + expr.delta
        is Expr.Var   -> expr.resolved
    }

    /** 6-digit hex (no #) */
    fun toHex(): String {
        val hRad = h * PI / 180.0
        val a = c * cos(hRad)
        val b = c * sin(hRad)

        val l_ = l + 0.3963377774 * a + 0.2158037573 * b
        val m_ = l - 0.1055613458 * a - 0.0638541728 * b
        val s_ = l - 0.0894841775 * a - 1.291485548 * b

        val lmsL = l_ * l_ * l_
        val lmsM = m_ * m_ * m_
        val lmsS = s_ * s_ * s_

        val lr = +4.0767416621 * lmsL - 3.3077115913 * lmsM + 0.2309699292 * lmsS
        val lg = -1.2684380046 * lmsL + 2.6097574011 * lmsM - 0.3413193965 * lmsS
        val lb = -0.0041960863 * lmsL - 0.7034186147 * lmsM + 1.7076147010 * lmsS

        fun srgb(c: Double): Int {
            val v = c.coerceIn(0.0, 1.0)
            val s = if (v <= 0.0031308) 12.92 * v else 1.055 * v.pow(1.0 / 2.4) - 0.055
            return (s * 255).roundToInt()
        }

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
}

// ── Constructor Functions ───────────────────────────────────────────

/** Base color: oklch(L C H) — all literals */
fun oklch(l: Double, c: Double, h: Double): Color =
    Color(null, Expr.Lit(l), Expr.Lit(c), Expr.Lit(h))

/** Base color with hue expression: oklch(L C var(--hue)) */
fun oklch(l: Double, c: Double, h: Expr): Color =
    Color(null, Expr.Lit(l), Expr.Lit(c), h)

/** Derived color: oklch(from <color> L C H) */
fun oklch(from: Color, l: Expr, c: Expr, h: Expr): Color =
    Color(from, l, c, h)

// ── Palette Entry Types ─────────────────────────────────────────────

sealed interface PaletteEntry
data class Def(val name: String, val color: Color, val comment: String? = null) : PaletteEntry
data class Alias(val name: String, val target: String, val comment: String? = null) : PaletteEntry
data class Section(val title: String) : PaletteEntry
class Blank : PaletteEntry

// ── Hues ────────────────────────────────────────────────────────────

val mainHue      = Expr.Var("main-hue",      128.0)
val secondaryHue = Expr.Var("secondary-hue", mainHue.resolved - 30)
val tertiaryHue  = Expr.Var("tertiary-hue",  mainHue.resolved + 30)
val redHue       = Expr.Var("red-hue",       28.0)
val blueHue      = Expr.Var("blue-hue",      248.0)
val orangeHue    = Expr.Var("orange-hue",    60.0)

// CSS custom property declarations for hues
val hueVarDefs = listOf(
    mainHue      to "${mainHue.resolved.roundToInt()}",
    secondaryHue to "calc(var(--main-hue) - 30)",
    tertiaryHue  to "calc(var(--main-hue) + 30)",
    redHue       to "${redHue.resolved.roundToInt()}",
    blueHue      to "${blueHue.resolved.roundToInt()}",
    orangeHue    to "${orangeHue.resolved.roundToInt()}",
)

// ── Editor ──────────────────────────────────────────────────────────

val editorBg       = oklch(0.25, 0.010, mainHue)
val caretRow       = oklch(0.27, 0.010, mainHue)
val selectionBg    = oklch(0.35, 0.020, mainHue)
val searchResultBg = oklch(0.35, 0.010, mainHue)
val lineNumber     = oklch(0.45, 0.010, mainHue)
val fg             = oklch(0.75, 0.010, mainHue)

// Preview backgrounds (derived from editor)
val pageBg   = oklch(editorBg, l - 0.05, c, h)
val tabBarBg = oklch(pageBg,   l + 0.01, c, h)
val gutterBg = oklch(editorBg, l + 0.05, c, h)

// ── Syntax ──────────────────────────────────────────────────────────

val keyword = oklch(0.55, 0.110, mainHue)
val functionDecl = oklch(keyword, l + 0.15, c, h)
val constantField = oklch(keyword, l + 0.10, c + 0.04, h)
val implicitParam = oklch(functionDecl, l, c + 0.04, h)
val number = oklch(keyword, l, c, h + 30.0)
val string = oklch(keyword, l, c - 0.03, h - 30.0)
val stringEscape = oklch(string, l, c, h + 30.0)
val stringEscBad = oklch(string, l - 0.15, c, h)
val staticFunc = oklch(keyword, l - 0.05, c - 0.02, h)
val namedArg = oklch(constantField, l, c - 0.09, h)

// ── Effects ─────────────────────────────────────────────────────────

val error            = oklch(0.55, 0.170, redHue)
val warning          = oklch(0.45, 0.035, secondaryHue)
val warningStripe    = oklch(0.70, 0.170, secondaryHue)
val mutableUnderline = oklch(0.40, 0.090, mainHue)
val typoUnderline    = oklch(0.40, 0.070, secondaryHue)

// ── Comments ────────────────────────────────────────────────────────

val comment = oklch(0.60, 0.000, 0.0)           // pure gray
val javadoc = oklch(0.60, 0.110, mainHue)
val todo    = oklch(string, l + 0.20, c + 0.09, h)

// ── Diff ────────────────────────────────────────────────────────────

val diffChange   = oklch(0.35, 0.040, blueHue)
val diffAdd      = oklch(0.35, 0.040, mainHue)
val diffConflict = oklch(0.35, 0.040, redHue)

// ── Terminal ────────────────────────────────────────────────────────

val termRed         = oklch(0.55, 0.080, orangeHue)
val termYellow      = oklch(0.55, 0.060, orangeHue)
val termBrightWhite = oklch(fg, l, c + 0.05, h)

// ── Palette ─────────────────────────────────────────────────────────
// Ordered list of palette entries. Drives CSS generation (sections,
// relative color syntax, var aliases) and ICLS hex resolution.

val paletteEntries: List<PaletteEntry> = listOf(
    Section("Editor"),
    Def("editor-bg", editorBg),
    Def("caret-row", caretRow),
    Def("selection-bg", selectionBg),
    Def("search-result-bg", searchResultBg),
    Def("line-number", lineNumber),
    Def("fg", fg),

    Section("Preview backgrounds"),
    Def("page-bg", pageBg),
    Def("tab-bar-bg", tabBarBg),
    Def("gutter-bg", gutterBg),
    Alias("fg-muted", "named-arg"),

    Section("Syntax"),
    Def("keyword", keyword),
    Def("function-decl", functionDecl, "bright keyword"),
    Def("constant-field", constantField, "bright & saturated keyword"),
    Def("implicit-param", implicitParam, "saturated function-decl"),
    Def("number", number, "hue shift of keyword"),
    Def("string", string, "desaturated hue shift of keyword"),
    Def("string-escape", stringEscape, "hue shift of string"),
    Def("string-escape-bad", stringEscBad, "darker string"),
    Def("static-function", staticFunc, "dark & desaturated keyword"),
    Def("named-arg", namedArg, "desaturated constant-field"),
    Blank(),
    Alias("class", "fg"),
    Alias("parameter", "fg"),
    Alias("function-call", "fg"),
    Alias("local-var", "fg"),
    Alias("operator", "fg"),
    Alias("parens", "fg", "brackets also"),
    Alias("punctuation", "keyword", "comma, semicolon"),
    Alias("generic-type-param", "number"),
    Alias("annotation", "string"),
    Alias("annotation-named-att", "named-arg"),

    Section("Effects"),
    Def("error", error),
    Def("warning", warning),
    Def("warning-stripe", warningStripe),
    Def("mutable-underline", mutableUnderline),
    Def("typo-underline", typoUnderline),
    Alias("deprecated-strikethrough", "fg"),

    Section("Comments"),
    Def("comment", comment),
    Def("javadoc", javadoc),
    Alias("javadoc-markup", "function-decl"),
    Alias("javadoc-tag", "javadoc"),
    Alias("javadoc-tag-val", "string"),
    Def("todo", todo, "bright & saturated string"),

    Section("Diff"),
    Alias("diff-delete", "search-result-bg"),
    Def("diff-change", diffChange),
    Def("diff-add", diffAdd),
    Def("diff-conflict", diffConflict),
)

// ── Palette Utilities ───────────────────────────────────────────────

// Reverse lookup: Color object → palette name (first name wins).
// Uses identity comparison so derived colors find their exact parent.
val colorNames: Map<Color, String> = IdentityHashMap<Color, String>().also { map ->
    for (entry in paletteEntries) {
        if (entry is Def) map.putIfAbsent(entry.color, entry.name)
    }
}

fun nameOf(color: Color): String? = colorNames[color]

// Forward lookup: palette name → uppercase hex. Resolves aliases.
val hexMap: Map<String, String> = buildMap {
    for (entry in paletteEntries) {
        if (entry is Def) put(entry.name, entry.color.toHex().uppercase())
    }
    for (entry in paletteEntries) {
        if (entry is Alias) get(entry.target)?.let { put(entry.name, it) }
    }
}

/** Resolve a palette key to uppercase hex, or pass through literal hex */
fun resolve(value: String): String = hexMap[value] ?: value.uppercase()

// ── CSS Generator ───────────────────────────────────────────────────

fun generateCss(): String = buildString {
    appendLine(":root {")

    // Hue variables
    appendLine("    /* Hues */")
    for ((hue, cssValue) in hueVarDefs) {
        appendLine("    --${hue.name}: $cssValue;")
    }

    for (entry in paletteEntries) {
        when (entry) {
            is Section -> {
                appendLine()
                appendLine("    /* ${entry.title} */")
            }
            is Def -> {
                val value = entry.color.toCss(::nameOf)
                val comment = entry.comment?.let { " /* $it */" } ?: ""
                appendLine("    --${entry.name}: $value;$comment")
            }
            is Alias -> {
                val comment = entry.comment?.let { " /* $it */" } ?: ""
                appendLine("    --${entry.name}: var(--${entry.target});$comment")
            }
            is Blank -> appendLine()
        }
    }
    appendLine("}")
}

// ── ICLS Generator ──────────────────────────────────────────────────

data class IclsColor(val name: String, val ref: String? = null, val hex: String? = null)
data class IclsAttr(
    val name: String,
    val fg: String? = null,
    val bg: String? = null,
    val fontType: Int? = null,
    val effectColor: String? = null,
    val effectType: Int? = null,
    val errorStripeColor: String? = null,
    val baseAttributes: String? = null,
)

val iclsColors = listOf(
    IclsColor("CARET_ROW_COLOR", ref = "caret-row"),
    IclsColor("FOLDED_TEXT_BORDER_COLOR", hex = "313335"),
    IclsColor("SELECTION_BACKGROUND", ref = "selection-bg"),
    IclsColor("TEARLINE_COLOR", hex = "3C3F41"),
)

val iclsAttributes = listOf(
    IclsAttr("ANNOTATION_ATTRIBUTE_NAME_ATTRIBUTES", fg = "annotation-named-att"),
    IclsAttr("ANNOTATION_NAME_ATTRIBUTES", fg = "annotation", effectType = 1),
    IclsAttr("DEFAULT_COMMA", fg = "punctuation"),
    IclsAttr("DEFAULT_CONSTANT", fg = "constant-field", fontType = 2),
    IclsAttr("DEFAULT_DOC_COMMENT", fg = "javadoc", fontType = 2),
    IclsAttr("DEFAULT_DOC_COMMENT_TAG", fg = "javadoc-tag", fontType = 3, effectColor = "javadoc-tag", effectType = 1),
    IclsAttr("DEFAULT_DOC_COMMENT_TAG_VALUE", fg = "javadoc-tag-val"),
    IclsAttr("DEFAULT_DOC_MARKUP", fg = "javadoc-markup"),
    IclsAttr("DEFAULT_FUNCTION_DECLARATION", fg = "function-decl"),
    IclsAttr("DEFAULT_IDENTIFIER", fg = "fg", effectType = 5),
    IclsAttr("DEFAULT_INSTANCE_FIELD", fg = "constant-field"),
    IclsAttr("DEFAULT_INVALID_STRING_ESCAPE", fg = "string-escape-bad", effectColor = "FF0000", effectType = 2),
    IclsAttr("DEFAULT_KEYWORD", fg = "keyword"),
    IclsAttr("DEFAULT_NUMBER", fg = "number"),
    IclsAttr("DEFAULT_SEMICOLON", fg = "punctuation"),
    IclsAttr("DEFAULT_STATIC_FIELD", fg = "constant-field", fontType = 2),
    IclsAttr("DEFAULT_STATIC_METHOD", fg = "static-function", fontType = 2),
    IclsAttr("DEFAULT_STRING", fg = "string"),
    IclsAttr("DEFAULT_TEMPLATE_LANGUAGE_COLOR", bg = "3C3F41"),
    IclsAttr("DEFAULT_VALID_STRING_ESCAPE", fg = "string-escape"),
    IclsAttr("DEPRECATED_ATTRIBUTES", effectColor = "fg", effectType = 3),
    IclsAttr("DIFF_CONFLICT", bg = "diff-conflict", errorStripeColor = "diff-conflict"),
    IclsAttr("DIFF_DELETED", bg = "diff-delete", errorStripeColor = "diff-delete"),
    IclsAttr("DIFF_INSERTED", bg = "diff-add", errorStripeColor = "diff-add"),
    IclsAttr("DIFF_MODIFIED", bg = "diff-change", errorStripeColor = "diff-change"),
    IclsAttr("IMPLICIT_ANONYMOUS_CLASS_PARAMETER_ATTRIBUTES", fg = "implicit-param", effectColor = "function-decl", effectType = 1),
    IclsAttr("INJECTED_LANGUAGE_FRAGMENT", bg = "282B27"),
    IclsAttr("KOTLIN_LABEL", fg = "number"),
    IclsAttr("KOTLIN_MUTABLE_VARIABLE", effectColor = "mutable-underline", effectType = 1),
    IclsAttr("KOTLIN_NAMED_ARGUMENT", fg = "named-arg"),
    IclsAttr("KOTLIN_TYPE_PARAMETER", baseAttributes = "TYPE_PARAMETER_NAME_ATTRIBUTES"),
    IclsAttr("SEARCH_RESULT_ATTRIBUTES", bg = "search-result-bg", errorStripeColor = "530D"),
    IclsAttr("TEXT", fg = "fg", bg = "editor-bg", effectType = 5),
    IclsAttr("TODO_DEFAULT_ATTRIBUTES", fg = "todo", fontType = 2, errorStripeColor = "977AB"),
    IclsAttr("TYPE_PARAMETER_NAME_ATTRIBUTES", fg = "generic-type-param"),
    IclsAttr("TYPO", effectColor = "typo-underline", effectType = 2),
    IclsAttr("WARNING_ATTRIBUTES", bg = "warning", errorStripeColor = "BE9117", effectType = 2),
    IclsAttr("WRONG_REFERENCES_ATTRIBUTES", fg = "error"),
)

fun generateIcls(): String = buildString {
    fun opt(name: String, value: String, indent: String) =
        "$indent<option name=\"$name\" value=\"$value\" />"

    appendLine("""<scheme name="Darcula Forest" version="142" parent_scheme="Darcula">""")

    // metaInfo
    appendLine("  <metaInfo>")
    appendLine("    <property name=\"ide\">idea</property>")
    appendLine("    <property name=\"ideVersion\">2025.3.2.0.0</property>")
    appendLine("    <property name=\"originalScheme\">Darcula Forest</property>")
    appendLine("  </metaInfo>")

    // colors
    appendLine("  <colors>")
    for (c in iclsColors) {
        val hex = if (c.ref != null) resolve(c.ref) else c.hex!!.uppercase()
        appendLine(opt(c.name, hex, "    "))
    }
    appendLine("  </colors>")

    // attributes
    appendLine("  <attributes>")
    for (attr in iclsAttributes) {
        if (attr.baseAttributes != null) {
            appendLine("    <option name=\"${attr.name}\" baseAttributes=\"${attr.baseAttributes}\" />")
            continue
        }
        appendLine("    <option name=\"${attr.name}\">")
        appendLine("      <value>")
        attr.fg?.let              { appendLine(opt("FOREGROUND", resolve(it), "        ")) }
        attr.bg?.let              { appendLine(opt("BACKGROUND", resolve(it), "        ")) }
        attr.fontType?.let        { appendLine(opt("FONT_TYPE", it.toString(), "        ")) }
        attr.errorStripeColor?.let { appendLine(opt("ERROR_STRIPE_COLOR", resolve(it), "        ")) }
        attr.effectColor?.let     { appendLine(opt("EFFECT_COLOR", resolve(it), "        ")) }
        attr.effectType?.let      { appendLine(opt("EFFECT_TYPE", it.toString(), "        ")) }
        appendLine("      </value>")
        appendLine("    </option>")
    }
    appendLine("  </attributes>")
    append("</scheme>")
}

// ── Alacritty TOML Generator ────────────────────────────────────────

fun generateAlacritty(): String = buildString {
    fun hex(color: Color) = "\"${color.hexCss}\""

    appendLine("# Darcula Forest — generated from palette.main.kts")
    appendLine("[colors]")
    appendLine("indexed_colors = [{ index = 16, color = ${hex(editorBg)} }]")
    appendLine()
    appendLine("[colors.primary]")
    appendLine("background = ${hex(editorBg)}")
    appendLine("foreground = ${hex(fg)}")
    appendLine()
    appendLine("[colors.normal]")
    appendLine("black   = ${hex(editorBg)}")
    appendLine("red     = ${hex(termRed)}")
    appendLine("green   = ${hex(stringEscape)}")
    appendLine("yellow  = ${hex(termYellow)}")
    appendLine("blue    = ${hex(keyword)}")
    appendLine("magenta = ${hex(functionDecl)}")
    appendLine("cyan    = ${hex(constantField)}")
    appendLine("white   = ${hex(fg)}")
    appendLine()
    appendLine("[colors.bright]")
    appendLine("black   = ${hex(selectionBg)}")
    appendLine("red     = ${hex(termRed)}")
    appendLine("green   = ${hex(stringEscape)}")
    appendLine("yellow  = ${hex(termYellow)}")
    appendLine("blue    = ${hex(keyword)}")
    appendLine("magenta = ${hex(functionDecl)}")
    appendLine("cyan    = ${hex(constantField)}")
    append("white   = ${hex(termBrightWhite)}")
}

// ── Generate ────────────────────────────────────────────────────────

val outDir = File("darcula")

File(outDir, "palette.css").writeText(generateCss())
File(outDir, "Darcula_Forest.icls").writeText(generateIcls() + "\n")
File(outDir, "alacritty.toml").writeText(generateAlacritty() + "\n")

println("Generated:")
println("  darcula/palette.css")
println("  darcula/Darcula_Forest.icls")
println("  darcula/alacritty.toml")
