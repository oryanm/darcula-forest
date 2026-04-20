package darculaforest

import java.util.IdentityHashMap
import kotlin.math.roundToInt

sealed interface PaletteEntry
data class Def(val name: String, val color: Color, val comment: String? = null) : PaletteEntry
data class Alias(val name: String, val target: String, val comment: String? = null) : PaletteEntry
data class Section(val title: String) : PaletteEntry
data object Blank : PaletteEntry

// ── Hues ────────────────────────────────────────────────────────────

val mainHue      = Expr.Var("main-hue",      128.0)
val complementaryColorOffset      = Expr.Var("comp-offset",      30.0)
val secondaryHue = Expr.Var("secondary-hue", mainHue.resolved - complementaryColorOffset.resolved)
val tertiaryHue  = Expr.Var("tertiary-hue",  mainHue.resolved + complementaryColorOffset.resolved)
val redHue       = Expr.Var("red-hue",       28.0)
val blueHue      = Expr.Var("blue-hue",      248.0)

// CSS custom property declarations for hues
val hueVarDefs = listOf(
    mainHue      to "${mainHue.resolved.roundToInt()}",
    complementaryColorOffset      to "${complementaryColorOffset.resolved.roundToInt()}",
    secondaryHue to "calc(var(--main-hue) - var(--comp-offset))",
    tertiaryHue  to "calc(var(--main-hue) + var(--comp-offset))",
    redHue       to "${redHue.resolved.roundToInt()}",
    blueHue      to "${blueHue.resolved.roundToInt()}",
)

// ── Editor ──────────────────────────────────────────────────────────

val editorBg       = oklch(0.25, 0.010, mainHue)
val caretRow       = oklch(0.27, 0.010, mainHue)
val selectionBg    = oklch(0.35, 0.020, mainHue)
val searchResultBg = oklch(0.35, 0.010, mainHue)
val lineNumber     = oklch(0.45, 0.010, mainHue)
val injectedLangBg = oklch(0.26, 0.010, mainHue)
val fg             = oklch(0.75, 0.010, mainHue)

// Preview backgrounds (derived from editor)
val pageBg   = oklch(editorBg, l - 0.05, c, h)
val tabBarBg = oklch(pageBg,   l + 0.01, c, h)
val gutterBg = oklch(editorBg, l + 0.05, c, h)

// ── Syntax ──────────────────────────────────────────────────────────

val keyword       = oklch(0.55, 0.110, mainHue)
val functionDecl  = oklch(keyword, l + 0.15, c, h)
val constantField = oklch(keyword, l + 0.10, c + 0.04, h)
val implicitParam = oklch(functionDecl, l, c + 0.04, h)
val number        = oklch(keyword, l, c, h + complementaryColorOffset)
val string        = oklch(keyword, l, c - 0.03, h - complementaryColorOffset)
val stringEscape  = oklch(string, l, c, h + complementaryColorOffset)
val stringEscBad  = oklch(string, l - 0.15, c, h)
val staticFunc    = oklch(keyword, l - 0.05, c - 0.02, h)
val namedArg      = oklch(constantField, l, c - 0.09, h)

// ── Effects ─────────────────────────────────────────────────────────

val error            = oklch(0.55, 0.170, redHue)
val warning          = oklch(0.45, 0.035, secondaryHue)
val mutableUnderline = oklch(0.40, 0.090, mainHue)
val typoUnderline    = oklch(0.40, 0.070, secondaryHue)

// ── Comments ────────────────────────────────────────────────────────

val comment = oklch(0.60, 0.000, 0.0)           // pure gray
val javadoc = oklch(0.60, 0.110, mainHue)
val todo    = oklch(string, l + 0.20, c + 0.09, h)


// ── Diff ────────────────────────────────────────────────────────────

val diffDelete   = oklch(searchResultBg, l, c, h)
val diffChange   = oklch(0.35, 0.040, blueHue)
val diffAdd      = oklch(0.35, 0.040, mainHue)
val diffConflict = oklch(0.35, 0.040, redHue)

// ── Editor right side stripe ────────────────────────────────────────

val searchResultStripe  = oklch(searchResultBg, l, c + 0.1, h)
val todoStripe          = oklch(todo, l, c + 0.1, h)
val warningStripe       = oklch(warning, l, c + 0.1, h)
val diffDeleteStripe   = oklch(diffDelete, l + 0.1, c + 0.1, h)
val diffChangeStripe   = oklch(diffChange, l + 0.1, c + 0.1, h)
val diffAddStripe      = oklch(diffAdd, l + 0.1, c + 0.1, h)
val diffConflictStripe = oklch(diffConflict, l + 0.1, c + 0.1, h)

// ── Terminal ────────────────────────────────────────────────────────

val termRed         = oklch(string, l, c, h)
val termYellow      = oklch(termRed, l, c - 0.2, h)
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
    Def("search-result-stripe", searchResultStripe),
    Def("injected-lang-bg", injectedLangBg),
    Def("line-number", lineNumber),
    Def("fg", fg),
    Alias("folded-text-bg", "search-result-bg"),
    Alias("tearline", "search-result-bg"),
    Alias("template-lang", "search-result-bg"),

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
    Blank,
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
    Def("todo-stripe", todoStripe),

    Section("Diff"),
    Def("diff-delete", diffDelete),
    Def("diff-change", diffChange),
    Def("diff-add", diffAdd),
    Def("diff-conflict", diffConflict),
    Def("diff-delete-stripe", diffDeleteStripe),
    Def("diff-change-stripe", diffChangeStripe),
    Def("diff-add-stripe", diffAddStripe),
    Def("diff-conflict-stripe", diffConflictStripe),
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
