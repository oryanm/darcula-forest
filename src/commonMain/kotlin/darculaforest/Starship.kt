package darculaforest

// `palette` is a top-level key: it must sit above the first [section] header when merged.
// Requires starship >= v1.11.0. Starship says 'purple', not 'magenta'; entries can't reference each other.
fun Palette.generateStarship() = $$"""
    # Darcula Forest — Starship palette
    # Merge into ~/.config/starship.toml; keep `palette = ...` above the first [section].
    palette = "darcula_forest"

    [palettes.darcula_forest]
    # The 16 standard names starship's style parser recognizes
    black = "$${hex(termBlack)}"
    red = "$${hex(termRed)}"
    green = "$${hex(termGreen)}"
    yellow = "$${hex(termYellow)}"
    blue = "$${hex(termBlue)}"
    purple = "$${hex(termMagenta)}"
    cyan = "$${hex(termCyan)}"
    white = "$${hex(termWhite)}"
    bright-black = "$${hex(termBrightBlack)}"
    bright-red = "$${hex(termBrightRed)}"
    bright-green = "$${hex(termBrightGreen)}"
    bright-yellow = "$${hex(termBrightYellow)}"
    bright-blue = "$${hex(termBrightBlue)}"
    bright-purple = "$${hex(termBrightMagenta)}"
    bright-cyan = "$${hex(termBrightCyan)}"
    bright-white = "$${hex(termBrightWhite)}"

    # Custom semantic names (usable in any style string)
    bg = "$${hex(editorBg)}"
    surface = "$${hex(panelBg)}"
    selection = "$${hex(selectionBg)}"
    border = "$${hex(borderColor)}"
    fg = "$${hex(fg)}"
    muted = "$${hex(textMuted)}"
    accent = "$${hex(keyword)}"
    accent_bright = "$${hex(functionDecl)}"
    info = "$${hex(constantField)}"
    value = "$${hex(string)}"
    success = "$${hex(diffAddStripe)}"
    error = "$${hex(error)}"
    warning = "$${hex(todo)}"
    added = "$${hex(diffAddStripe)}"
    deleted = "$${hex(diffDeleteStripe)}"
    changed = "$${hex(diffChangeStripe)}"
    conflicted = "$${hex(diffConflictStripe)}"

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
