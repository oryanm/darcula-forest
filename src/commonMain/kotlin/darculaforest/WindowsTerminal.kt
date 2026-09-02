package darculaforest

fun Palette.generateWindowsTerminal() = """
    {
      "name": "Darcula Forest",
      "background": "${hex(editorBg)}",
      "foreground": "${hex(fg)}",
      "cursorColor": "${hex(keyword)}",
      "selectionBackground": "${hex(selectionBg)}",
      "black": "${hex(termBlack)}",
      "red": "${hex(termRed)}",
      "green": "${hex(termGreen)}",
      "yellow": "${hex(termYellow)}",
      "blue": "${hex(termBlue)}",
      "purple": "${hex(termMagenta)}",
      "cyan": "${hex(termCyan)}",
      "white": "${hex(termWhite)}",
      "brightBlack": "${hex(termBrightBlack)}",
      "brightRed": "${hex(termBrightRed)}",
      "brightGreen": "${hex(termBrightGreen)}",
      "brightYellow": "${hex(termBrightYellow)}",
      "brightBlue": "${hex(termBrightBlue)}",
      "brightPurple": "${hex(termBrightMagenta)}",
      "brightCyan": "${hex(termBrightCyan)}",
      "brightWhite": "${hex(termBrightWhite)}"
    }
""".trimIndent()
