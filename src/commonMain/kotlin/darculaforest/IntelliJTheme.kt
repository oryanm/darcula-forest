package darculaforest

import darculaforest.IntelliJTheme.Colors
import darculaforest.IntelliJTheme.Icons
import darculaforest.IntelliJTheme.Icons.ColorPalette
import darculaforest.IntelliJTheme.UI
import darculaforest.IntelliJTheme.UI.UiDefaults
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

// ── Schema (IntelliJ *.theme.json) ──────────────────────────────────
// Field ordering here drives JSON key ordering in the output.

@Serializable
data class IntelliJTheme(
    val name: String,
    val author: String,
    val dark: Boolean,
    val parentTheme: String,
    val editorScheme: String,
    val colors: Colors,
    val ui: UI,
    val icons: Icons,
) {
    @Serializable
    data class Colors(
        @SerialName("editor-bg")          val editorBg: String,
        @SerialName("caret-row")          val caretRow: String,
        @SerialName("search-result-bg")   val searchResultBg: String,
        @SerialName("selection-bg")       val selectionBg: String,
        @SerialName("line-number")        val lineNumber: String,
        @SerialName("page-bg")            val pageBg: String,
        @SerialName("tab-bar-bg")         val tabBarBg: String,
        @SerialName("gutter-bg")          val gutterBg: String,
        val fg: String,
        @SerialName("text-muted")         val textMuted: String,
        @SerialName("text-placeholder")   val textPlaceholder: String,
        @SerialName("fg-muted")           val fgMuted: String,
        val border: String,
        @SerialName("border-variant")     val borderVariant: String,
        @SerialName("element-hover")      val elementHover: String,
        @SerialName("element-active")     val elementActive: String,
        val keyword: String,
        @SerialName("static-function")    val staticFunction: String,
        @SerialName("function-decl")      val functionDecl: String,
        @SerialName("constant-field")     val constantField: String,
        val number: String,
        val string: String,
        @SerialName("string-escape")      val stringEscape: String,
        @SerialName("string-escape-bad")  val stringEscapeBad: String,
        @SerialName("named-arg")          val namedArg: String,
        val error: String,
        @SerialName("warning-bg")         val warningBg: String,
        val todo: String,
        @SerialName("diff-add")           val diffAdd: String,
        @SerialName("diff-change")        val diffChange: String,
        @SerialName("diff-delete")        val diffDelete: String,
        @SerialName("diff-conflict")      val diffConflict: String,
        @SerialName("underlined-tab-bg")  val underlinedTabBg: String,
    )

    @Serializable
    data class UI(
        @SerialName("*")                    val all: UiDefaults,
        @SerialName("Panel.background")     val panelBackground: String,
        @SerialName("Borders.color")        val bordersColor: String,
        @SerialName("MainToolbar")          val mainToolbar: MainToolbar,
        @SerialName("ToolWindow")           val toolWindow: ToolWindow,
        @SerialName("MainWindow.background") val mainWindowBackground: String,
        @SerialName("EditorTabs")           val editorTabs: EditorTabs,
        @SerialName("Component")            val component: Component,
        @SerialName("Button")               val button: Button,
        @SerialName("ComboBox")             val comboBox: ComboBox,
        @SerialName("Link")                 val link: Link,
    ) {
        @Serializable
        data class UiDefaults(
            val background: String,
            val foreground: String,
            val selectionBackground: String,
            val selectionForeground: String,
            val selectionInactiveBackground: String,
            val selectionInactiveForeground: String,
            val infoForeground: String,
            val errorForeground: String,
            val warningForeground: String,
            val focusColor: String,
            val inactiveBackground: String,
            val lightSelectionBackground: String,
            val lightSelectionInactiveBackground: String,
        )

        @Serializable
        data class MainToolbar(val background: String)

        @Serializable
        data class ToolWindow(
            val background: String,
            @SerialName("Header.background")         val headerBackground: String,
            @SerialName("Header.inactiveBackground") val headerInactiveBackground: String,
        )

        @Serializable
        data class EditorTabs(
            val underlinedTabBackground: String,
            val underlinedBorderColor: String,
        )

        @Serializable
        data class Component(
            val borderColor: String,
            val disabledBorderColor: String,
            val focusedBorderColor: String,
            val errorFocusColor: String,
            val inactiveErrorFocusColor: String,
            val warningFocusColor: String,
            val inactiveWarningFocusColor: String,
        )

        @Serializable
        data class Button(
            val background: String,
            val foreground: String,
            val startBackground: String,
            val endBackground: String,
            val startBorderColor: String,
            val endBorderColor: String,
            val focusedBorderColor: String,
            val default: Default,
        ) {
            @Serializable
            data class Default(
                val startBackground: String,
                val endBackground: String,
                val startBorderColor: String,
                val endBorderColor: String,
                val foreground: String,
                val focusColor: String,
            )
        }

        @Serializable
        data class ComboBox(
            val background: String,
            val foreground: String,
            @SerialName("ArrowButton.background")            val arrowButtonBackground: String,
            @SerialName("ArrowButton.nonEditableBackground") val arrowButtonNonEditableBackground: String,
            val nonEditableBackground: String,
            val disabledBackground: String,
        )

        @Serializable
        data class Link(
            val activeForeground: String,
            val hoverForeground: String,
            val pressedForeground: String,
            val visitedForeground: String,
            val focusedBorderColor: String,
        )
    }

    @Serializable
    data class Icons(
        @SerialName("ColorPalette") val colorPalette: ColorPalette,
    ) {
        @Serializable
        data class ColorPalette(
            @SerialName("Green.Solid")   val greenSolid: String,
            @SerialName("Green.Fill")    val greenFill: String,
            @SerialName("Green.Stroke")  val greenStroke: String,
            @SerialName("Red.Solid")     val redSolid: String,
            @SerialName("Red.Fill")      val redFill: String,
            @SerialName("Red.Stroke")    val redStroke: String,
            @SerialName("Blue.Solid")    val blueSolid: String,
            @SerialName("Blue.Fill")     val blueFill: String,
            @SerialName("Blue.Stroke")   val blueStroke: String,
            @SerialName("Purple.Solid")  val purpleSolid: String,
            @SerialName("Purple.Fill")   val purpleFill: String,
            @SerialName("Purple.Stroke") val purpleStroke: String,
            @SerialName("Checkbox.Background.Default")  val checkboxBackgroundDefault: String,
            @SerialName("Checkbox.Background.Disabled") val checkboxBackgroundDisabled: String,
            @SerialName("Checkbox.Background.Selected") val checkboxBackgroundSelected: String,
            @SerialName("Checkbox.Border.Default")      val checkboxBorderDefault: String,
            @SerialName("Checkbox.Border.Selected")     val checkboxBorderSelected: String,
            @SerialName("Checkbox.Focus.Thin.Default")  val checkboxFocusThinDefault: String,
            @SerialName("Checkbox.Focus.Wide")          val checkboxFocusWide: String,
            @SerialName("Checkbox.Foreground.Selected") val checkboxForegroundSelected: String,
            @SerialName("Checkbox.Foreground.Disabled") val checkboxForegroundDisabled: String,
            @SerialName("Radio.Background.Default")     val radioBackgroundDefault: String,
            @SerialName("Radio.Background.Disabled")    val radioBackgroundDisabled: String,
            @SerialName("Radio.Background.Selected")    val radioBackgroundSelected: String,
            @SerialName("Radio.Border.Default")         val radioBorderDefault: String,
            @SerialName("Radio.Border.Selected")        val radioBorderSelected: String,
            @SerialName("Radio.Focus.Thin.Default")     val radioFocusThinDefault: String,
            @SerialName("Radio.Focus.Wide")             val radioFocusWide: String,
            @SerialName("Radio.Foreground.Selected")    val radioForegroundSelected: String,
            @SerialName("Radio.Foreground.Disabled")    val radioForegroundDisabled: String,
        )
    }
}






