" Darcula Forest
" Requires a truecolor terminal: set termguicolors
set background=dark
highlight clear
if exists('syntax_on')
  syntax reset
endif
let g:colors_name = 'darcula-forest'

" ── Editor UI ────────────────────────────────────────────────────
hi Normal        guifg=#acafa9 guibg=#20231e
hi Cursor        guifg=#20231e guibg=#acafa9
hi CursorLine    guibg=#252722 gui=NONE
hi CursorColumn  guibg=#252722
hi CursorLineNr  guifg=#acafa9 guibg=#252722 gui=NONE
hi LineNr        guifg=#545651
hi SignColumn    guibg=#2c2f2a
hi FoldColumn    guifg=#545651 guibg=#2c2f2a
hi Folded        guifg=#9ca099 guibg=#393c36
hi ColorColumn   guibg=#252722
hi VertSplit     guifg=#343631 guibg=NONE gui=NONE
hi StatusLine    guifg=#acafa9 guibg=#171914 gui=NONE
hi StatusLineNC  guifg=#85976f guibg=#151712 gui=NONE
hi TabLine       guifg=#9ca099 guibg=#171914 gui=NONE
hi TabLineSel    guifg=#acafa9 guibg=#20231e gui=NONE
hi TabLineFill   guibg=#151712 gui=NONE
hi Visual        guibg=#373d32
hi Search        guibg=#393c36
hi IncSearch     guifg=#20231e guibg=#cbad00 gui=NONE
hi MatchParen    guifg=#b89e00 guibg=#3d3b2f
hi Pmenu         guifg=#acafa9 guibg=#252722
hi PmenuSel      guibg=#393c36
hi PmenuSbar     guibg=#252722
hi PmenuThumb    guibg=#343631
hi WildMenu      guibg=#393c36
hi QuickFixLine  guibg=#373d32
hi NonText       guifg=#70736d
hi SpecialKey    guifg=#70736d
hi EndOfBuffer   guifg=#70736d
hi Conceal       guifg=#9ca099
hi Directory     guifg=#8aab5f
hi Title         guifg=#8aab5f gui=bold
hi ErrorMsg      guifg=#bd4238 guibg=NONE
hi WarningMsg    guifg=#cbad00
hi MoreMsg       guifg=#5f7d31
hi ModeMsg       guifg=#acafa9
hi Question      guifg=#5f7d31

" ── Diff ─────────────────────────────────────────────────────────
hi DiffAdd       guibg=#343f28
hi DiffDelete    guifg=#70736d guibg=#393c36
hi DiffChange    guibg=#2a3c4f
hi DiffText      guibg=#2a3c4f gui=bold

" ── Spelling ─────────────────────────────────────────────────────
hi SpellBad      guisp=#584600 gui=undercurl
hi SpellCap      guisp=#365200 gui=undercurl
hi SpellLocal    guisp=#365200 gui=undercurl
hi SpellRare     guisp=#365200 gui=undercurl

" ── Syntax ───────────────────────────────────────────────────────
hi Comment       guifg=#808080 gui=italic
hi Constant      guifg=#749f2b
hi String        guifg=#7e7238
hi Character     guifg=#7e7238
hi Number        guifg=#2a8558
hi Boolean       guifg=#5f7d31
hi Float         guifg=#2a8558
hi Identifier    guifg=#acafa9 gui=NONE
hi Function      guifg=#8aab5f
hi Statement     guifg=#5f7d31 gui=NONE
hi Operator      guifg=#acafa9
hi PreProc       guifg=#7e7238
hi Type          guifg=#acafa9 gui=NONE
hi StorageClass  guifg=#5f7d31
hi Structure     guifg=#5f7d31
hi Special       guifg=#647b47
hi SpecialChar   guifg=#647b47
hi Tag           guifg=#5f7d31
hi Delimiter     guifg=#5f7d31
hi SpecialComment guifg=#6d8c40
hi Debug         guifg=#cbad00
hi Underlined    gui=underline
hi Ignore        guifg=#70736d
hi Error         guifg=#bd4238 guibg=NONE
hi Todo          guifg=#cbad00 guibg=NONE gui=bold
