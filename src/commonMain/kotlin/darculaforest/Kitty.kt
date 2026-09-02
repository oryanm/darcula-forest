package darculaforest

fun Palette.generateKitty() = """
    foreground            ${hex(fg)}
    background            ${hex(editorBg)}
    cursor                ${hex(keyword)}
    cursor_text_color     ${hex(editorBg)}
    selection_foreground  ${hex(fg)}
    selection_background  ${hex(selectionBg)}
    url_color             ${hex(keyword)}

    active_border_color   ${hex(keyword)}
    inactive_border_color ${hex(borderColor)}

    tab_bar_background      ${hex(tabBarBg)}
    active_tab_foreground   ${hex(fg)}
    active_tab_background   ${hex(editorBg)}
    inactive_tab_foreground ${hex(textMuted)}
    inactive_tab_background ${hex(tabBarBg)}

    color0  ${hex(termBlack)}
    color1  ${hex(termRed)}
    color2  ${hex(termGreen)}
    color3  ${hex(termYellow)}
    color4  ${hex(termBlue)}
    color5  ${hex(termMagenta)}
    color6  ${hex(termCyan)}
    color7  ${hex(termWhite)}
    color8  ${hex(termBrightBlack)}
    color9  ${hex(termBrightRed)}
    color10 ${hex(termBrightGreen)}
    color11 ${hex(termBrightYellow)}
    color12 ${hex(termBrightBlue)}
    color13 ${hex(termBrightMagenta)}
    color14 ${hex(termBrightCyan)}
    color15 ${hex(termBrightWhite)}
""".trimIndent()
