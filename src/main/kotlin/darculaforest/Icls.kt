package darculaforest

import darculaforest.EffectType.BOLD_DOTTED_LINE
import darculaforest.EffectType.LINE_UNDERSCORE
import darculaforest.EffectType.STRIKEOUT
import darculaforest.EffectType.WAVE_UNDERSCORE
import darculaforest.FontType.BOLD_ITALIC
import darculaforest.FontType.ITALIC

data class IclsColor(val name: String, val ref: String)

data class IclsAttr(
    val name: String,
    val fg: String? = null,
    val bg: String? = null,
    val fontType: FontType? = null,
    val effectColor: String? = null,
    val effectType: EffectType? = null,
    val errorStripeColor: String? = null,
    val baseAttributes: String? = null,
)

enum class FontType(val value: Int) {
    PLAIN(0),
    BOLD(1),
    ITALIC(2),
    BOLD_ITALIC(3),
}

enum class EffectType(val value: Int) {
    BOXED(0),
    LINE_UNDERSCORE(1),
    WAVE_UNDERSCORE(2),
    STRIKEOUT(3),
    BOLD_LINE_UNDERSCORE(4),
    BOLD_DOTTED_LINE(5),
    SEARCH_MATCH(6),
}

val iclsColors = listOf(
    IclsColor("CARET_ROW_COLOR", ref = "caret-row"),
    IclsColor("FOLDED_TEXT_BORDER_COLOR", ref = "folded-text-bg"),
    IclsColor("SELECTION_BACKGROUND", ref = "selection-bg"),
    IclsColor("TEARLINE_COLOR", ref = "tearline"),
)

val iclsAttributes = listOf(
    IclsAttr("ANNOTATION_ATTRIBUTE_NAME_ATTRIBUTES", fg = "annotation-named-att"),
    IclsAttr("ANNOTATION_NAME_ATTRIBUTES", fg = "annotation", effectType = LINE_UNDERSCORE),
    IclsAttr("DEFAULT_COMMA", fg = "punctuation"),
    IclsAttr("DEFAULT_CONSTANT", fg = "constant-field", fontType = ITALIC),
    IclsAttr("DEFAULT_DOC_COMMENT", fg = "javadoc", fontType = ITALIC),
    IclsAttr("DEFAULT_DOC_COMMENT_TAG", fg = "javadoc-tag", fontType = BOLD_ITALIC, effectColor = "javadoc-tag", effectType = LINE_UNDERSCORE),
    IclsAttr("DEFAULT_DOC_COMMENT_TAG_VALUE", fg = "javadoc-tag-val"),
    IclsAttr("DEFAULT_DOC_MARKUP", fg = "javadoc-markup"),
    IclsAttr("DEFAULT_FUNCTION_DECLARATION", fg = "function-decl"),
    IclsAttr("DEFAULT_IDENTIFIER", fg = "fg", effectType = BOLD_DOTTED_LINE),
    IclsAttr("DEFAULT_INSTANCE_FIELD", fg = "constant-field"),
    IclsAttr("DEFAULT_INVALID_STRING_ESCAPE", fg = "string-escape-bad", effectColor = "error", effectType = WAVE_UNDERSCORE),
    IclsAttr("DEFAULT_KEYWORD", fg = "keyword"),
    IclsAttr("DEFAULT_NUMBER", fg = "number"),
    IclsAttr("DEFAULT_SEMICOLON", fg = "punctuation"),
    IclsAttr("DEFAULT_STATIC_FIELD", fg = "constant-field", fontType = ITALIC),
    IclsAttr("DEFAULT_STATIC_METHOD", fg = "static-function", fontType = ITALIC),
    IclsAttr("DEFAULT_STRING", fg = "string"),
    IclsAttr("DEFAULT_TEMPLATE_LANGUAGE_COLOR", bg = "template-lang"),
    IclsAttr("DEFAULT_VALID_STRING_ESCAPE", fg = "string-escape"),
    IclsAttr("DEPRECATED_ATTRIBUTES", effectColor = "fg", effectType = STRIKEOUT),
    IclsAttr("DIFF_CONFLICT", bg = "diff-conflict", errorStripeColor = "diff-conflict-stripe"),
    IclsAttr("DIFF_DELETED", bg = "diff-delete", errorStripeColor = "diff-delete-stripe"),
    IclsAttr("DIFF_INSERTED", bg = "diff-add", errorStripeColor = "diff-add-stripe"),
    IclsAttr("DIFF_MODIFIED", bg = "diff-change", errorStripeColor = "diff-change-stripe"),
    IclsAttr("IMPLICIT_ANONYMOUS_CLASS_PARAMETER_ATTRIBUTES", fg = "implicit-param", effectColor = "function-decl", effectType = LINE_UNDERSCORE),
    IclsAttr("INJECTED_LANGUAGE_FRAGMENT", bg = "injected-lang-bg"),
    IclsAttr("KOTLIN_LABEL", fg = "number"),
    IclsAttr("KOTLIN_MUTABLE_VARIABLE", effectColor = "mutable-underline", effectType = LINE_UNDERSCORE),
    IclsAttr("KOTLIN_NAMED_ARGUMENT", fg = "named-arg"),
    IclsAttr("KOTLIN_TYPE_PARAMETER", baseAttributes = "TYPE_PARAMETER_NAME_ATTRIBUTES"),
    IclsAttr("SEARCH_RESULT_ATTRIBUTES", bg = "search-result-bg", errorStripeColor = "search-result-stripe"),
    IclsAttr("TEXT", fg = "fg", bg = "editor-bg", effectType = BOLD_DOTTED_LINE),
    IclsAttr("TODO_DEFAULT_ATTRIBUTES", fg = "todo", fontType = ITALIC, errorStripeColor = "todo-stripe"),
    IclsAttr("TYPE_PARAMETER_NAME_ATTRIBUTES", fg = "generic-type-param"),
    IclsAttr("TYPO", effectColor = "typo-underline", effectType = WAVE_UNDERSCORE),
    IclsAttr("WARNING_ATTRIBUTES", bg = "warning", errorStripeColor = "warning-stripe", effectType = WAVE_UNDERSCORE),
    IclsAttr("WRONG_REFERENCES_ATTRIBUTES", fg = "error"),
)
