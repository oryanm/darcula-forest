package darculaforest

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

// ── T3 Code theme ───────────────────────────────────────────────────
// Absolute oklch() values; every entry resolves from a Palette var so the tone matches the editor.

@Serializable
data class T3CodeTheme(
    val version: Int,
    val id: String,
    val name: String,
    val appearance: String,
    val colors: Map<String, String>,
)

val Palette.t3CodeTheme: T3CodeTheme get() {
    fun c(v: Expr.Var) = absoluteOklchOf(v)
    return T3CodeTheme(
        version = 1,
        id = "darcula-forest",
        name = "Darcula Forest",
        appearance = "dark",
        colors = linkedMapOf(
            "canvas"                   to c(editorBg),
            "chrome"                   to c(editorBg),
            "toolbar"                  to c(editorBg),
            "toolbarForeground"        to c(fg),
            "toolbarBorder"            to c(borderColor),
            "toolbarControl"           to c(elementHover),
            "toolbarControlForeground" to c(fg),
            "toolbarControlHover"      to c(elementActive),
            "surface"                  to c(editorBg),
            "surfaceRaised"            to c(caretRow),
            "surfaceOverlay"           to c(gutterBg),
            "text"                     to c(fg),
            "textMuted"                to c(textMuted),
            "border"                   to c(borderColor),
            "input"                    to c(elementActive),
            "focus"                    to c(keyword),
            "accent"                   to c(keyword),
            "accentForeground"         to c(editorBg),
            "secondary"                to c(elementHover),
            "secondaryForeground"      to c(fg),
            "muted"                    to c(borderVariant),
            "mutedForeground"          to c(fgMuted),
            "placeholder"              to c(textPlaceholder),
            "secondaryLabel"           to c(textMuted),
            "iconMuted"                to c(textMuted),
            "error"                    to c(error),
            "errorForeground"          to c(error),
            "errorSurface"             to c(errorBg),
            "warning"                  to c(termYellow),
            "warningForeground"        to c(termYellow),
            "warningSurface"           to c(warningBg),
            "update"                   to c(diffChangeStripe),
            "updateForeground"         to c(diffChangeStripe),
            "updateSurface"            to c(infoBg),
            "accentSurface"            to c(successBg),
            "accentSurfaceForeground"  to c(fg),
            "messageSurface"           to c(selectionBg),
            "messageForeground"        to c(fg),
            "messageAction"            to c(constantField),
            "messageActionForeground"  to c(editorBg),
            "messageActionHover"       to c(implicitParam),
            "codeBackground"           to c(injectedLangBg),
            "codeForeground"           to c(fg),
            "sidebar"                  to c(panelBg),
            "sidebarForeground"        to c(fg),
            "sidebarMutedForeground"   to c(textMuted),
            "sidebarControlSurface"    to c(elementActive),
            "sidebarRowHover"          to c(elementHover),
            "sidebarRowActive"         to c(elementActive),
            "sidebarRowSelected"       to c(selectionBg),
            "sidebarBorder"            to c(borderColor),
            "terminalBackground"       to c(editorBg),
            "terminalForeground"       to c(fg),
            "terminalCursor"           to c(keyword),
            "terminalSelection"        to c(selectionBg),
            "terminalScrollbar"        to c(scrollbarThumb),
            "terminalScrollbarHover"   to c(elementActive),
        ),
    )
}

@OptIn(ExperimentalSerializationApi::class)
private val t3Json = Json {
    prettyPrint = true
    prettyPrintIndent = "  "
}

fun Palette.generateT3Code(): String = t3Json.encodeToString(T3CodeTheme.serializer(), t3CodeTheme)
