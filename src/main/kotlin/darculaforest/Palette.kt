package darculaforest

import darculaforest.Expr.Oklch
import darculaforest.Expr.Var

sealed interface PaletteEntry
data class Def(val variable: Var, val comment: String? = null) : PaletteEntry
data class Section(val title: String) : PaletteEntry
data object Blank : PaletteEntry

// ── Hues ────────────────────────────────────────────────────────────

val mainHue                  = Var("main-hue",      128.0)
val complementaryColorOffset = Var("comp-offset",   30.0)
val secondaryHue             = Var("secondary-hue", mainHue - complementaryColorOffset)
val tertiaryHue              = Var("tertiary-hue",  mainHue + complementaryColorOffset)
val baseChroma               = Var("base-chroma",   0.110)
val redHue                   = Var("red-hue",       28.0)
val blueHue                  = Var("blue-hue",      248.0)

// ── Editor ──────────────────────────────────────────────────────────

val editorBg       = Var("editor-bg",        oklch(0.25, 0.010, mainHue))
val caretRow       = Var("caret-row",        oklch(editorBg, l + 0.02, c, h))
val searchResultBg = Var("search-result-bg", oklch(editorBg, l + 0.1, c, h))
val selectionBg    = Var("selection-bg",     oklch(searchResultBg, l, c + 0.01, h))
val lineNumber     = Var("line-number",      oklch(editorBg, l + 0.2, c, h))
val injectedLangBg = Var("injected-lang-bg", oklch(editorBg, l + 0.01, c, h))
val fg             = Var("fg",               oklch(0.75, 0.010, mainHue))

val foldedTextBg = Var("folded-text-bg", searchResultBg)
val tearline     = Var("tearline",       searchResultBg)
val templateLang = Var("template-lang",  searchResultBg)

// ── Syntax ──────────────────────────────────────────────────────────

val keyword       = Var("keyword",           oklch(0.55, baseChroma, mainHue))
val functionDecl  = Var("function-decl",     oklch(keyword, l + 0.15, c, h))
val constantField = Var("constant-field",    oklch(keyword, l + 0.10, c + 0.04, h))
val implicitParam = Var("implicit-param",    oklch(functionDecl, l, c + 0.04, h))
val number        = Var("number",            oklch(keyword, l, c, h + complementaryColorOffset))
val string        = Var("string",            oklch(keyword, l, c - 0.03, h - complementaryColorOffset))
val stringEscape  = Var("string-escape",     oklch(string, l, c, h + complementaryColorOffset))
val stringEscBad  = Var("string-escape-bad", oklch(string, l - 0.15, c, h))
val staticFunc    = Var("static-function",   oklch(keyword, l - 0.05, c - 0.02, h))
val namedArg      = Var("named-arg",         oklch(keyword, l + 0.1, c - 0.05, h))

val `class`            = Var("class",                fg)
val parameter          = Var("parameter",            fg)
val functionCall       = Var("function-call",        fg)
val localVar           = Var("local-var",            fg)
val operator           = Var("operator",             fg)
val parens             = Var("parens",               fg)
val punctuation        = Var("punctuation",          keyword)
val genericTypeParam   = Var("generic-type-param",   number)
val annotation         = Var("annotation",           string)
val annotationNamedAtt = Var("annotation-named-att", namedArg)

// ── Effects ─────────────────────────────────────────────────────────

val error            = Var("error",             oklch(keyword, l, c + 0.05, redHue))
val warningBg        = Var("warning-bg",        oklch(editorBg, l + 0.2, c + 0.025, secondaryHue))
val mutableUnderline = Var("mutable-underline", oklch(keyword, l - 0.15, c, h))
val typoUnderline    = Var("typo-underline",    oklch(mutableUnderline, l, c, secondaryHue))
val deprecatedStrikethrough = Var("deprecated-strikethrough", fg)

// ── Comments ────────────────────────────────────────────────────────

