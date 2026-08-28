package darculaforest

fun Palette.generateFoot() = """
    [colors]
    background=${hexOf(editorBg)}
    foreground=${hexOf(fg)}
    selection-foreground=${hexOf(fg)}
    selection-background=${hexOf(selectionBg)}
    urls=${hexOf(keyword)}

    regular0=${hexOf(termBlack)}
    regular1=${hexOf(termRed)}
    regular2=${hexOf(termGreen)}
    regular3=${hexOf(termYellow)}
    regular4=${hexOf(termBlue)}
    regular5=${hexOf(termMagenta)}
    regular6=${hexOf(termCyan)}
    regular7=${hexOf(termWhite)}

    bright0=${hexOf(termBrightBlack)}
    bright1=${hexOf(termBrightRed)}
    bright2=${hexOf(termBrightGreen)}
    bright3=${hexOf(termBrightYellow)}
    bright4=${hexOf(termBrightBlue)}
    bright5=${hexOf(termBrightMagenta)}
    bright6=${hexOf(termBrightCyan)}
    bright7=${hexOf(termBrightWhite)}
""".trimIndent()
