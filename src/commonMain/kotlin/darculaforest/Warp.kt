package darculaforest

fun Palette.generateWarp() = """
    name: Darcula Forest
    accent: '#${hexOf(keyword)}'
    background: '#${hexOf(editorBg)}'
    foreground: '#${hexOf(fg)}'
    details: darker
    terminal_colors:
      normal:
        black: '#${hexOf(termBlack)}'
        red: '#${hexOf(termRed)}'
        green: '#${hexOf(termGreen)}'
        yellow: '#${hexOf(termYellow)}'
        blue: '#${hexOf(termBlue)}'
        magenta: '#${hexOf(termMagenta)}'
        cyan: '#${hexOf(termCyan)}'
        white: '#${hexOf(termWhite)}'
      bright:
        black: '#${hexOf(termBrightBlack)}'
        red: '#${hexOf(termBrightRed)}'
        green: '#${hexOf(termBrightGreen)}'
        yellow: '#${hexOf(termBrightYellow)}'
        blue: '#${hexOf(termBrightBlue)}'
        magenta: '#${hexOf(termBrightMagenta)}'
        cyan: '#${hexOf(termBrightCyan)}'
        white: '#${hexOf(termBrightWhite)}'
""".trimIndent()
