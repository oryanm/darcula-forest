package darculaforest

fun Palette.generateKitty() = """
    foreground            #${hexOf(fg)}
    background            #${hexOf(editorBg)}
    cursor                #${hexOf(keyword)}
    cursor_text_color     #${hexOf(editorBg)}
    selection_foreground  #${hexOf(fg)}
    selection_background  #${hexOf(selectionBg)}
    url_color             #${hexOf(keyword)}

    active_border_color   #${hexOf(keyword)}
    inactive_border_color #${hexOf(borderColor)}

    tab_bar_background      #${hexOf(tabBarBg)}
    active_tab_foreground   #${hexOf(fg)}
    active_tab_background   #${hexOf(editorBg)}
    inactive_tab_foreground #${hexOf(textMuted)}
    inactive_tab_background #${hexOf(tabBarBg)}

    color0  #${hexOf(termBlack)}
    color1  #${hexOf(termRed)}
    color2  #${hexOf(termGreen)}
    color3  #${hexOf(termYellow)}
    color4  #${hexOf(termBlue)}
    color5  #${hexOf(termMagenta)}
    color6  #${hexOf(termCyan)}
    color7  #${hexOf(termWhite)}
    color8  #${hexOf(termBrightBlack)}
    color9  #${hexOf(termBrightRed)}
    color10 #${hexOf(termBrightGreen)}
    color11 #${hexOf(termBrightYellow)}
    color12 #${hexOf(termBrightBlue)}
    color13 #${hexOf(termBrightMagenta)}
    color14 #${hexOf(termBrightCyan)}
    color15 #${hexOf(termBrightWhite)}
""".trimIndent()
