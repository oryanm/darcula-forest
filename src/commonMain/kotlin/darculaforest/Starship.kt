package darculaforest

// `palette` is a top-level key: it must sit above the first [section] header when merged.
// Requires starship >= v1.11.0. Starship says 'purple', not 'magenta'; entries can't reference each other.
fun Palette.generateStarship() = $$"""
    # Darcula Forest — Starship palette
    # Merge into ~/.config/starship.toml; keep `palette = ...` above the first [section].
    palette = "darcula_forest"

    [palettes.darcula_forest]
    # The 16 standard names starship's style parser recognizes
    black = "#$${hexOf(termBlack)}"
    red = "#$${hexOf(termRed)}"
    green = "#$${hexOf(termGreen)}"
    yellow = "#$${hexOf(termYellow)}"
    blue = "#$${hexOf(termBlue)}"
    purple = "#$${hexOf(termMagenta)}"
    cyan = "#$${hexOf(termCyan)}"
    white = "#$${hexOf(termWhite)}"
    bright-black = "#$${hexOf(termBrightBlack)}"
    bright-red = "#$${hexOf(termBrightRed)}"
    bright-green = "#$${hexOf(termBrightGreen)}"
    bright-yellow = "#$${hexOf(termBrightYellow)}"
    bright-blue = "#$${hexOf(termBrightBlue)}"
    bright-purple = "#$${hexOf(termBrightMagenta)}"
    bright-cyan = "#$${hexOf(termBrightCyan)}"
    bright-white = "#$${hexOf(termBrightWhite)}"

    # Custom semantic names (usable in any style string)
    bg = "#$${hexOf(editorBg)}"
    surface = "#$${hexOf(panelBg)}"
    selection = "#$${hexOf(selectionBg)}"
    border = "#$${hexOf(borderColor)}"
    fg = "#$${hexOf(fg)}"
    muted = "#$${hexOf(textMuted)}"
    accent = "#$${hexOf(keyword)}"
    accent_bright = "#$${hexOf(functionDecl)}"
    info = "#$${hexOf(constantField)}"
    value = "#$${hexOf(string)}"
    success = "#$${hexOf(diffAddStripe)}"
    error = "#$${hexOf(error)}"
    warning = "#$${hexOf(todo)}"
    added = "#$${hexOf(diffAddStripe)}"
    deleted = "#$${hexOf(diffDeleteStripe)}"
    changed = "#$${hexOf(diffChangeStripe)}"
    conflicted = "#$${hexOf(diffConflictStripe)}"

    # Optional module styles wired to the palette (safe to drop or adapt)
    [character]
    success_symbol = "[❯](bold accent)"
    error_symbol = "[❯](bold error)"

    [directory]
    style = "bold accent"

    [git_branch]
    style = "bold accent_bright"

    [git_status]
    style = "bold changed"
    conflicted = "[=$count](bold conflicted)"
    deleted = "[✘$count](bold deleted)"
    staged = "[+$count](bold added)"
    modified = "[!$count](bold changed)"

    [cmd_duration]
    style = "muted"

    [status]
    disabled = false
    style = "bold error"

    [jobs]
    style = "bold warning"
""".trimIndent()
