package darculaforest

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

// ── Schema (Zed theme JSON v0.2.0) ──────────────────────────────────
// Field ordering here drives JSON key ordering in the output.

@Serializable
data class ZedTheme(
    @SerialName("\$schema") val schema: String,
    val name: String,
    val author: String,
    val themes: List<ZedThemeVariant>,
)

@Serializable
data class ZedThemeVariant(
    val name: String,
    val appearance: String,
    val style: ZedStyle,
)

@Serializable
data class ZedStyle(
    // Borders
    val border: String,
    @SerialName("border.variant")    val borderVariant: String,
    @SerialName("border.focused")    val borderFocused: String,
    @SerialName("border.selected")   val borderSelected: String,
    @SerialName("border.transparent") val borderTransparent: String,
    @SerialName("border.disabled")   val borderDisabled: String,

    // Surfaces
    @SerialName("elevated_surface.background") val elevatedSurfaceBackground: String,
    @SerialName("surface.background")          val surfaceBackground: String,
    val background: String,
    @SerialName("element.background")    val elementBackground: String,
    @SerialName("element.hover")         val elementHover: String,
    @SerialName("element.active")        val elementActive: String,
    @SerialName("element.selected")      val elementSelected: String,
    @SerialName("element.disabled")      val elementDisabled: String,
    @SerialName("drop_target.background") val dropTargetBackground: String,
    @SerialName("ghost_element.background") val ghostElementBackground: String,
    @SerialName("ghost_element.hover")      val ghostElementHover: String,
    @SerialName("ghost_element.active")     val ghostElementActive: String,
    @SerialName("ghost_element.selected")   val ghostElementSelected: String,
    @SerialName("ghost_element.disabled")   val ghostElementDisabled: String,

    // Text & icons
    val text: String,
    @SerialName("text.muted")        val textMuted: String,
    @SerialName("text.placeholder")  val textPlaceholder: String,
    @SerialName("text.disabled")     val textDisabled: String,
    @SerialName("text.accent")       val textAccent: String,
    val icon: String,
    @SerialName("icon.muted")        val iconMuted: String,
    @SerialName("icon.disabled")     val iconDisabled: String,
    @SerialName("icon.placeholder")  val iconPlaceholder: String,
    @SerialName("icon.accent")       val iconAccent: String,

    // Chrome bars
    @SerialName("status_bar.background")           val statusBarBackground: String,
    @SerialName("title_bar.background")            val titleBarBackground: String,
    @SerialName("title_bar.inactive_background")   val titleBarInactiveBackground: String,
    @SerialName("toolbar.background")              val toolbarBackground: String,
    @SerialName("tab_bar.background")              val tabBarBackground: String,
    @SerialName("tab.inactive_background")         val tabInactiveBackground: String,
    @SerialName("tab.active_background")           val tabActiveBackground: String,
    @SerialName("search.match_background")         val searchMatchBackground: String,
    @SerialName("search.active_match_background") val searchActiveMatchBackground: String,
    @SerialName("panel.background")                val panelBackground: String,

    // Scrollbar
    @SerialName("scrollbar.thumb.background")       val scrollbarThumbBackground: String,
    @SerialName("scrollbar.thumb.hover_background") val scrollbarThumbHoverBackground: String,
    @SerialName("scrollbar.thumb.border")           val scrollbarThumbBorder: String,
    @SerialName("scrollbar.track.background")       val scrollbarTrackBackground: String,
    @SerialName("scrollbar.track.border")           val scrollbarTrackBorder: String,

    // Editor pane
    @SerialName("editor.foreground")                          val editorForeground: String,
    @SerialName("editor.background")                          val editorBackground: String,
    @SerialName("editor.gutter.background")                   val editorGutterBackground: String,
    @SerialName("editor.subheader.background")                val editorSubheaderBackground: String,
    @SerialName("editor.active_line.background")              val editorActiveLineBackground: String,
    @SerialName("editor.highlighted_line.background")         val editorHighlightedLineBackground: String,
    @SerialName("editor.line_number")                         val editorLineNumber: String,
    @SerialName("editor.active_line_number")                  val editorActiveLineNumber: String,
    @SerialName("editor.hover_line_number")                   val editorHoverLineNumber: String,
    @SerialName("editor.invisible")                           val editorInvisible: String,
    @SerialName("editor.wrap_guide")                          val editorWrapGuide: String,
    @SerialName("editor.active_wrap_guide")                   val editorActiveWrapGuide: String,
    @SerialName("editor.document_highlight.read_background")  val editorDocumentHighlightReadBackground: String,
    @SerialName("editor.document_highlight.write_background") val editorDocumentHighlightWriteBackground: String,

    // Terminal
    @SerialName("terminal.background")          val terminalBackground: String,
    @SerialName("terminal.foreground")          val terminalForeground: String,
    @SerialName("terminal.bright_foreground")   val terminalBrightForeground: String,
    @SerialName("terminal.dim_foreground")      val terminalDimForeground: String,
    @SerialName("terminal.ansi.black")          val terminalAnsiBlack: String,
    @SerialName("terminal.ansi.bright_black")   val terminalAnsiBrightBlack: String,
    @SerialName("terminal.ansi.dim_black")      val terminalAnsiDimBlack: String,
    @SerialName("terminal.ansi.red")            val terminalAnsiRed: String,
    @SerialName("terminal.ansi.bright_red")     val terminalAnsiBrightRed: String,
    @SerialName("terminal.ansi.dim_red")        val terminalAnsiDimRed: String,
    @SerialName("terminal.ansi.green")          val terminalAnsiGreen: String,
    @SerialName("terminal.ansi.bright_green")   val terminalAnsiBrightGreen: String,
    @SerialName("terminal.ansi.dim_green")      val terminalAnsiDimGreen: String,
    @SerialName("terminal.ansi.yellow")         val terminalAnsiYellow: String,
    @SerialName("terminal.ansi.bright_yellow")  val terminalAnsiBrightYellow: String,
    @SerialName("terminal.ansi.dim_yellow")     val terminalAnsiDimYellow: String,
    @SerialName("terminal.ansi.blue")           val terminalAnsiBlue: String,
    @SerialName("terminal.ansi.bright_blue")    val terminalAnsiBrightBlue: String,
    @SerialName("terminal.ansi.dim_blue")       val terminalAnsiDimBlue: String,
    @SerialName("terminal.ansi.magenta")        val terminalAnsiMagenta: String,
    @SerialName("terminal.ansi.bright_magenta") val terminalAnsiBrightMagenta: String,
    @SerialName("terminal.ansi.dim_magenta")    val terminalAnsiDimMagenta: String,
    @SerialName("terminal.ansi.cyan")           val terminalAnsiCyan: String,
    @SerialName("terminal.ansi.bright_cyan")    val terminalAnsiBrightCyan: String,
    @SerialName("terminal.ansi.dim_cyan")       val terminalAnsiDimCyan: String,
    @SerialName("terminal.ansi.white")          val terminalAnsiWhite: String,
    @SerialName("terminal.ansi.bright_white")   val terminalAnsiBrightWhite: String,
    @SerialName("terminal.ansi.dim_white")      val terminalAnsiDimWhite: String,

    // Version control
    @SerialName("link_text.hover")                          val linkTextHover: String,
    @SerialName("version_control.added")                    val versionControlAdded: String,
    @SerialName("version_control.modified")                 val versionControlModified: String,
    @SerialName("version_control.deleted")                  val versionControlDeleted: String,
    @SerialName("version_control.word_added")               val versionControlWordAdded: String,
    @SerialName("version_control.word_deleted")             val versionControlWordDeleted: String,
    @SerialName("version_control.conflict_marker.ours")     val versionControlConflictMarkerOurs: String,
    @SerialName("version_control.conflict_marker.theirs")   val versionControlConflictMarkerTheirs: String,

    // Status / diagnostics
    val conflict: String,
    @SerialName("conflict.background")    val conflictBackground: String,
    @SerialName("conflict.border")        val conflictBorder: String,
    val created: String,
    @SerialName("created.background")     val createdBackground: String,
    @SerialName("created.border")         val createdBorder: String,
    val deleted: String,
    @SerialName("deleted.background")     val deletedBackground: String,
    @SerialName("deleted.border")         val deletedBorder: String,
    val error: String,
    @SerialName("error.background")       val errorBackground: String,
    @SerialName("error.border")           val errorBorder: String,
    val hidden: String,
    @SerialName("hidden.background")      val hiddenBackground: String,
    @SerialName("hidden.border")          val hiddenBorder: String,
    val hint: String,
    @SerialName("hint.background")        val hintBackground: String,
    @SerialName("hint.border")            val hintBorder: String,
    val ignored: String,
    @SerialName("ignored.background")     val ignoredBackground: String,
    @SerialName("ignored.border")         val ignoredBorder: String,
    val info: String,
    @SerialName("info.background")        val infoBackground: String,
    @SerialName("info.border")            val infoBorder: String,
    val modified: String,
    @SerialName("modified.background")    val modifiedBackground: String,
    @SerialName("modified.border")        val modifiedBorder: String,
    val predictive: String,
    @SerialName("predictive.background")  val predictiveBackground: String,
    @SerialName("predictive.border")      val predictiveBorder: String,
    val renamed: String,
    @SerialName("renamed.background")     val renamedBackground: String,
    @SerialName("renamed.border")         val renamedBorder: String,
    val success: String,
    @SerialName("success.background")     val successBackground: String,
    @SerialName("success.border")         val successBorder: String,
    val unreachable: String,
    @SerialName("unreachable.background") val unreachableBackground: String,
    @SerialName("unreachable.border")     val unreachableBorder: String,
    val warning: String,
    @SerialName("warning.background")     val warningBackground: String,
    @SerialName("warning.border")         val warningBorder: String,

    val players: List<ZedPlayer>,
    val syntax: ZedSyntax,
)

