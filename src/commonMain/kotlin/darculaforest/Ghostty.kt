package darculaforest

fun Palette.generateGhostty() = """
    palette = 0=${hex(termBlack)}
    palette = 1=${hex(termRed)}
    palette = 2=${hex(termGreen)}
    palette = 3=${hex(termYellow)}
    palette = 4=${hex(termBlue)}
    palette = 5=${hex(termMagenta)}
    palette = 6=${hex(termCyan)}
    palette = 7=${hex(termWhite)}
    palette = 8=${hex(termBrightBlack)}
    palette = 9=${hex(termBrightRed)}
    palette = 10=${hex(termBrightGreen)}
    palette = 11=${hex(termBrightYellow)}
    palette = 12=${hex(termBrightBlue)}
    palette = 13=${hex(termBrightMagenta)}
    palette = 14=${hex(termBrightCyan)}
    palette = 15=${hex(termBrightWhite)}
    background = ${hex(editorBg)}
    foreground = ${hex(fg)}
    cursor-color = ${hex(keyword)}
    cursor-text = ${hex(editorBg)}
    selection-background = ${hex(selectionBg)}
    selection-foreground = ${hex(fg)}
""".trimIndent()
