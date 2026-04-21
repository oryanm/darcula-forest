package darculaforest

sealed interface PaletteEntry
data class Def(val variable: Expr.Var, val comment: String? = null) : PaletteEntry
data class Alias(val name: String, val target: Expr.Var, val comment: String? = null) : PaletteEntry
data class Section(val title: String) : PaletteEntry
data object Blank : PaletteEntry

// ── Hues ────────────────────────────────────────────────────────────

val mainHue                  = Expr.Var("main-hue",      128.0)
val complementaryColorOffset = Expr.Var("comp-offset",   30.0)
val secondaryHue             = Expr.Var("secondary-hue", mainHue - complementaryColorOffset)
val tertiaryHue              = Expr.Var("tertiary-hue",  mainHue + complementaryColorOffset)
val baseChroma               = Expr.Var("base-chroma",   0.110)
val redHue                   = Expr.Var("red-hue",       28.0)
val blueHue                  = Expr.Var("blue-hue",      248.0)

// ── Editor ──────────────────────────────────────────────────────────

val editorBg       = Expr.Var("editor-bg",        oklch(0.25, 0.010, mainHue))
val caretRow       = Expr.Var("caret-row",        oklch(editorBg, l + 0.02, c, h))
val searchResultBg = Expr.Var("search-result-bg", oklch(editorBg, l + 0.1, c, h))
val selectionBg    = Expr.Var("selection-bg",     oklch(searchResultBg, l, c + 0.01, h))
val lineNumber     = Expr.Var("line-number",      oklch(editorBg, l + 0.2, c, h))
val injectedLangBg = Expr.Var("injected-lang-bg", oklch(editorBg, l + 0.01, c, h))
val fg             = Expr.Var("fg",               oklch(0.75, 0.010, mainHue))

// Preview backgrounds (derived from editor)
val pageBg   = Expr.Var("page-bg",    oklch(editorBg, l - 0.05, c, h))
val tabBarBg = Expr.Var("tab-bar-bg", oklch(pageBg,   l + 0.01, c, h))
val gutterBg = Expr.Var("gutter-bg",  oklch(editorBg, l + 0.05, c, h))

// ── Syntax ──────────────────────────────────────────────────────────

val keyword       = Expr.Var("keyword",           oklch(0.55, baseChroma, mainHue))
val functionDecl  = Expr.Var("function-decl",     oklch(keyword, l + 0.15, c, h))
val constantField = Expr.Var("constant-field",    oklch(keyword, l + 0.10, c + 0.04, h))
val implicitParam = Expr.Var("implicit-param",    oklch(functionDecl, l, c + 0.04, h))
val number        = Expr.Var("number",            oklch(keyword, l, c, h + complementaryColorOffset))
val string        = Expr.Var("string",            oklch(keyword, l, c - 0.03, h - complementaryColorOffset))
val stringEscape  = Expr.Var("string-escape",     oklch(string, l, c, h + complementaryColorOffset))
val stringEscBad  = Expr.Var("string-escape-bad", oklch(string, l - 0.15, c, h))
val staticFunc    = Expr.Var("static-function",   oklch(keyword, l - 0.05, c - 0.02, h))
val namedArg      = Expr.Var("named-arg",         oklch(keyword, l + 0.1, c - 0.05, h))

// ── Effects ─────────────────────────────────────────────────────────

val error            = Expr.Var("error",             oklch(keyword, l, c + 0.05, redHue))
val warningBg        = Expr.Var("warning-bg",        oklch(editorBg, l + 0.2, c + 0.025, secondaryHue))
val mutableUnderline = Expr.Var("mutable-underline", oklch(keyword, l - 0.15, c, h))
val typoUnderline    = Expr.Var("typo-underline",    oklch(mutableUnderline, l, c, secondaryHue))

// ── Comments ────────────────────────────────────────────────────────

val comment = Expr.Var("comment", oklch(keyword, l + 0.05, Expr.Lit(0.0), h))
val javadoc = Expr.Var("javadoc", oklch(keyword, l + 0.05, c, h))
val todo    = Expr.Var("todo",    oklch(string, l + 0.20, c + 0.09, h))

// ── Diff ────────────────────────────────────────────────────────────

val diffDelete   = Expr.Var("diff-delete",   oklch(searchResultBg, l, c, h))
val diffChange   = Expr.Var("diff-change",   oklch(diffDelete, l, c + 0.03, blueHue))
val diffAdd      = Expr.Var("diff-add",      oklch(diffDelete, l, c + 0.03, mainHue))
val diffConflict = Expr.Var("diff-conflict", oklch(diffDelete, l, c + 0.03, redHue))

