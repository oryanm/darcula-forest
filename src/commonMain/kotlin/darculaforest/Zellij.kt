package darculaforest

fun Palette.generateZellij() = """
    themes {
        darcula-forest {
            fg "${hex(fg)}"
            bg "${hex(editorBg)}"
            black "${hex(termBlack)}"
            red "${hex(termRed)}"
            green "${hex(termGreen)}"
            yellow "${hex(termYellow)}"
            blue "${hex(termBlue)}"
            magenta "${hex(termMagenta)}"
            cyan "${hex(termCyan)}"
            white "${hex(termWhite)}"
            orange "${hex(todo)}"
        }
    }
""".trimIndent()
