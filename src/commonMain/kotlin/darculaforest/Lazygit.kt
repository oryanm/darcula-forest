package darculaforest

// Merge into config.yml, or layer: LG_CONFIG_FILE="$HOME/.config/lazygit/config.yml,<this file>".
// selectedLineBgColor also covers range selection since v0.40.
fun Palette.generateLazygit() = """
    # Darcula Forest — lazygit theme
    gui:
      theme:
        # Border of the focused panel
        activeBorderColor:
          - '${hex(keyword)}'
          - bold
        # Border of unfocused panels
        inactiveBorderColor:
          - '${hex(borderColor)}'
        # Border of the focused panel while searching in it
        searchingActiveBorderColor:
          - '${hex(todo)}'
          - bold
        # Keybinding help text in the bottom line
        optionsTextColor:
          - '${hex(textMuted)}'
        # Background of the selected line
        selectedLineBgColor:
          - '${hex(selectionBg)}'
        # Background of the selected line in unfocused views
        inactiveViewSelectedLineBgColor:
          - '${hex(caretRow)}'
        # Copied (cherry-picked) commit
        cherryPickedCommitFgColor:
          - '${hex(constantField)}'
        cherryPickedCommitBgColor:
          - '${hex(infoBg)}'
        # Marked base commit for rebase
        markedBaseCommitFgColor:
          - '${hex(todo)}'
        markedBaseCommitBgColor:
          - '${hex(warningBg)}'
        # Files with unstaged changes
        unstagedChangesColor:
          - '${hex(error)}'
        # Default text color
        defaultFgColor:
          - '${hex(fg)}'
      # Keeps the commits pane on-palette instead of lazygit's per-author colors
      authorColors:
        '*': '${hex(functionDecl)}'
      # Branch name colors by regex (not implicitly anchored)
      branchColorPatterns:
        '^(main|master)$': '${hex(keyword)}'
""".trimIndent()