// ── Editor right side stripe ────────────────────────────────────────

val searchResultStripe = Expr.Var("search-result-stripe", oklch(searchResultBg, l, c + 0.1, h))
val todoStripe         = Expr.Var("todo-stripe",          oklch(todo, l, c + 0.1, h))
val warningStripe      = Expr.Var("warning-stripe",       oklch(warningBg, l, c + 0.1, h))
val diffDeleteStripe   = Expr.Var("diff-delete-stripe",   oklch(diffDelete, l + 0.1, c + 0.1, h))
val diffChangeStripe   = Expr.Var("diff-change-stripe",   oklch(diffChange, l + 0.1, c + 0.1, h))
val diffAddStripe      = Expr.Var("diff-add-stripe",      oklch(diffAdd, l + 0.1, c + 0.1, h))
val diffConflictStripe = Expr.Var("diff-conflict-stripe", oklch(diffConflict, l + 0.1, c + 0.1, h))

// ── Terminal (alacritty-only; not in paletteEntries) ────────────────

val termRed         = Expr.Var("term-red",          oklch(string, l, c, h))
val termYellow      = Expr.Var("term-yellow",       oklch(termRed, l, c - 0.03, h))
val termBrightWhite = Expr.Var("term-bright-white", oklch(fg, l, c + 0.05, h))

// ── Palette ─────────────────────────────────────────────────────────
// Ordered list of palette entries. Drives CSS generation and ICLS hex resolution.

val paletteEntries: List<PaletteEntry> = listOf(
    Section("Hues"),
    Def(mainHue),
    Def(complementaryColorOffset),
    Def(secondaryHue),
    Def(tertiaryHue),
    Def(baseChroma),
    Def(redHue),
    Def(blueHue),

    Section("Editor"),
    Def(editorBg),
    Def(caretRow),
    Def(selectionBg),
    Def(searchResultBg),
    Def(searchResultStripe),
    Def(injectedLangBg),
    Def(lineNumber),
    Def(fg),
    Alias("folded-text-bg", searchResultBg),
    Alias("tearline", searchResultBg),
    Alias("template-lang", searchResultBg),

    Section("Preview backgrounds"),
    Def(pageBg),
    Def(tabBarBg),
    Def(gutterBg),
    Alias("fg-muted", namedArg),

    Section("Syntax"),
    Def(keyword),
    Def(functionDecl),
    Def(constantField),
    Def(implicitParam),
    Def(number),
    Def(string),
    Def(stringEscape),
    Def(stringEscBad),
    Def(staticFunc),
    Def(namedArg),
    Blank,
    Alias("class", fg),
    Alias("parameter", fg),
    Alias("function-call", fg),
    Alias("local-var", fg),
    Alias("operator", fg),
    Alias("parens", fg, "brackets also"),
    Alias("punctuation", keyword, "comma, semicolon"),
    Alias("generic-type-param", number),
    Alias("annotation", string),
    Alias("annotation-named-att", namedArg),

    Section("Effects"),
    Def(error),
    Def(warningBg),
    Def(warningStripe),
    Def(mutableUnderline),
    Def(typoUnderline),
    Alias("deprecated-strikethrough", fg),

    Section("Comments"),
    Def(comment),
    Def(javadoc),
    Alias("javadoc-markup", functionDecl),
    Alias("javadoc-tag", javadoc),
    Alias("javadoc-tag-val", string),
    Def(todo),
    Def(todoStripe),

    Section("Diff"),
    Def(diffDelete),
    Def(diffChange),
    Def(diffAdd),
    Def(diffConflict),
    Def(diffDeleteStripe),
    Def(diffChangeStripe),
    Def(diffAddStripe),
    Def(diffConflictStripe),
)

// ── Palette Utilities ───────────────────────────────────────────────

/** Forward lookup: palette name → uppercase hex. Resolves aliases. Only Oklch defs contribute. */
val hexMap: Map<String, String> = buildMap {
    for (entry in paletteEntries) {
        when (entry) {
            is Def -> {
                val value = entry.variable.value
                if (value is Expr.Oklch) put(entry.variable.name, hexOf(value).uppercase())
            }
            is Alias -> get(entry.target.name)?.let { put(entry.name, it) }
            is Section, Blank -> {}
        }
    }
}

/** Resolve a palette key to uppercase hex, or pass through literal hex */
fun resolve(value: String): String = hexMap[value] ?: value.uppercase()