val comment = Var("comment", oklch(keyword, l + 0.05, Expr.Lit(0.0), h))
val javadoc = Var("javadoc", oklch(keyword, l + 0.05, c, h))
val javadocMarkup = Var("javadoc-markup",  functionDecl)
val javadocTag    = Var("javadoc-tag",     javadoc)
val javadocTagVal = Var("javadoc-tag-val", string)
val todo    = Var("todo",    oklch(string, l + 0.20, c + 0.09, h))

// ── Diff ────────────────────────────────────────────────────────────

val diffDelete   = Var("diff-delete",   oklch(searchResultBg, l, c, h))
val diffChange   = Var("diff-change",   oklch(diffDelete, l, c + 0.03, blueHue))
val diffAdd      = Var("diff-add",      oklch(diffDelete, l, c + 0.03, mainHue))
val diffConflict = Var("diff-conflict", oklch(diffDelete, l, c + 0.03, redHue))

// ── Editor right side stripe ────────────────────────────────────────

val searchResultStripe = Var("search-result-stripe", oklch(searchResultBg, l, c + 0.1, h))
val todoStripe         = Var("todo-stripe",          oklch(todo, l, c + 0.1, h))
val warningStripe      = Var("warning-stripe",       oklch(warningBg, l, c + 0.1, h))
val diffDeleteStripe   = Var("diff-delete-stripe",   oklch(diffDelete, l + 0.1, c + 0.1, h))
val diffChangeStripe   = Var("diff-change-stripe",   oklch(diffChange, l + 0.1, c + 0.1, h))
val diffAddStripe      = Var("diff-add-stripe",      oklch(diffAdd, l + 0.1, c + 0.1, h))
val diffConflictStripe = Var("diff-conflict-stripe", oklch(diffConflict, l + 0.1, c + 0.1, h))

// preview.html colors (not part of color-scheme)
val pageBg   = Var("page-bg",    oklch(editorBg, l - 0.05, c, h))
val tabBarBg = Var("tab-bar-bg", oklch(pageBg,   l + 0.01, c, h))
val gutterBg = Var("gutter-bg",  oklch(editorBg, l + 0.05, c, h))
val fgMuted = Var("fg-muted", namedArg)

// ── Terminal (alacritty-only; not in paletteEntries) ────────────────

val termRed         = Var("term-red",          oklch(string, l, c, h))
val termYellow      = Var("term-yellow",       oklch(termRed, l, c - 0.03, h))
val termBrightWhite = Var("term-bright-white", oklch(fg, l, c + 0.05, h))

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
    Def(foldedTextBg),
    Def(tearline),
    Def(templateLang),

    Section("Preview backgrounds"),
    Def(pageBg),
    Def(tabBarBg),
    Def(gutterBg),
    Def(fgMuted),

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
    Def(`class`),
    Def(parameter),
    Def(functionCall),
    Def(localVar),
    Def(operator),
    Def(parens, "brackets also"),
    Def(punctuation, "comma, semicolon"),
    Def(genericTypeParam),
    Def(annotation),
    Def(annotationNamedAtt),

    Section("Effects"),
    Def(error),
    Def(warningBg),
    Def(warningStripe),
    Def(mutableUnderline),
    Def(typoUnderline),
    Def(deprecatedStrikethrough),

    Section("Comments"),
    Def(comment),
    Def(javadoc),
    Def(javadocMarkup),
    Def(javadocTag),
    Def(javadocTagVal),
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

/** Forward lookup: palette name → uppercase hex. Walks Var chains to find the underlying Oklch. */
val hexMap: Map<String, String> = paletteEntries
  .filterIsInstance<Def>()
  .map { Pair(it.variable.name, oklchOf(it.variable.value)) }
  .filterNot { it.second == null }
  .associate { Pair(it.first, hexOf(it.second!!).uppercase()) }

tailrec fun oklchOf(e: Expr): Oklch? = when (e) {
  is Oklch -> e
  is Var -> oklchOf(e.value)
  else -> null
}

/** Resolve a palette key to uppercase hex, or pass through literal hex */
fun resolve(value: String): String = hexMap[value] ?: value.uppercase()
