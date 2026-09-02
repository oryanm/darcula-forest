package darculaforest

import darculaforest.Expr.Var

// Lua colorscheme for Neovim (>= 0.10 capture names: @markup.*, @module).
// Classic groups first, then treesitter captures mirroring the Helix/Zed mapping,
// then diagnostics and float UI. Treesitter captures not set here fall back to
// their default links into the classic groups.

private sealed interface NvimEntry
private data class NvimSection(val title: String) : NvimEntry
private data class Hl(
    val group: String,
    val fg: Var? = null,
    val bg: Var? = null,
    val sp: Var? = null,
    val bold: Boolean = false,
    val italic: Boolean = false,
    val underline: Boolean = false,
    val undercurl: Boolean = false,
    val strikethrough: Boolean = false,
) : NvimEntry

private val Palette.nvimHighlights: List<NvimEntry> get() = listOf(
    NvimSection("Editor UI"),
    Hl("Normal",       fg = fg, bg = editorBg),
    Hl("NormalFloat",  fg = fg, bg = panelBg),
    Hl("FloatBorder",  fg = borderColor, bg = panelBg),
    Hl("FloatTitle",   fg = functionDecl, bg = panelBg, bold = true),
    Hl("Cursor",       fg = editorBg, bg = fg),
    Hl("CursorLine",   bg = caretRow),
    Hl("CursorColumn", bg = caretRow),
    Hl("CursorLineNr", fg = fg, bg = caretRow),
    Hl("LineNr",       fg = lineNumber),
    Hl("SignColumn",   bg = gutterBg),
    Hl("FoldColumn",   fg = lineNumber, bg = gutterBg),
    Hl("Folded",       fg = textMuted, bg = foldedTextBg),
    Hl("ColorColumn",  bg = caretRow),
    Hl("WinSeparator", fg = borderColor),
    Hl("WinBar",       fg = fg, bg = editorBg),
    Hl("WinBarNC",     fg = fgMuted, bg = editorBg),
    Hl("StatusLine",   fg = fg, bg = tabBarBg),
    Hl("StatusLineNC", fg = fgMuted, bg = pageBg),
    Hl("TabLine",      fg = textMuted, bg = tabBarBg),
    Hl("TabLineSel",   fg = fg, bg = editorBg),
    Hl("TabLineFill",  bg = pageBg),
    Hl("Visual",       bg = selectionBg),
    Hl("Search",       bg = searchResultBg),
    Hl("IncSearch",    fg = editorBg, bg = todo),
    Hl("CurSearch",    fg = editorBg, bg = todo),
    Hl("MatchParen",   fg = matchingBraceFg, bg = matchingBraceBg),
    Hl("Pmenu",        fg = fg, bg = panelBg),
    Hl("PmenuSel",     bg = elementActive),
    Hl("PmenuSbar",    bg = scrollbarTrack),
    Hl("PmenuThumb",   bg = scrollbarThumb),
    Hl("WildMenu",     bg = elementActive),
    Hl("QuickFixLine", bg = selectionBg),
    Hl("NonText",      fg = textPlaceholder),
    Hl("SpecialKey",   fg = textPlaceholder),
    Hl("Whitespace",   fg = textPlaceholder),
    Hl("EndOfBuffer",  fg = textPlaceholder),
    Hl("Conceal",      fg = textMuted),
    Hl("Directory",    fg = functionDecl),
    Hl("Title",        fg = functionDecl, bold = true),
    Hl("ErrorMsg",     fg = error),
    Hl("WarningMsg",   fg = todo),
    Hl("MoreMsg",      fg = keyword),
    Hl("ModeMsg",      fg = fg),
    Hl("Question",     fg = keyword),

    NvimSection("Diff"),
    Hl("DiffAdd",    bg = diffAdd),
    Hl("DiffDelete", fg = textPlaceholder, bg = diffDelete),
    Hl("DiffChange", bg = diffChange),
    Hl("DiffText",   bg = diffChange, bold = true),
    Hl("Added",      fg = diffAddStripe),
    Hl("Removed",    fg = diffDeleteStripe),
    Hl("Changed",    fg = diffChangeStripe),

    NvimSection("Spelling"),
    Hl("SpellBad",   sp = typoUnderline, undercurl = true),
    Hl("SpellCap",   sp = mutableUnderline, undercurl = true),
    Hl("SpellLocal", sp = mutableUnderline, undercurl = true),
    Hl("SpellRare",  sp = mutableUnderline, undercurl = true),

    NvimSection("Classic syntax (fallbacks for non-treesitter buffers)"),
    Hl("Comment",        fg = comment, italic = true),
    Hl("Constant",       fg = constantField),
    Hl("String",         fg = string),
    Hl("Character",      fg = string),
    Hl("Number",         fg = number),
    Hl("Boolean",        fg = keyword),
    Hl("Float",          fg = number),
    Hl("Identifier",     fg = fg),
    Hl("Function",       fg = functionDecl),
    Hl("Statement",      fg = keyword),
    Hl("Operator",       fg = operator),
    Hl("PreProc",        fg = annotation),
    Hl("Type",           fg = `class`),
    Hl("StorageClass",   fg = keyword),
    Hl("Structure",      fg = keyword),
    Hl("Special",        fg = stringEscape),
    Hl("SpecialChar",    fg = stringEscape),
    Hl("Tag",            fg = keyword),
    Hl("Delimiter",      fg = punctuation),
    Hl("SpecialComment", fg = javadoc),
    Hl("Debug",          fg = todo),
    Hl("Underlined",     underline = true),
    Hl("Ignore",         fg = textPlaceholder),
    Hl("Error",          fg = error),
    Hl("Todo",           fg = todo, bold = true),

    NvimSection("Treesitter captures (mirrors the Helix/Zed mapping)"),
    Hl("@attribute",             fg = annotationNamedAtt),
    Hl("@type",                  fg = `class`),
    Hl("@type.builtin",          fg = `class`),
    Hl("@constructor",           fg = functionDecl),
    Hl("@constant",              fg = constantField, italic = true),
    Hl("@constant.builtin",      fg = keyword),
    Hl("@number",                fg = number),
    Hl("@string",                fg = string),
    Hl("@string.escape",         fg = stringEscape),
    Hl("@string.regexp",         fg = stringEscape),
    Hl("@string.special",        fg = stringEscape),
    Hl("@comment",               fg = comment, italic = true),
    Hl("@comment.documentation", fg = javadoc, italic = true),
    Hl("@comment.todo",          fg = todo, bold = true),
    Hl("@variable",              fg = localVar),
    Hl("@variable.builtin",      fg = implicitParam, italic = true),
    Hl("@variable.parameter",    fg = parameter),
    Hl("@variable.member",       fg = fg),
    Hl("@label",                 fg = number),
    Hl("@punctuation.delimiter", fg = punctuation),
    Hl("@punctuation.bracket",   fg = parens),
    Hl("@punctuation.special",   fg = stringEscape),
    Hl("@keyword",               fg = keyword),
    Hl("@keyword.directive",     fg = staticFunc),
    Hl("@operator",              fg = operator),
    Hl("@function",              fg = functionDecl),
    Hl("@function.builtin",      fg = staticFunc),
    Hl("@function.macro",        fg = staticFunc),
    Hl("@tag",                   fg = keyword),
    Hl("@module",                fg = fg),
    Hl("@markup.heading",        fg = functionDecl, bold = true),
    Hl("@markup.list",           fg = keyword),
    Hl("@markup.bold",           bold = true),
    Hl("@markup.italic",         italic = true),
    Hl("@markup.strikethrough",  strikethrough = true),
    Hl("@markup.link.url",       fg = keyword, underline = true),
    Hl("@markup.link.label",     fg = namedArg),
    Hl("@markup.quote",          fg = comment, italic = true),
    Hl("@markup.raw",            fg = string),
    Hl("@diff.plus",             fg = diffAddStripe),
    Hl("@diff.minus",            fg = diffDeleteStripe),
    Hl("@diff.delta",            fg = diffChangeStripe),

    NvimSection("Diagnostics"),
    Hl("DiagnosticError",          fg = error),
    Hl("DiagnosticWarn",           fg = todo),
    Hl("DiagnosticInfo",           fg = diffChangeStripe),
    Hl("DiagnosticHint",           fg = textMuted),
    Hl("DiagnosticUnderlineError", sp = error, undercurl = true),
    Hl("DiagnosticUnderlineWarn",  sp = todo, undercurl = true),
    Hl("DiagnosticUnderlineInfo",  sp = diffChangeStripe, undercurl = true),
    Hl("DiagnosticUnderlineHint",  sp = textMuted, undercurl = true),
    Hl("LspInlayHint",             fg = textMuted, italic = true),
    Hl("LspReferenceText",         bg = elementHover),
    Hl("LspReferenceRead",         bg = elementHover),
    Hl("LspReferenceWrite",        bg = elementHover),
)

