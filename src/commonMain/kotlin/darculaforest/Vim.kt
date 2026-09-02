package darculaforest

fun Palette.generateVim() = """
    " Darcula Forest
    " Requires a truecolor terminal: set termguicolors
    set background=dark
    highlight clear
    if exists('syntax_on')
      syntax reset
    endif
    let g:colors_name = 'darcula-forest'

    " ── Editor UI ────────────────────────────────────────────────────
    hi Normal        guifg=${hex(fg)} guibg=${hex(editorBg)}
    hi Cursor        guifg=${hex(editorBg)} guibg=${hex(fg)}
    hi CursorLine    guibg=${hex(caretRow)} gui=NONE
    hi CursorColumn  guibg=${hex(caretRow)}
    hi CursorLineNr  guifg=${hex(fg)} guibg=${hex(caretRow)} gui=NONE
    hi LineNr        guifg=${hex(lineNumber)}
    hi SignColumn    guibg=${hex(gutterBg)}
    hi FoldColumn    guifg=${hex(lineNumber)} guibg=${hex(gutterBg)}
    hi Folded        guifg=${hex(textMuted)} guibg=${hex(foldedTextBg)}
    hi ColorColumn   guibg=${hex(caretRow)}
    hi VertSplit     guifg=${hex(borderColor)} guibg=NONE gui=NONE
    hi StatusLine    guifg=${hex(fg)} guibg=${hex(tabBarBg)} gui=NONE
    hi StatusLineNC  guifg=${hex(fgMuted)} guibg=${hex(pageBg)} gui=NONE
    hi TabLine       guifg=${hex(textMuted)} guibg=${hex(tabBarBg)} gui=NONE
    hi TabLineSel    guifg=${hex(fg)} guibg=${hex(editorBg)} gui=NONE
    hi TabLineFill   guibg=${hex(pageBg)} gui=NONE
    hi Visual        guibg=${hex(selectionBg)}
    hi Search        guibg=${hex(searchResultBg)}
    hi IncSearch     guifg=${hex(editorBg)} guibg=${hex(todo)} gui=NONE
    hi MatchParen    guifg=${hex(matchingBraceFg)} guibg=${hex(matchingBraceBg)}
    hi Pmenu         guifg=${hex(fg)} guibg=${hex(panelBg)}
    hi PmenuSel      guibg=${hex(elementActive)}
    hi PmenuSbar     guibg=${hex(scrollbarTrack)}
    hi PmenuThumb    guibg=${hex(scrollbarThumb)}
    hi WildMenu      guibg=${hex(elementActive)}
    hi QuickFixLine  guibg=${hex(selectionBg)}
    hi NonText       guifg=${hex(textPlaceholder)}
    hi SpecialKey    guifg=${hex(textPlaceholder)}
    hi EndOfBuffer   guifg=${hex(textPlaceholder)}
    hi Conceal       guifg=${hex(textMuted)}
    hi Directory     guifg=${hex(functionDecl)}
    hi Title         guifg=${hex(functionDecl)} gui=bold
    hi ErrorMsg      guifg=${hex(error)} guibg=NONE
    hi WarningMsg    guifg=${hex(todo)}
    hi MoreMsg       guifg=${hex(keyword)}
    hi ModeMsg       guifg=${hex(fg)}
    hi Question      guifg=${hex(keyword)}

    " ── Diff ─────────────────────────────────────────────────────────
    hi DiffAdd       guibg=${hex(diffAdd)}
    hi DiffDelete    guifg=${hex(textPlaceholder)} guibg=${hex(diffDelete)}
    hi DiffChange    guibg=${hex(diffChange)}
    hi DiffText      guibg=${hex(diffChange)} gui=bold

    " ── Spelling ─────────────────────────────────────────────────────
    hi SpellBad      guisp=${hex(typoUnderline)} gui=undercurl
    hi SpellCap      guisp=${hex(mutableUnderline)} gui=undercurl
    hi SpellLocal    guisp=${hex(mutableUnderline)} gui=undercurl
    hi SpellRare     guisp=${hex(mutableUnderline)} gui=undercurl

    " ── Syntax ───────────────────────────────────────────────────────
    hi Comment       guifg=${hex(comment)} gui=italic
    hi Constant      guifg=${hex(constantField)}
    hi String        guifg=${hex(string)}
    hi Character     guifg=${hex(string)}
    hi Number        guifg=${hex(number)}
    hi Boolean       guifg=${hex(keyword)}
    hi Float         guifg=${hex(number)}
    hi Identifier    guifg=${hex(fg)} gui=NONE
    hi Function      guifg=${hex(functionDecl)}
    hi Statement     guifg=${hex(keyword)} gui=NONE
    hi Operator      guifg=${hex(fg)}
    hi PreProc       guifg=${hex(annotation)}
    hi Type          guifg=${hex(fg)} gui=NONE
    hi StorageClass  guifg=${hex(keyword)}
    hi Structure     guifg=${hex(keyword)}
    hi Special       guifg=${hex(stringEscape)}
    hi SpecialChar   guifg=${hex(stringEscape)}
    hi Tag           guifg=${hex(keyword)}
    hi Delimiter     guifg=${hex(keyword)}
    hi SpecialComment guifg=${hex(javadoc)}
    hi Debug         guifg=${hex(todo)}
    hi Underlined    gui=underline
    hi Ignore        guifg=${hex(textPlaceholder)}
    hi Error         guifg=${hex(error)} guibg=NONE
    hi Todo          guifg=${hex(todo)} guibg=NONE gui=bold
""".trimIndent()
