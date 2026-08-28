package darculaforest

fun Palette.generateWezTerm() = """
    [colors]
    foreground = "#${hexOf(fg)}"
    background = "#${hexOf(editorBg)}"
    cursor_bg = "#${hexOf(keyword)}"
    cursor_fg = "#${hexOf(editorBg)}"
    cursor_border = "#${hexOf(keyword)}"
    selection_bg = "#${hexOf(selectionBg)}"
    selection_fg = "#${hexOf(fg)}"
    split = "#${hexOf(borderColor)}"

    ansi = [
      "#${hexOf(termBlack)}",
      "#${hexOf(termRed)}",
      "#${hexOf(termGreen)}",
      "#${hexOf(termYellow)}",
      "#${hexOf(termBlue)}",
      "#${hexOf(termMagenta)}",
      "#${hexOf(termCyan)}",
      "#${hexOf(termWhite)}",
    ]
    brights = [
      "#${hexOf(termBrightBlack)}",
      "#${hexOf(termBrightRed)}",
      "#${hexOf(termBrightGreen)}",
      "#${hexOf(termBrightYellow)}",
      "#${hexOf(termBrightBlue)}",
      "#${hexOf(termBrightMagenta)}",
      "#${hexOf(termBrightCyan)}",
      "#${hexOf(termBrightWhite)}",
    ]

    [colors.tab_bar]
    background = "#${hexOf(tabBarBg)}"

    [colors.tab_bar.active_tab]
    bg_color = "#${hexOf(editorBg)}"
    fg_color = "#${hexOf(fg)}"

    [colors.tab_bar.inactive_tab]
    bg_color = "#${hexOf(tabBarBg)}"
    fg_color = "#${hexOf(textMuted)}"

    [colors.tab_bar.inactive_tab_hover]
    bg_color = "#${hexOf(elementHover)}"
    fg_color = "#${hexOf(fg)}"

    [colors.tab_bar.new_tab]
    bg_color = "#${hexOf(tabBarBg)}"
    fg_color = "#${hexOf(textMuted)}"

    [colors.tab_bar.new_tab_hover]
    bg_color = "#${hexOf(elementHover)}"
    fg_color = "#${hexOf(fg)}"

    [metadata]
    name = "Darcula Forest"
    author = "Oryan"
""".trimIndent()