private fun luaOpts(h: Hl) = buildList {
    h.fg?.let { add("fg = '${hex(it)}'") }
    h.bg?.let { add("bg = '${hex(it)}'") }
    h.sp?.let { add("sp = '${hex(it)}'") }
    if (h.bold) add("bold = true")
    if (h.italic) add("italic = true")
    if (h.underline) add("underline = true")
    if (h.undercurl) add("undercurl = true")
    if (h.strikethrough) add("strikethrough = true")
}.joinToString(", ")

fun Palette.generateNeovim(): String = buildString {
    appendLine("""
        -- Darcula Forest
        vim.cmd.highlight('clear')
        if vim.fn.exists('syntax_on') == 1 then
          vim.cmd.syntax('reset')
        end
        vim.g.colors_name = 'darcula-forest'
        vim.o.background = 'dark'
        vim.o.termguicolors = true

        local function hl(group, opts)
          vim.api.nvim_set_hl(0, group, opts)
        end
    """.trimIndent())
    val width = nvimHighlights.filterIsInstance<Hl>().maxOf { it.group.length } + 3
    for (entry in nvimHighlights) when (entry) {
        is NvimSection -> {
            appendLine()
            appendLine("-- ── ${entry.title} ".padEnd(69, '─'))
        }
        is Hl -> appendLine("hl(${"'${entry.group}',".padEnd(width)} { ${luaOpts(entry)} })")
    }
}.trimEnd()
