package darculaforest

fun Palette.generateGhostty() = """
    palette = 0=#${hexOf(termBlack)}
    palette = 1=#${hexOf(termRed)}
    palette = 2=#${hexOf(termGreen)}
    palette = 3=#${hexOf(termYellow)}
    palette = 4=#${hexOf(termBlue)}
    palette = 5=#${hexOf(termMagenta)}
    palette = 6=#${hexOf(termCyan)}
    palette = 7=#${hexOf(termWhite)}
    palette = 8=#${hexOf(termBrightBlack)}
    palette = 9=#${hexOf(termBrightRed)}
    palette = 10=#${hexOf(termBrightGreen)}
    palette = 11=#${hexOf(termBrightYellow)}
    palette = 12=#${hexOf(termBrightBlue)}
    palette = 13=#${hexOf(termBrightMagenta)}
    palette = 14=#${hexOf(termBrightCyan)}
    palette = 15=#${hexOf(termBrightWhite)}
    background = #${hexOf(editorBg)}
    foreground = #${hexOf(fg)}
    cursor-color = #${hexOf(keyword)}
    cursor-text = #${hexOf(editorBg)}
    selection-background = #${hexOf(selectionBg)}
    selection-foreground = #${hexOf(fg)}
""".trimIndent()