// ── Theme construction ─────────────────────────────────────────────

private fun hex(v: Expr.Var) = "#${hexOf(v)}"

private fun ref(v: Expr.Var) = v.name

val Palette.intellijTheme: IntelliJTheme get() = IntelliJTheme(
    name = "Darcula Forest",
    author = "Oryan",
    dark = true,
    parentTheme = "Islands Darcula",
    editorScheme = "/Darcula_Forest.xml",

    colors = Colors(
        editorBg         = hex(editorBg),
        caretRow         = hex(caretRow),
        searchResultBg   = hex(searchResultBg),
        selectionBg      = hex(selectionBg),
        lineNumber       = hex(lineNumber),
        pageBg           = hex(pageBg),
        tabBarBg         = hex(tabBarBg),
        gutterBg         = hex(gutterBg),
        fg               = hex(fg),
        textMuted        = hex(textMuted),
        textPlaceholder  = hex(textPlaceholder),
        fgMuted          = hex(fgMuted),
        border           = hex(borderColor),
        borderVariant    = hex(borderVariant),
        elementHover     = hex(elementHover),
        elementActive    = hex(elementActive),
        keyword          = hex(keyword),
        staticFunction   = hex(staticFunc),
        functionDecl     = hex(functionDecl),
        constantField    = hex(constantField),
        number           = hex(number),
        string           = hex(string),
        stringEscape     = hex(stringEscape),
        stringEscapeBad  = hex(stringEscBad),
        namedArg         = hex(namedArg),
        error            = hex(error),
        warningBg        = hex(warningBg),
        todo             = hex(todo),
        diffAdd          = hex(diffAdd),
        diffChange       = hex(diffChange),
        diffDelete       = hex(diffDelete),
        diffConflict     = hex(diffConflict),
        underlinedTabBg  = hex(underlinedTabBg),
    ),

    ui = UI(
        all = UiDefaults(
            background                       = ref(caretRow),
            foreground                       = ref(fg),
            selectionBackground              = ref(selectionBg),
            selectionForeground              = ref(fg),
            selectionInactiveBackground      = ref(elementActive),
            selectionInactiveForeground      = ref(fg),
            infoForeground                   = ref(textMuted),
            errorForeground                  = ref(error),
            warningForeground                = ref(todo),
            focusColor                       = ref(keyword),
            inactiveBackground               = ref(editorBg),
            lightSelectionBackground         = ref(elementActive),
            lightSelectionInactiveBackground = ref(elementHover),
        ),
        panelBackground      = ref(caretRow),
        bordersColor         = ref(searchResultBg),
        mainToolbar          = UI.MainToolbar(background = ref(caretRow)),
        toolWindow = UI.ToolWindow(
            background               = ref(editorBg),
            headerBackground         = ref(editorBg),
            headerInactiveBackground = ref(editorBg),
        ),
        mainWindowBackground = ref(caretRow),
        editorTabs = UI.EditorTabs(
            underlinedTabBackground = ref(underlinedTabBg),
            underlinedBorderColor   = ref(namedArg),
        ),
        component = UI.Component(
            borderColor               = ref(lineNumber),
            disabledBorderColor       = ref(lineNumber),
            focusedBorderColor        = ref(keyword),
            errorFocusColor           = ref(error),
            inactiveErrorFocusColor   = ref(error),
            warningFocusColor         = ref(todo),
            inactiveWarningFocusColor = ref(todo),
        ),
        button = UI.Button(
            background         = ref(caretRow),
            foreground         = ref(fg),
            startBackground    = ref(caretRow),
            endBackground      = ref(caretRow),
            startBorderColor   = ref(lineNumber),
            endBorderColor     = ref(lineNumber),
            focusedBorderColor = ref(keyword),
            default = UI.Button.Default(
                startBackground  = ref(keyword),
                endBackground    = ref(keyword),
                startBorderColor = ref(keyword),
                endBorderColor   = ref(keyword),
                foreground       = ref(caretRow),
                focusColor       = ref(keyword),
            ),
        ),
        comboBox = UI.ComboBox(
            background                        = ref(caretRow),
            foreground                        = ref(fg),
            arrowButtonBackground             = ref(caretRow),
            arrowButtonNonEditableBackground  = ref(caretRow),
            nonEditableBackground             = ref(caretRow),
            disabledBackground                = ref(caretRow),
        ),
        link = UI.Link(
            activeForeground   = ref(keyword),
            hoverForeground    = ref(constantField),
            pressedForeground  = ref(functionDecl),
            visitedForeground  = ref(namedArg),
            focusedBorderColor = ref(keyword),
        ),
    ),

    icons = Icons(
        colorPalette = ColorPalette(
            greenSolid                  = ref(keyword),
            greenFill                   = ref(editorBg),
            greenStroke                 = ref(keyword),
            redSolid                    = ref(string),
            redFill                     = ref(editorBg),
            redStroke                   = ref(string),
            blueSolid                   = ref(stringEscape),
            blueFill                    = ref(editorBg),
            blueStroke                  = ref(stringEscape),
            purpleSolid                 = ref(functionDecl),
            purpleFill                  = ref(editorBg),
            purpleStroke                = ref(functionDecl),
            checkboxBackgroundDefault   = ref(searchResultBg),
            checkboxBackgroundDisabled  = ref(caretRow),
            checkboxBackgroundSelected  = ref(keyword),
            checkboxBorderDefault       = ref(lineNumber),
            checkboxBorderSelected      = ref(keyword),
            checkboxFocusThinDefault    = ref(keyword),
            checkboxFocusWide           = ref(keyword),
            checkboxForegroundSelected  = ref(searchResultBg),
            checkboxForegroundDisabled  = ref(lineNumber),
            radioBackgroundDefault      = ref(searchResultBg),
            radioBackgroundDisabled     = ref(caretRow),
            radioBackgroundSelected     = ref(keyword),
            radioBorderDefault          = ref(lineNumber),
            radioBorderSelected         = ref(keyword),
            radioFocusThinDefault       = ref(keyword),
            radioFocusWide              = ref(keyword),
            radioForegroundSelected     = ref(searchResultBg),
            radioForegroundDisabled     = ref(lineNumber),
        ),
    ),
)

@OptIn(ExperimentalSerializationApi::class)
private val intellijJson = Json {
    prettyPrint = true
    prettyPrintIndent = "  "
}

fun Palette.generateIntellijTheme() = intellijJson.encodeToString(IntelliJTheme.serializer(), intellijTheme)
