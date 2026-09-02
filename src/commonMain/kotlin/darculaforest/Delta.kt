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
        commit-style = bold "${hex(todo)}"
        commit-decoration-style = "${hex(borderColor)}" box ul

        # file header
        file-style = bold "${hex(functionDecl)}"
        file-decoration-style = "${hex(borderColor)}" ul

        # hunk headers
        hunk-header-style = file line-number syntax
        hunk-header-decoration-style = "${hex(borderVariant)}" box
        hunk-header-file-style = "${hex(textMuted)}"
        hunk-header-line-number-style = bold "${hex(functionDecl)}"

        # diff lines
        minus-style = syntax "${hex(diffDelete)}"
        minus-non-emph-style = syntax "${hex(diffDelete)}"
        minus-emph-style = "${hex(editorBg)}" "${hex(diffDeleteStripe)}" bold
        plus-style = syntax "${hex(diffAdd)}"
        plus-non-emph-style = syntax "${hex(diffAdd)}"
        plus-emph-style = "${hex(editorBg)}" "${hex(diffAddStripe)}" bold
        zero-style = syntax
        whitespace-error-style = "${hex(error)}" reverse

        # line numbers
        line-numbers = true
        line-numbers-left-format = "{nm:>4} │"
        line-numbers-right-format = "{np:>4} │ "
        line-numbers-left-style = "${hex(lineNumber)}"
        line-numbers-right-style = "${hex(lineNumber)}"
        line-numbers-minus-style = "${hex(diffDeleteStripe)}"
        line-numbers-plus-style = "${hex(diffAddStripe)}"
        line-numbers-zero-style = "${hex(lineNumber)}"

        # merge conflicts
        merge-conflict-ours-diff-header-style = bold "${hex(constantField)}"
        merge-conflict-ours-diff-header-decoration-style = "${hex(borderColor)}" box
        merge-conflict-theirs-diff-header-style = bold "${hex(todo)}"
        merge-conflict-theirs-diff-header-decoration-style = "${hex(borderColor)}" box

        # git blame
        blame-code-style = syntax
        blame-palette = "${hex(editorBg)}" "${hex(caretRow)}" "${hex(gutterBg)}" "${hex(tabBarBg)}" "${hex(pageBg)}"
        blame-separator-style = "${hex(borderVariant)}"

        # grep (delta as a pager for rg/git grep)
        grep-file-style = bold "${hex(functionDecl)}"
        grep-line-number-style = "${hex(lineNumber)}"
        grep-match-line-style = syntax "${hex(searchResultBg)}"
        grep-match-word-style = "${hex(editorBg)}" "${hex(todo)}" bold
        grep-context-line-style = syntax

        # inline hints (wrap markers etc.)
        inline-hint-style = "${hex(textMuted)}"
""".trimIndent()
