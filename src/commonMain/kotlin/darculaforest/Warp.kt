package darculaforest

fun Palette.generateWarp() = """
    name: Darcula Forest
    accent: '${hex(keyword)}'
    background: '${hex(editorBg)}'
    foreground: '${hex(fg)}'
    details: darker
    terminal_colors:
      normal:
        black: '${hex(termBlack)}'
        red: '${hex(termRed)}'
        green: '${hex(termGreen)}'
        yellow: '${hex(termYellow)}'
        blue: '${hex(termBlue)}'
        magenta: '${hex(termMagenta)}'
        cyan: '${hex(termCyan)}'
        white: '${hex(termWhite)}'
      bright:
        black: '${hex(termBrightBlack)}'
        red: '${hex(termBrightRed)}'
        green: '${hex(termBrightGreen)}'
        yellow: '${hex(termBrightYellow)}'
        blue: '${hex(termBrightBlue)}'
        magenta: '${hex(termBrightMagenta)}'
        cyan: '${hex(termBrightCyan)}'
        white: '${hex(termBrightWhite)}'
""".trimIndent()
