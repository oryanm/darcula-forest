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
    hi Normal        guifg=#${hexOf(fg)} guibg=#${hexOf(editorBg)}
    hi Cursor        guifg=#${hexOf(editorBg)} guibg=#${hexOf(fg)}
    hi CursorLine    guibg=#${hexOf(caretRow)} gui=NONE
    hi CursorColumn  guibg=#${hexOf(caretRow)}
    hi CursorLineNr  guifg=#${hexOf(fg)} guibg=#${hexOf(caretRow)} gui=NONE
    hi LineNr        guifg=#${hexOf(lineNumber)}
    hi SignColumn    guibg=#${hexOf(gutterBg)}
    hi FoldColumn    guifg=#${hexOf(lineNumber)} guibg=#${hexOf(gutterBg)}
    hi Folded        guifg=#${hexOf(textMuted)} guibg=#${hexOf(foldedTextBg)}
    hi ColorColumn   guibg=#${hexOf(caretRow)}
    hi VertSplit     guifg=#${hexOf(borderColor)} guibg=NONE gui=NONE
    hi StatusLine    guifg=#${hexOf(fg)} guibg=#${hexOf(tabBarBg)} gui=NONE
    hi StatusLineNC  guifg=#${hexOf(fgMuted)} guibg=#${hexOf(pageBg)} gui=NONE
    hi TabLine       guifg=#${hexOf(textMuted)} guibg=#${hexOf(tabBarBg)} gui=NONE
    hi TabLineSel    guifg=#${hexOf(fg)} guibg=#${hexOf(editorBg)} gui=NONE
    hi TabLineFill   guibg=#${hexOf(pageBg)} gui=NONE
    hi Visual        guibg=#${hexOf(selectionBg)}
    hi Search        guibg=#${hexOf(searchResultBg)}
    hi IncSearch     guifg=#${hexOf(editorBg)} guibg=#${hexOf(todo)} gui=NONE
    hi MatchParen    guifg=#${hexOf(matchingBraceFg)} guibg=#${hexOf(matchingBraceBg)}
    hi Pmenu         guifg=#${hexOf(fg)} guibg=#${hexOf(panelBg)}
    hi PmenuSel      guibg=#${hexOf(elementActive)}
    hi PmenuSbar     guibg=#${hexOf(scrollbarTrack)}
    hi PmenuThumb    guibg=#${hexOf(scrollbarThumb)}
    hi WildMenu      guibg=#${hexOf(elementActive)}
    hi QuickFixLine  guibg=#${hexOf(selectionBg)}
    hi NonText       guifg=#${hexOf(textPlaceholder)}
    hi SpecialKey    guifg=#${hexOf(textPlaceholder)}
    hi EndOfBuffer   guifg=#${hexOf(textPlaceholder)}
    hi Conceal       guifg=#${hexOf(textMuted)}
    hi Directory     guifg=#${hexOf(functionDecl)}
    hi Title         guifg=#${hexOf(functionDecl)} gui=bold
    hi ErrorMsg      guifg=#${hexOf(error)} guibg=NONE
    hi WarningMsg    guifg=#${hexOf(todo)}
    hi MoreMsg       guifg=#${hexOf(keyword)}
    hi ModeMsg       guifg=#${hexOf(fg)}
    hi Question      guifg=#${hexOf(keyword)}

    " ── Diff ─────────────────────────────────────────────────────────
    hi DiffAdd       guibg=#${hexOf(diffAdd)}
    hi DiffDelete    guifg=#${hexOf(textPlaceholder)} guibg=#${hexOf(diffDelete)}
    hi DiffChange    guibg=#${hexOf(diffChange)}
    hi DiffText      guibg=#${hexOf(diffChange)} gui=bold

    " ── Spelling ─────────────────────────────────────────────────────
    hi SpellBad      guisp=#${hexOf(typoUnderline)} gui=undercurl
    hi SpellCap      guisp=#${hexOf(mutableUnderline)} gui=undercurl
    hi SpellLocal    guisp=#${hexOf(mutableUnderline)} gui=undercurl
    hi SpellRare     guisp=#${hexOf(mutableUnderline)} gui=undercurl

    " ── Syntax ───────────────────────────────────────────────────────
    hi Comment       guifg=#${hexOf(comment)} gui=italic
    hi Constant      guifg=#${hexOf(constantField)}
    hi String        guifg=#${hexOf(string)}
    hi Character     guifg=#${hexOf(string)}
    hi Number        guifg=#${hexOf(number)}
    hi Boolean       guifg=#${hexOf(keyword)}
    hi Float         guifg=#${hexOf(number)}
    hi Identifier    guifg=#${hexOf(fg)} gui=NONE
    hi Function      guifg=#${hexOf(functionDecl)}
    hi Statement     guifg=#${hexOf(keyword)} gui=NONE
    hi Operator      guifg=#${hexOf(fg)}
    hi PreProc       guifg=#${hexOf(annotation)}
    hi Type          guifg=#${hexOf(fg)} gui=NONE
    hi StorageClass  guifg=#${hexOf(keyword)}
    hi Structure     guifg=#${hexOf(keyword)}
    hi Special       guifg=#${hexOf(stringEscape)}
    hi SpecialChar   guifg=#${hexOf(stringEscape)}
    hi Tag           guifg=#${hexOf(keyword)}
    hi Delimiter     guifg=#${hexOf(keyword)}
    hi SpecialComment guifg=#${hexOf(javadoc)}
    hi Debug         guifg=#${hexOf(todo)}
    hi Underlined    gui=underline
    hi Ignore        guifg=#${hexOf(textPlaceholder)}
    hi Error         guifg=#${hexOf(error)} guibg=NONE
    hi Todo          guifg=#${hexOf(todo)} guibg=NONE gui=bold
""".trimIndent()
