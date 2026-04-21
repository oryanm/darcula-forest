package darculaforest

import java.util.IdentityHashMap

sealed interface PaletteEntry
data class Def(val name: String, val color: Color, val comment: String? = null) : PaletteEntry
data class Alias(val name: String, val target: String, val comment: String? = null) : PaletteEntry
data class Section(val title: String) : PaletteEntry
data object Blank : PaletteEntry

// ── Hues ────────────────────────────────────────────────────────────

val mainHue                  = Expr.Var("main-hue",     128.0)
val complementaryColorOffset = Expr.Var("comp-offset",  30.0)
val secondaryHue             = Expr.Var("secondary-hue", mainHue - complementaryColorOffset)
val tertiaryHue              = Expr.Var("tertiary-hue",  mainHue + complementaryColorOffset)
val baseChroma               = Expr.Var("base-chroma",  0.110)
val redHue                   = Expr.Var("red-hue",      28.0)
val blueHue                  = Expr.Var("blue-hue",     248.0)

// Ordered list of CSS custom property declarations; CSS is derived structurally from each var's value expr.
val hueVarDefs: List<Expr.Var> = listOf(
    mainHue, complementaryColorOffset, secondaryHue, tertiaryHue,
    baseChroma, redHue, blueHue,
)

// ── Editor ──────────────────────────────────────────────────────────

val editorBg       = oklch(0.25, 0.010, mainHue)
val caretRow       = oklch(editorBg, l + 0.02, c, h)
val searchResultBg = oklch(editorBg, l + 0.1, c, h)
val selectionBg    = oklch(searchResultBg, l, c + 0.01, h)
val lineNumber     = oklch(editorBg, l + 0.2, c, h)
val injectedLangBg = oklch(editorBg, l + 0.01, c, h)
val fg             = oklch(0.75, 0.010, mainHue)

// Preview backgrounds (derived from editor)
val pageBg   = oklch(editorBg, l - 0.05, c, h)
val tabBarBg = oklch(pageBg,   l + 0.01, c, h)
val gutterBg = oklch(editorBg, l + 0.05, c, h)

// ── Syntax ──────────────────────────────────────────────────────────

val keyword       = oklch(0.55, baseChroma, mainHue)
val functionDecl  = oklch(keyword, l + 0.15, c, h)
val constantField = oklch(keyword, l + 0.10, c + 0.04, h)
val implicitParam = oklch(functionDecl, l, c + 0.04, h)
val number        = oklch(keyword, l, c, h + complementaryColorOffset)
val string        = oklch(keyword, l, c - 0.03, h - complementaryColorOffset)
val stringEscape  = oklch(string, l, c, h + complementaryColorOffset)
val stringEscBad  = oklch(string, l - 0.15, c, h)
val staticFunc    = oklch(keyword, l - 0.05, c - 0.02, h)
val namedArg      = oklch(keyword, l + 0.1, c - 0.05, h)

// ── Effects ─────────────────────────────────────────────────────────

val error            = oklch(keyword, l, c + 0.05, redHue)
val warningBg        = oklch(editorBg, l + 0.2, c + 0.025, secondaryHue)
val mutableUnderline = oklch(keyword, l - 0.15, c, h)
val typoUnderline    = oklch(mutableUnderline, l, c, secondaryHue)

// ── Comments ────────────────────────────────────────────────────────

val comment = oklch(keyword, l + 0.05, Expr.Lit(0.0), h)
val javadoc = oklch(keyword, l + 0.05, c, h)
val todo    = oklch(string, l + 0.20, c + 0.09, h)


// ── Diff ────────────────────────────────────────────────────────────

val diffDelete   = oklch(searchResultBg, l, c, h)
val diffChange   = oklch(diffDelete, l, c + 0.03, blueHue)
val diffAdd      = oklch(diffDelete, l, c + 0.03, mainHue)
val diffConflict = oklch(diffDelete, l, c + 0.03, redHue)

// ── Editor right side stripe ────────────────────────────────────────

val searchResultStripe  = oklch(searchResultBg, l, c + 0.1, h)
val todoStripe          = oklch(todo, l, c + 0.1, h)
val warningStripe       = oklch(warningBg, l, c + 0.1, h)
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
    Def("function-decl", functionDecl),
    Def("constant-field", constantField),
    Def("implicit-param", implicitParam),
    Def("number", number),
    Def("string", string),
    Def("string-escape", stringEscape),
    Def("string-escape-bad", stringEscBad),
    Def("static-function", staticFunc),
    Def("named-arg", namedArg),
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
    Def("warning-bg", warningBg),
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
    Def("todo", todo),
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
val colorNames: Map<Color, String> = paletteEntries
    .filterIsInstance<Def>()
    .associate { Pair(it.color, it.name) }

fun nameOf(color: Color): String? = colorNames[color]

// Forward lookup: palette name → uppercase hex. Resolves aliases.
val hexMap: Map<String, String> = buildMap {
    for (entry in paletteEntries) {
        if (entry is Def) put(entry.name, entry.color.toHex().uppercase())
        if (entry is Alias) get(entry.target)?.let { put(entry.name, it) }
    }
}

/** Resolve a palette key to uppercase hex, or pass through literal hex */
fun resolve(value: String): String = hexMap[value] ?: value.uppercase()
