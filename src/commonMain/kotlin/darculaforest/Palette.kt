package darculaforest

import darculaforest.Expr.Oklch
import darculaforest.Expr.Var

sealed interface PaletteEntry
data class Def(val variable: Var, val comment: String? = null) : PaletteEntry
data class Section(val title: String) : PaletteEntry
data object Blank : PaletteEntry

class Palette(params: ThemeParams = ThemeParams()) {

    // ── Hues ────────────────────────────────────────────────────────────

    val mainHue                  = Var("main-hue",      params.mainHue)
    val complementaryColorOffset = Var("comp-offset",   params.complementaryColorOffset)
    val secondaryHue             = Var("secondary-hue", mainHue - complementaryColorOffset)
    val tertiaryHue              = Var("tertiary-hue",  mainHue + complementaryColorOffset)
    val baseChroma               = Var("base-chroma",   params.baseChroma)
    val redHue                   = Var("red-hue",       28.0)
    val greenHue                 = Var("green-hue",     128.0)
    val blueHue                  = Var("blue-hue",      248.0)

    // ── Editor ──────────────────────────────────────────────────────────

    val editorBg       = Var("editor-bg",        oklch(0.25, 0.010, mainHue))
    val caretRow       = Var("caret-row",        oklch(editorBg, l + 0.02, c, h))
    val docBg          = Var("doc-bg",           caretRow)
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
    val matchingBraceFg =   Var("matching-brace-fg", oklch(implicitParam, l, c, secondaryHue))
    val matchingBraceBg =   Var("matching-brace-bg", oklch(selectionBg, l, c, secondaryHue))
    val unusedElement =     Var("unused-element", oklch(keyword, l, c - 0.1, h))

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
    val diffAdd      = Var("diff-add",      oklch(diffDelete, l, c + 0.03, greenHue))
    val diffConflict = Var("diff-conflict", oklch(diffDelete, l, c + 0.03, redHue))

    // ── Editor right side stripe ────────────────────────────────────────

    val searchResultStripe = Var("search-result-stripe", oklch(searchResultBg, l, c + 0.1, h))
    val todoStripe         = Var("todo-stripe",          oklch(todo, l, c + 0.1, h))
    val warningStripe      = Var("warning-stripe",       oklch(warningBg, l, c + 0.1, h))
    val diffDeleteStripe   = Var("diff-delete-stripe",   oklch(diffDelete, l + 0.3, c, h))
    val diffChangeStripe   = Var("diff-change-stripe",   oklch(diffChange, l + 0.3, c + 0.1, h))
    val diffAddStripe      = Var("diff-add-stripe",      oklch(diffAdd, l + 0.3, c + 0.1, h))
    val diffConflictStripe = Var("diff-conflict-stripe", oklch(diffConflict, l + 0.3, c + 0.1, h))
    val unusedElementStripe = Var("unused-element-stripe", todoStripe)

    // site/index.html backgrounds — also re-used as Zed UI surfaces.
    val pageBg   = Var("page-bg",    oklch(editorBg, l - 0.05, c, h))
    val tabBarBg = Var("tab-bar-bg", oklch(pageBg,   l + 0.01, c, h))
    val gutterBg = Var("gutter-bg",  oklch(editorBg, l + 0.05, c, h))
    val fgMuted = Var("fg-muted", namedArg)

    // IntelliJ tab underline — translucent green wash painted over the active tab.
    val underlinedTabBg = Var("underlined-tab-bg", oklch(selectionBg, l - 0.02, c + 0.03, h, alpha = 0.67))

    // ── Terminal ────────────────────────────────────────────────────────
    // 16 ANSI slots. Bright variants alias the normal slot unless they need a different value.

    val termBlack         = Var("term-black",          editorBg)
    val termRed           = Var("term-red",            string)
    val termGreen         = Var("term-green",          stringEscape)
    val termYellow        = Var("term-yellow",         oklch(termRed, l, c - 0.03, h))
    val termBlue          = Var("term-blue",           keyword)
    val termMagenta       = Var("term-magenta",        functionDecl)
    val termCyan          = Var("term-cyan",           constantField)
    val termWhite         = Var("term-white",          fg)

    val termBrightBlack   = Var("term-bright-black",   selectionBg)
    val termBrightRed     = Var("term-bright-red",     termRed)
    val termBrightGreen   = Var("term-bright-green",   termGreen)
    val termBrightYellow  = Var("term-bright-yellow",  termYellow)
    val termBrightBlue    = Var("term-bright-blue",    termBlue)
    val termBrightMagenta = Var("term-bright-magenta", termMagenta)
    val termBrightCyan    = Var("term-bright-cyan",    termCyan)
    val termBrightWhite   = Var("term-bright-white",   oklch(fg, l, c + 0.05, h))

    // ── Zed UI ───────────────────────────────────────────────────
    // colors for Zed's UI surface. Each is derived from editorBg/fg so the tone stays consistent with the code pane

    val transparent     = Var("transparent",      oklch(0.0, 0.0, 0.0, 0.0))
    val panelBg         = Var("panel-bg",         caretRow)
    val textMuted       = Var("text-muted",       oklch(fg, l - 0.05, c, h))
    val borderColor     = Var("border",           oklch(editorBg, l + 0.08, c, h))
    val borderVariant   = Var("border-variant",   oklch(editorBg, l + 0.04, c, h))
    val borderFocused   = Var("border-focused",   keyword)
    val elementHover    = Var("element-hover",    oklch(editorBg, l + 0.06, c, h))
    val elementActive   = Var("element-active",   oklch(editorBg, l + 0.10, c, h))
    val textAccent      = Var("text-accent",      keyword)
    val textPlaceholder = Var("text-placeholder", oklch(fg, l - 0.20, c, h))
    val scrollbarThumb  = Var("scrollbar-thumb",  oklch(editorBg, l + 0.08, c, h))
    val scrollbarTrack  = Var("scrollbar-track",  oklch(editorBg, l + 0.02, c, h))

    // ── Zed status tints ────────────────────────────────────────────────
    // Zed renders error/warning/info/success as a triple (fg, bg, border)

    val errorBg       = Var("error-bg",       oklch(editorBg, l + 0.04, c + 0.02, redHue))
    val errorBorder   = Var("error-border",   oklch(editorBg, l + 0.10, c + 0.03, redHue))
    val warningBorder = Var("warning-border", oklch(editorBg, l + 0.10, c + 0.03, secondaryHue))
    val infoBg        = Var("info-bg",        oklch(editorBg, l + 0.04, c + 0.02, blueHue))
    val infoBorder    = Var("info-border",    oklch(editorBg, l + 0.10, c + 0.03, blueHue))
    val successBg     = Var("success-bg",     oklch(editorBg, l + 0.04, c + 0.02, mainHue))
    val successBorder = Var("success-border", oklch(editorBg, l + 0.10, c + 0.03, mainHue))
}

// ── Palette Utilities ───────────────────────────────────────────────

tailrec fun oklchOf(e: Expr): Oklch? = when (e) {
  is Oklch -> e
  is Var -> oklchOf(e.value)
  else -> null
}

/** Lowercase 6-char hex for a color Var. Errors if the var doesn't resolve to an Oklch. */
fun hexOf(v: Var) = hexOf(oklchOf(v) ?: error("'${v.name}' does not resolve to a color"))
