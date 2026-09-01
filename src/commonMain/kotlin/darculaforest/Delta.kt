package darculaforest

// Hex colors must be double-quoted in gitconfig — an unquoted # starts a comment.
// Style strings: first color = fg, second = bg; `syntax` keeps delta's syntax highlighting as fg.
fun Palette.generateDelta() = """
    # Darcula Forest — theme for delta (https://github.com/dandavison/delta)
    #
    # Usage, in ~/.gitconfig:
    #   [include]
    #       path = /path/to/darcula-forest.gitconfig
    #   [core]
    #       pager = delta
    #   [interactive]
    #       diffFilter = delta --color-only
    #   [delta]
    #       features = darcula-forest

    [delta "darcula-forest"]
        dark = true
        true-color = auto
        syntax-theme = ansi

        # commit line
        commit-style = bold "#${hexOf(todo)}"
        commit-decoration-style = "#${hexOf(borderColor)}" box ul

        # file header
        file-style = bold "#${hexOf(functionDecl)}"
        file-decoration-style = "#${hexOf(borderColor)}" ul

        # hunk headers
        hunk-header-style = file line-number syntax
        hunk-header-decoration-style = "#${hexOf(borderVariant)}" box
        hunk-header-file-style = "#${hexOf(textMuted)}"
        hunk-header-line-number-style = bold "#${hexOf(functionDecl)}"

        # diff lines
        minus-style = syntax "#${hexOf(diffDelete)}"
        minus-non-emph-style = syntax "#${hexOf(diffDelete)}"
        minus-emph-style = "#${hexOf(editorBg)}" "#${hexOf(diffDeleteStripe)}" bold
        plus-style = syntax "#${hexOf(diffAdd)}"
        plus-non-emph-style = syntax "#${hexOf(diffAdd)}"
        plus-emph-style = "#${hexOf(editorBg)}" "#${hexOf(diffAddStripe)}" bold
        zero-style = syntax
        whitespace-error-style = "#${hexOf(error)}" reverse

        # line numbers
        line-numbers = true
        line-numbers-left-format = "{nm:>4} │"
        line-numbers-right-format = "{np:>4} │ "
        line-numbers-left-style = "#${hexOf(lineNumber)}"
        line-numbers-right-style = "#${hexOf(lineNumber)}"
        line-numbers-minus-style = "#${hexOf(diffDeleteStripe)}"
        line-numbers-plus-style = "#${hexOf(diffAddStripe)}"
        line-numbers-zero-style = "#${hexOf(lineNumber)}"

        # merge conflicts
        merge-conflict-ours-diff-header-style = bold "#${hexOf(constantField)}"
        merge-conflict-ours-diff-header-decoration-style = "#${hexOf(borderColor)}" box
        merge-conflict-theirs-diff-header-style = bold "#${hexOf(todo)}"
        merge-conflict-theirs-diff-header-decoration-style = "#${hexOf(borderColor)}" box

        # git blame
        blame-code-style = syntax
        blame-palette = "#${hexOf(editorBg)}" "#${hexOf(caretRow)}" "#${hexOf(gutterBg)}" "#${hexOf(tabBarBg)}" "#${hexOf(pageBg)}"
        blame-separator-style = "#${hexOf(borderVariant)}"

        # grep (delta as a pager for rg/git grep)
        grep-file-style = bold "#${hexOf(functionDecl)}"
        grep-line-number-style = "#${hexOf(lineNumber)}"
        grep-match-line-style = syntax "#${hexOf(searchResultBg)}"
        grep-match-word-style = "#${hexOf(editorBg)}" "#${hexOf(todo)}" bold
        grep-context-line-style = syntax

        # inline hints (wrap markers etc.)
        inline-hint-style = "#${hexOf(textMuted)}"
""".trimIndent()
