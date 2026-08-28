package darculaforest

fun Palette.generateWindowsTerminal() = """
    {
      "name": "Darcula Forest",
      "background": "#${hexOf(editorBg)}",
      "foreground": "#${hexOf(fg)}",
      "cursorColor": "#${hexOf(keyword)}",
      "selectionBackground": "#${hexOf(selectionBg)}",
      "black": "#${hexOf(termBlack)}",
      "red": "#${hexOf(termRed)}",
      "green": "#${hexOf(termGreen)}",
      "yellow": "#${hexOf(termYellow)}",
      "blue": "#${hexOf(termBlue)}",
      "purple": "#${hexOf(termMagenta)}",
      "cyan": "#${hexOf(termCyan)}",
      "white": "#${hexOf(termWhite)}",
      "brightBlack": "#${hexOf(termBrightBlack)}",
      "brightRed": "#${hexOf(termBrightRed)}",
      "brightGreen": "#${hexOf(termBrightGreen)}",
      "brightYellow": "#${hexOf(termBrightYellow)}",
      "brightBlue": "#${hexOf(termBrightBlue)}",
      "brightPurple": "#${hexOf(termBrightMagenta)}",
      "brightCyan": "#${hexOf(termBrightCyan)}",
      "brightWhite": "#${hexOf(termBrightWhite)}"
    }
""".trimIndent()