@Serializable
data class ZedPlayer(
    val cursor: String,
    val background: String,
    val selection: String,
)

@Serializable
data class ZedToken(
    val color: String,
    @SerialName("font_style")  val fontStyle: String? = null,
    @SerialName("font_weight") val fontWeight: Int? = null,
)

@Serializable
data class ZedSyntax(
    val attribute: ZedToken,
    val boolean: ZedToken,
    val comment: ZedToken,
    @SerialName("comment.doc")              val commentDoc: ZedToken,
    val constant: ZedToken,
    val constructor: ZedToken,
    val embedded: ZedToken,
    val emphasis: ZedToken,
    @SerialName("emphasis.strong")          val emphasisStrong: ZedToken,
    val enum: ZedToken,
    val function: ZedToken,
    val hint: ZedToken,
    val keyword: ZedToken,
    val label: ZedToken,
    @SerialName("link_text")                val linkText: ZedToken,
    @SerialName("link_uri")                 val linkUri: ZedToken,
    val namespace: ZedToken,
    val number: ZedToken,
    val operator: ZedToken,
    val predictive: ZedToken,
    val preproc: ZedToken,
    val primary: ZedToken,
    val property: ZedToken,
    val punctuation: ZedToken,
    @SerialName("punctuation.bracket")      val punctuationBracket: ZedToken,
    @SerialName("punctuation.delimiter")    val punctuationDelimiter: ZedToken,
    @SerialName("punctuation.list_marker")  val punctuationListMarker: ZedToken,
    @SerialName("punctuation.markup")       val punctuationMarkup: ZedToken,
    @SerialName("punctuation.special")      val punctuationSpecial: ZedToken,
    val selector: ZedToken,
    @SerialName("selector.pseudo")          val selectorPseudo: ZedToken,
    val string: ZedToken,
    @SerialName("string.escape")            val stringEscape: ZedToken,
    @SerialName("string.regex")             val stringRegex: ZedToken,
    @SerialName("string.special")           val stringSpecial: ZedToken,
    @SerialName("string.special.symbol")    val stringSpecialSymbol: ZedToken,
    val tag: ZedToken,
    @SerialName("text.literal")             val textLiteral: ZedToken,
    val title: ZedToken,
    val type: ZedToken,
    val variable: ZedToken,
    @SerialName("variable.special")         val variableSpecial: ZedToken,
    val variant: ZedToken,
    @SerialName("diff.plus")                val diffPlus: ZedToken,
    @SerialName("diff.minus")               val diffMinus: ZedToken,
)

