package darculaforest

// Syntax scopes mirror the Zed mapping (Zed.kt) so the tone matches across editors.
// [palette] must be the LAST section; entries use the vars' canonical kebab-case names.
private val HELIX_BODY = """
    # Darcula Forest — Helix theme
    # Install: ~/.config/helix/themes/darcula-forest.toml, then `:theme darcula-forest`

    # ── Syntax ──────────────────────────────────────────────────────────
    "attribute" = "named-arg"
    "type" = "fg"
    "type.builtin" = "fg"
    "type.enum.variant" = "constant-field"
    "constructor" = "function-decl"
    "constant" = { fg = "constant-field", modifiers = ["italic"] }
    "constant.builtin" = "keyword"
    "constant.numeric" = "number"
    "constant.character.escape" = "string-escape"
    "string" = "string"
    "string.regexp" = "string-escape"
    "string.special" = "string-escape"
    "string.special.symbol" = "string-escape"
    "comment" = { fg = "comment", modifiers = ["italic"] }
    "comment.block.documentation" = { fg = "javadoc", modifiers = ["italic"] }
    "variable" = "fg"
    "variable.builtin" = { fg = "implicit-param", modifiers = ["italic"] }
    "variable.parameter" = "fg"
    "variable.other.member" = "fg"
    "label" = "number"
    "punctuation" = "keyword"
    "punctuation.delimiter" = "keyword"
    "punctuation.bracket" = "fg"
    "punctuation.special" = "string-escape"
    "keyword" = "keyword"
    "keyword.control" = "keyword"
    "keyword.operator" = "keyword"
    "keyword.directive" = "static-function"
    "keyword.storage" = "keyword"
    "operator" = "fg"
    "function" = "function-decl"
    "function.method" = "function-decl"
    "function.builtin" = "static-function"
    "function.macro" = "static-function"
    "function.special" = "static-function"
    "tag" = "keyword"
    "namespace" = "fg"
    "special" = "static-function"

    # ── Markup (markdown etc.) ──────────────────────────────────────────
    "markup.heading" = { fg = "function-decl", modifiers = ["bold"] }
    "markup.heading.marker" = "fg-muted"
    "markup.list" = "keyword"
    "markup.list.checked" = "function-decl"
    "markup.list.unchecked" = "fg-muted"
    "markup.bold" = { modifiers = ["bold"] }
    "markup.italic" = { modifiers = ["italic"] }
    "markup.strikethrough" = { modifiers = ["crossed_out"] }
    "markup.link.url" = { fg = "keyword", underline = { style = "line" } }
    "markup.link.text" = { fg = "string", modifiers = ["italic"] }
    "markup.link.label" = "named-arg"
    "markup.quote" = { fg = "comment", modifiers = ["italic"] }
    "markup.raw" = "string"

    # ── Diff / VCS ──────────────────────────────────────────────────────
    "diff.plus" = "diff-add-stripe"
    "diff.plus.gutter" = "diff-add-stripe"
    "diff.minus" = "diff-delete-stripe"
    "diff.minus.gutter" = "diff-delete-stripe"
    "diff.delta" = "diff-change-stripe"
    "diff.delta.gutter" = "diff-change-stripe"
    "diff.delta.conflict" = "diff-conflict-stripe"

    # ── Diagnostics ─────────────────────────────────────────────────────
    "error" = { fg = "error", modifiers = ["bold"] }
    "warning" = { fg = "todo", modifiers = ["bold"] }
    "info" = { fg = "info-border", modifiers = ["bold"] }
    "hint" = { fg = "success-border", modifiers = ["bold"] }
    "diagnostic.error" = { underline = { color = "error", style = "curl" } }
    "diagnostic.warning" = { underline = { color = "todo", style = "curl" } }
    "diagnostic.info" = { underline = { color = "info-border", style = "curl" } }
    "diagnostic.hint" = { underline = { color = "success-border", style = "dotted" } }
    "diagnostic.unnecessary" = { modifiers = ["dim"] }
    "diagnostic.deprecated" = { modifiers = ["crossed_out"] }

    # ── UI ──────────────────────────────────────────────────────────────
    "ui.background" = { fg = "fg", bg = "editor-bg" }
    "ui.background.separator" = "border"
    "ui.text" = "fg"
    "ui.text.focus" = { fg = "fg", bg = "element-active", modifiers = ["bold"] }
    "ui.text.inactive" = "fg-muted"
    "ui.text.directory" = "function-decl"
    "ui.text.info" = { fg = "fg", bg = "panel-bg" }
    "ui.window" = { fg = "border" }
    "ui.help" = { fg = "fg", bg = "panel-bg" }
    "ui.popup" = { fg = "fg", bg = "panel-bg" }
    "ui.popup.info" = { fg = "fg", bg = "panel-bg" }
    "ui.menu" = { fg = "fg", bg = "panel-bg" }
    "ui.menu.selected" = { fg = "fg", bg = "element-active" }
    "ui.menu.scroll" = { fg = "scrollbar-thumb", bg = "scrollbar-track" }
    "ui.statusline" = { fg = "fg", bg = "tab-bar-bg" }
    "ui.statusline.inactive" = { fg = "fg-muted", bg = "page-bg" }
    "ui.statusline.normal" = { fg = "editor-bg", bg = "keyword", modifiers = ["bold"] }
    "ui.statusline.insert" = { fg = "editor-bg", bg = "function-decl", modifiers = ["bold"] }
    "ui.statusline.select" = { fg = "editor-bg", bg = "todo", modifiers = ["bold"] }
    "ui.statusline.separator" = "border-variant"
    "ui.bufferline" = { fg = "text-muted", bg = "tab-bar-bg" }
    "ui.bufferline.active" = { fg = "fg", bg = "editor-bg", underline = { color = "keyword", style = "line" } }
    "ui.bufferline.background" = { bg = "page-bg" }
    "ui.cursor" = { modifiers = ["reversed"] }
    "ui.cursor.primary" = { fg = "editor-bg", bg = "fg" }
    "ui.cursor.match" = { bg = "search-result-bg", modifiers = ["bold"] }
    "ui.selection" = { bg = "selection-bg" }
    "ui.selection.primary" = { bg = "selection-bg" }
    "ui.cursorline.primary" = { bg = "caret-row" }
    "ui.highlight" = { bg = "element-hover" }
    "ui.highlight.frameline" = { bg = "warning-bg" }
    "ui.linenr" = "line-number"
    "ui.linenr.selected" = "fg"
    "ui.gutter" = { bg = "gutter-bg" }
    "ui.gutter.selected" = { bg = "caret-row" }
    "ui.virtual" = "text-placeholder"
    "ui.virtual.whitespace" = "text-placeholder"
    "ui.virtual.ruler" = { bg = "caret-row" }
    "ui.virtual.indent-guide" = "border-variant"
    "ui.virtual.inlay-hint" = { fg = "text-muted", modifiers = ["italic"] }
    "ui.virtual.wrap" = "text-placeholder"
    "ui.virtual.jump-label" = { fg = "todo", modifiers = ["bold"] }
    "ui.picker.header" = { fg = "text-muted", modifiers = ["bold"] }
    "ui.debug.breakpoint" = "error"
    "ui.debug.active" = "todo"

    # [palette] must come last: every key after this header belongs to the table.
    [palette]
""".trimIndent()

fun Palette.generateHelix(): String {
    val vars = listOf(
        editorBg, caretRow, tabBarBg, pageBg, panelBg, gutterBg, selectionBg, searchResultBg,
        elementHover, elementActive, borderColor, borderVariant, scrollbarThumb, scrollbarTrack,
        fg, textMuted, fgMuted, textPlaceholder, lineNumber,
        keyword, functionDecl, constantField, implicitParam, number, string, stringEscape,
        staticFunc, namedArg, comment, javadoc, todo, error, warningBg, infoBorder, successBorder,
        diffAddStripe, diffDeleteStripe, diffChangeStripe, diffConflictStripe,
    )
    return HELIX_BODY + "\n" + vars.joinToString("\n") { "${it.name} = \"${hex(it)}\"" }
}
