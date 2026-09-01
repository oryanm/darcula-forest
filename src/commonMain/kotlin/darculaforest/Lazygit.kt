package darculaforest

// Merge into config.yml, or layer: LG_CONFIG_FILE="$HOME/.config/lazygit/config.yml,<this file>".
// selectedLineBgColor also covers range selection since v0.40.
fun Palette.generateLazygit() = """
    # Darcula Forest — lazygit theme
    gui:
      theme:
        # Border of the focused panel
        activeBorderColor:
          - '#${hexOf(keyword)}'
          - bold
        # Border of unfocused panels
        inactiveBorderColor:
          - '#${hexOf(borderColor)}'
        # Border of the focused panel while searching in it
        searchingActiveBorderColor:
          - '#${hexOf(todo)}'
          - bold
        # Keybinding help text in the bottom line
        optionsTextColor:
          - '#${hexOf(textMuted)}'
        # Background of the selected line
        selectedLineBgColor:
          - '#${hexOf(selectionBg)}'
        # Background of the selected line in unfocused views
        inactiveViewSelectedLineBgColor:
          - '#${hexOf(caretRow)}'
        # Copied (cherry-picked) commit
        cherryPickedCommitFgColor:
          - '#${hexOf(constantField)}'
        cherryPickedCommitBgColor:
          - '#${hexOf(infoBg)}'
        # Marked base commit for rebase
        markedBaseCommitFgColor:
          - '#${hexOf(todo)}'
        markedBaseCommitBgColor:
          - '#${hexOf(warningBg)}'
        # Files with unstaged changes
        unstagedChangesColor:
          - '#${hexOf(error)}'
        # Default text color
        defaultFgColor:
          - '#${hexOf(fg)}'
      # Keeps the commits pane on-palette instead of lazygit's per-author colors
      authorColors:
        '*': '#${hexOf(functionDecl)}'
      # Branch name colors by regex (not implicitly anchored)
      branchColorPatterns:
        '^(main|master)$': '#${hexOf(keyword)}'
""".trimIndent()
