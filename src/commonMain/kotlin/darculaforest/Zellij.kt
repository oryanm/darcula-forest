package darculaforest

fun Palette.generateZellij() = """
    themes {
        darcula-forest {
            fg "#${hexOf(fg)}"
            bg "#${hexOf(editorBg)}"
            black "#${hexOf(termBlack)}"
            red "#${hexOf(termRed)}"
            green "#${hexOf(termGreen)}"
            yellow "#${hexOf(termYellow)}"
            blue "#${hexOf(termBlue)}"
            magenta "#${hexOf(termMagenta)}"
            cyan "#${hexOf(termCyan)}"
            white "#${hexOf(termWhite)}"
            orange "#${hexOf(todo)}"
        }
    }
""".trimIndent()