// ── Theme construction ─────────────────────────────────────────────

private fun hex(v: Expr.Var, alpha: Double? = null) =
  hexOf(alpha?.let { oklch(from = v, l = l, c = c, h = h, alpha = it) }
    ?: (oklchOf(v) ?: error("'${v.name}' does not resolve to a color")))
    .let { "#${it}" }

private fun token(v: Expr.Var, fontStyle: String? = null, fontWeight: Int? = null) = ZedToken(hex(v), fontStyle, fontWeight)

private fun player(v: Expr.Var) = ZedPlayer(hex(v), hex(v), hex(v, alpha = 0.24))

val Palette.zedTheme: ZedTheme get() = ZedTheme(
    schema = "https://zed.dev/schema/themes/v0.2.0.json",
    name = "Darcula Forest",
    author = "Oryan",
    themes = listOf(
        ZedThemeVariant(
            name = "Darcula Forest",
            appearance = "dark",
            style = ZedStyle(
                // Borders
                border             = hex(borderColor),
                borderVariant      = hex(borderVariant),
                borderFocused      = hex(borderFocused),
                borderSelected     = hex(selectionBg),
                borderTransparent  = hex(transparent),
                borderDisabled     = hex(borderVariant),

                // Surfaces
                elevatedSurfaceBackground = hex(pageBg),
                surfaceBackground         = hex(pageBg),
                background                = hex(gutterBg),
                elementBackground         = hex(panelBg),
                elementHover              = hex(elementHover),
                elementActive             = hex(elementActive),
                elementSelected           = hex(selectionBg),
                elementDisabled           = hex(panelBg),
                dropTargetBackground      = hex(selectionBg, alpha = 0.5),
                ghostElementBackground    = hex(transparent),
                ghostElementHover         = hex(elementHover),
                ghostElementActive        = hex(elementActive),
                ghostElementSelected      = hex(selectionBg),
                ghostElementDisabled      = hex(panelBg),

                // Text & icons
                text             = hex(fg),
                textMuted        = hex(textMuted),
                textPlaceholder  = hex(textPlaceholder),
                textDisabled     = hex(textPlaceholder),
                textAccent       = hex(textAccent),
                icon             = hex(fg),
                iconMuted        = hex(textMuted),
                iconDisabled     = hex(textPlaceholder),
                iconPlaceholder  = hex(textMuted),
                iconAccent       = hex(textAccent),

                // Chrome bars
                statusBarBackground         = hex(gutterBg),
                titleBarBackground          = hex(gutterBg),
                titleBarInactiveBackground  = hex(caretRow),
                toolbarBackground           = hex(editorBg),
                tabBarBackground            = hex(panelBg),
                tabInactiveBackground       = hex(panelBg),
                tabActiveBackground         = hex(editorBg),
                searchMatchBackground       = hex(searchResultBg),
                searchActiveMatchBackground = hex(searchResultStripe),
                panelBackground             = hex(panelBg),

                // Scrollbar
                scrollbarThumbBackground       = hex(scrollbarThumb),
                scrollbarThumbHoverBackground  = hex(elementHover),
                scrollbarThumbBorder           = hex(borderVariant),
                scrollbarTrackBackground       = hex(scrollbarTrack),
                scrollbarTrackBorder           = hex(borderVariant),

                // Editor pane
                editorForeground                          = hex(fg),
                editorBackground                          = hex(editorBg),
                editorGutterBackground                    = hex(editorBg),
                editorSubheaderBackground                 = hex(pageBg),
                editorActiveLineBackground                = hex(caretRow),
                editorHighlightedLineBackground           = hex(caretRow),
                editorLineNumber                          = hex(lineNumber),
                editorActiveLineNumber                    = hex(fg),
                editorHoverLineNumber                     = hex(textMuted),
                editorInvisible                           = hex(lineNumber),
                editorWrapGuide                           = hex(borderVariant),
                editorActiveWrapGuide                     = hex(borderColor),
                editorDocumentHighlightReadBackground     = hex(searchResultBg),
                editorDocumentHighlightWriteBackground    = hex(selectionBg),

                // Terminal
                terminalBackground          = hex(editorBg),
                terminalForeground          = hex(fg),
                terminalBrightForeground    = hex(termBrightWhite),
                terminalDimForeground       = hex(textMuted),
                terminalAnsiBlack           = hex(termBlack),
                terminalAnsiBrightBlack     = hex(termBrightBlack),
                terminalAnsiDimBlack        = hex(termBlack),
                terminalAnsiRed             = hex(termRed),
                terminalAnsiBrightRed       = hex(termBrightRed),
                terminalAnsiDimRed          = hex(termRed),
                terminalAnsiGreen           = hex(termGreen),
                terminalAnsiBrightGreen     = hex(termBrightGreen),
                terminalAnsiDimGreen        = hex(termGreen),
                terminalAnsiYellow          = hex(termYellow),
                terminalAnsiBrightYellow    = hex(termBrightYellow),
                terminalAnsiDimYellow       = hex(termYellow),
                terminalAnsiBlue            = hex(termBlue),
                terminalAnsiBrightBlue      = hex(termBrightBlue),
                terminalAnsiDimBlue         = hex(termBlue),
                terminalAnsiMagenta         = hex(termMagenta),
                terminalAnsiBrightMagenta   = hex(termBrightMagenta),
                terminalAnsiDimMagenta      = hex(termMagenta),
                terminalAnsiCyan            = hex(termCyan),
                terminalAnsiBrightCyan      = hex(termBrightCyan),
                terminalAnsiDimCyan         = hex(termCyan),
                terminalAnsiWhite           = hex(termWhite),
                terminalAnsiBrightWhite     = hex(termBrightWhite),
                terminalAnsiDimWhite        = hex(termWhite),

                // Version control
                linkTextHover                       = hex(textAccent),
                versionControlAdded                 = hex(diffAddStripe),
                versionControlModified              = hex(diffChangeStripe),
                versionControlDeleted               = hex(diffDeleteStripe),
                versionControlWordAdded             = hex(diffAdd),
                versionControlWordDeleted           = hex(diffDelete),
                versionControlConflictMarkerOurs    = hex(diffAdd),
                versionControlConflictMarkerTheirs  = hex(diffConflict),

                // Status / diagnostics
                conflict             = hex(diffConflictStripe),
                conflictBackground   = hex(warningBg),
                conflictBorder       = hex(warningBorder),
                created              = hex(diffAddStripe),
                createdBackground    = hex(successBg),
                createdBorder        = hex(successBorder),
                deleted              = hex(diffDeleteStripe),
                deletedBackground    = hex(errorBg),
                deletedBorder        = hex(errorBorder),
                error                = hex(error),
                errorBackground      = hex(errorBg),
                errorBorder          = hex(errorBorder),
                hidden               = hex(textMuted),
                hiddenBackground     = hex(panelBg),
                hiddenBorder         = hex(borderVariant),
                hint                 = hex(textMuted),
                hintBackground       = hex(infoBg),
                hintBorder           = hex(infoBorder),
                ignored              = hex(textMuted),
                ignoredBackground    = hex(panelBg),
                ignoredBorder        = hex(borderVariant),
                info                 = hex(keyword),
                infoBackground       = hex(infoBg),
                infoBorder           = hex(infoBorder),
                modified             = hex(diffChangeStripe),
                modifiedBackground   = hex(warningBg),
                modifiedBorder       = hex(warningBorder),
                predictive           = hex(textPlaceholder),
                predictiveBackground = hex(panelBg),
                predictiveBorder     = hex(borderVariant),
                renamed              = hex(diffChangeStripe),
                renamedBackground    = hex(infoBg),
                renamedBorder        = hex(infoBorder),
                success              = hex(diffAddStripe),
                successBackground    = hex(successBg),
                successBorder        = hex(successBorder),
                unreachable          = hex(textMuted),
                unreachableBackground = hex(panelBg),
                unreachableBorder    = hex(borderVariant),
                warning              = hex(todoStripe),
                warningBackground    = hex(warningBg),
                warningBorder        = hex(warningBorder),

                players = listOf(
                    player(keyword),
                    player(error),
                    player(number),
                    player(functionDecl),
                    player(constantField),
                    player(todoStripe),
                    player(string),
                    player(diffAddStripe),
                ),

                syntax = ZedSyntax(
                    attribute             = token(annotationNamedAtt),
                    boolean               = token(keyword),
                    comment               = token(comment, fontStyle = "italic"),
                    commentDoc            = token(javadoc, fontStyle = "italic"),
                    constant              = token(constantField, fontStyle = "italic"),
                    constructor           = token(functionDecl),
                    embedded              = token(fg), // injected/embedded language strings
                    emphasis              = token(keyword, fontStyle = "italic"), // markdown italics
                    emphasisStrong        = token(keyword, fontWeight = 700), // markdown bold
                    enum                  = token(constantField),
                    function              = token(functionDecl),
                    hint                  = token(textMuted), // inlay hints
                    keyword               = token(keyword),
                    label                 = token(number),
                    linkText              = token(string, fontStyle = "italic"),
                    linkUri               = token(keyword),
                    namespace             = token(fg),
                    number                = token(number),
                    operator              = token(operator),
                    predictive            = token(textPlaceholder, fontStyle = "italic"), // AI nonsense
                    preproc               = token(annotation),
                    primary               = token(fg), // same as foreground probably
                    property              = token(fg),
                    punctuation           = token(punctuation),
                    punctuationBracket    = token(parens),
                    punctuationDelimiter  = token(punctuation),
                    punctuationListMarker = token(keyword),
                    punctuationMarkup     = token(keyword),
                    punctuationSpecial    = token(stringEscape),
                    selector              = token(keyword), // CSS selectors
                    selectorPseudo        = token(functionDecl), // CSS pseudo-classes/elements
                    string                = token(string),
                    stringEscape          = token(stringEscape),
                    stringRegex           = token(stringEscape),
                    stringSpecial         = token(stringEscape),
                    stringSpecialSymbol   = token(stringEscape),
                    tag                   = token(keyword),
                    textLiteral           = token(string), // markdown inline code span/block
                    title                 = token(functionDecl, fontWeight = 700), // markdown headings
                    type                  = token(fg),
                    variable              = token(fg),
                    variableSpecial       = token(implicitParam),
                    variant               = token(constantField),
                    diffPlus              = token(diffAddStripe),
                    diffMinus             = token(diffDeleteStripe),
                ),
            ),
        ),
    ),
)

@OptIn(ExperimentalSerializationApi::class)
private val zedJson = Json {
    prettyPrint = true
    prettyPrintIndent = "  "
}

fun Palette.generateZed(): String = zedJson.encodeToString(ZedTheme.serializer(), zedTheme)
