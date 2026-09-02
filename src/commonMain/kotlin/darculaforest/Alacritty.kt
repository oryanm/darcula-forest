package darculaforest

fun Palette.generateAlacritty() = """
    [colors]
    indexed_colors = [{ index = 16, color = "${hex(editorBg)}" }]

    [colors.primary]
    background = "${hex(editorBg)}"
    foreground = "${hex(fg)}"

    [colors.normal]
    black   = "${hex(termBlack)}"
    red     = "${hex(termRed)}"
    green   = "${hex(termGreen)}"
    yellow  = "${hex(termYellow)}"
    blue    = "${hex(termBlue)}"
    magenta = "${hex(termMagenta)}"
    cyan    = "${hex(termCyan)}"
    white   = "${hex(termWhite)}"

    [colors.bright]
    black   = "${hex(termBrightBlack)}"
    red     = "${hex(termBrightRed)}"
    green   = "${hex(termBrightGreen)}"
    yellow  = "${hex(termBrightYellow)}"
    blue    = "${hex(termBrightBlue)}"
    magenta = "${hex(termBrightMagenta)}"
    cyan    = "${hex(termBrightCyan)}"
    white   = "${hex(termBrightWhite)}"
""".trimIndent()
