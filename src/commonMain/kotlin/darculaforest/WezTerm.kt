package darculaforest

fun Palette.generateWezTerm() = """
    [colors]
    foreground = "${hex(fg)}"
    background = "${hex(editorBg)}"
    cursor_bg = "${hex(keyword)}"
    cursor_fg = "${hex(editorBg)}"
    cursor_border = "${hex(keyword)}"
    selection_bg = "${hex(selectionBg)}"
    selection_fg = "${hex(fg)}"
    split = "${hex(borderColor)}"

    ansi = [
      "${hex(termBlack)}",
      "${hex(termRed)}",
      "${hex(termGreen)}",
      "${hex(termYellow)}",
      "${hex(termBlue)}",
      "${hex(termMagenta)}",
      "${hex(termCyan)}",
      "${hex(termWhite)}",
    ]
    brights = [
      "${hex(termBrightBlack)}",
      "${hex(termBrightRed)}",
      "${hex(termBrightGreen)}",
      "${hex(termBrightYellow)}",
      "${hex(termBrightBlue)}",
      "${hex(termBrightMagenta)}",
      "${hex(termBrightCyan)}",
      "${hex(termBrightWhite)}",
    ]

    [colors.tab_bar]
    background = "${hex(tabBarBg)}"

    [colors.tab_bar.active_tab]
    bg_color = "${hex(editorBg)}"
    fg_color = "${hex(fg)}"

    [colors.tab_bar.inactive_tab]
    bg_color = "${hex(tabBarBg)}"
    fg_color = "${hex(textMuted)}"

    [colors.tab_bar.inactive_tab_hover]
    bg_color = "${hex(elementHover)}"
    fg_color = "${hex(fg)}"

    [colors.tab_bar.new_tab]
    bg_color = "${hex(tabBarBg)}"
    fg_color = "${hex(textMuted)}"

    [colors.tab_bar.new_tab_hover]
    bg_color = "${hex(elementHover)}"
    fg_color = "${hex(fg)}"

    [metadata]
    name = "Darcula Forest"
    author = "Oryan"
""".trimIndent()
