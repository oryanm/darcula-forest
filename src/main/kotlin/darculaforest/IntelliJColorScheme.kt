package darculaforest

import darculaforest.EffectType.BOLD_DOTTED_LINE
import darculaforest.EffectType.LINE_UNDERSCORE
import darculaforest.EffectType.STRIKEOUT
import darculaforest.EffectType.WAVE_UNDERSCORE
import darculaforest.FontType.BOLD
import darculaforest.FontType.BOLD_ITALIC
import darculaforest.FontType.ITALIC

// IntelliJ Color Scheme format

data class IclsColor(val name: String, val ref: Expr.Var)

data class IclsAttr(
    val name: String,
    val fg: Expr.Var? = null,
    val bg: Expr.Var? = null,
    val fontType: FontType? = null,
    val effectColor: Expr.Var? = null,
    val effectType: EffectType? = null,
    val errorStripeColor: Expr.Var? = null,
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
    IclsColor("CARET_ROW_COLOR",          caretRow),
    IclsColor("CONSOLE_BACKGROUND_KEY",   editorBg),
    IclsColor("DOCUMENTATION_COLOR",      docBg), // code documentation tooltip background
    IclsColor("FOLDED_TEXT_BORDER_COLOR", foldedTextBg),
    IclsColor("SELECTION_BACKGROUND",     selectionBg),
    IclsColor("TEARLINE_COLOR",           tearline),
)

val iclsAttributes = listOf(
    IclsAttr("ANNOTATION_ATTRIBUTE_NAME_ATTRIBUTES", fg = annotationNamedAtt),
    IclsAttr("ANNOTATION_NAME_ATTRIBUTES", fg = annotation, effectType = LINE_UNDERSCORE),
    IclsAttr("BASH.EXTERNAL_COMMAND", fg = constantField),
    IclsAttr("CSS.COLOR", baseAttributes = "CSS.IDENT"),
    IclsAttr("DEFAULT_COMMA", fg = punctuation),
    IclsAttr("DEFAULT_CONSTANT", fg = constantField, fontType = ITALIC),
    IclsAttr("DEFAULT_DOC_COMMENT", fg = javadoc, fontType = ITALIC),
    IclsAttr("DEFAULT_DOC_COMMENT_TAG", fg = javadocTag, fontType = BOLD_ITALIC, effectColor = javadocTag, effectType = LINE_UNDERSCORE),
    IclsAttr("DEFAULT_DOC_COMMENT_TAG_VALUE", fg = javadocTagVal),
    IclsAttr("DEFAULT_DOC_MARKUP", fg = javadocMarkup),
    IclsAttr("DEFAULT_ENTITY", fg = number),
    IclsAttr("DEFAULT_FUNCTION_DECLARATION", fg = functionDecl),
    IclsAttr("DEFAULT_IDENTIFIER", fg = fg, effectType = BOLD_DOTTED_LINE),
    IclsAttr("DEFAULT_INSTANCE_FIELD", fg = constantField),
    IclsAttr("DEFAULT_INVALID_STRING_ESCAPE", fg = stringEscBad, effectColor = error, effectType = WAVE_UNDERSCORE),
    IclsAttr("DEFAULT_KEYWORD", fg = keyword),
    IclsAttr("DEFAULT_METADATA", fg = annotation),
    IclsAttr("DEFAULT_NUMBER", fg = number),
    IclsAttr("DEFAULT_SEMICOLON", fg = punctuation),
    IclsAttr("DEFAULT_STATIC_FIELD", fg = constantField, fontType = ITALIC),
    IclsAttr("DEFAULT_STATIC_METHOD", fg = staticFunc, fontType = ITALIC),
    IclsAttr("DEFAULT_STRING", fg = string),
    IclsAttr("DEFAULT_TAG", fg = keyword),
    IclsAttr("DEFAULT_TEMPLATE_LANGUAGE_COLOR", bg = templateLang),
    IclsAttr("DEFAULT_VALID_STRING_ESCAPE", fg = stringEscape),
    IclsAttr("DEPRECATED_ATTRIBUTES", effectColor = fg, effectType = STRIKEOUT),
    IclsAttr("DIFF_CONFLICT", bg = diffConflict, errorStripeColor = diffConflictStripe),
    IclsAttr("DIFF_DELETED", bg = diffDelete, errorStripeColor = diffDeleteStripe),
    IclsAttr("DIFF_INSERTED", bg = diffAdd, errorStripeColor = diffAddStripe),
    IclsAttr("DIFF_MODIFIED", bg = diffChange, errorStripeColor = diffChangeStripe),
    IclsAttr("HTML_ATTRIBUTE_NAME", baseAttributes = "DEFAULT_ATTRIBUTE"),
    IclsAttr("HTML_ATTRIBUTE_VALUE", baseAttributes = "DEFAULT_STRING"),
    IclsAttr("HTML_CUSTOM_TAG_NAME", fg = number),
    IclsAttr("HTML_ENTITY_REFERENCE", baseAttributes = "DEFAULT_ENTITY"),
    IclsAttr("HTML_TAG", baseAttributes = "DEFAULT_TAG"),
    IclsAttr("HTML_TAG_NAME", baseAttributes = "DEFAULT_KEYWORD"),
    IclsAttr("HYPERLINK_ATTRIBUTES", fg = constantField, effectColor = constantField, effectType = LINE_UNDERSCORE),
    IclsAttr("IMPLICIT_ANONYMOUS_CLASS_PARAMETER_ATTRIBUTES", fg = implicitParam, effectColor = functionDecl, effectType = LINE_UNDERSCORE),
    IclsAttr("INJECTED_LANGUAGE_FRAGMENT", bg = injectedLangBg),
    IclsAttr("JS.GLOBAL_FUNCTION", baseAttributes = "DEFAULT_FUNCTION_DECLARATION"),
    IclsAttr("JS.GLOBAL_VARIABLE", baseAttributes = "DEFAULT_GLOBAL_VARIABLE"),
    IclsAttr("JS.INSTANCE_MEMBER_FUNCTION", baseAttributes = "DEFAULT_INSTANCE_METHOD"),
    IclsAttr("JS.LOCAL_VARIABLE", baseAttributes = "DEFAULT_LOCAL_VARIABLE"),
    IclsAttr("JS.REGEXP", baseAttributes = "DEFAULT_STRING"),
    IclsAttr("JSP_DIRECTIVE_NAME", baseAttributes = "DEFAULT_KEYWORD"),
    IclsAttr("KOTLIN_LABEL", fg = number),
    IclsAttr("KOTLIN_MUTABLE_VARIABLE", effectColor = mutableUnderline, effectType = LINE_UNDERSCORE),
    IclsAttr("KOTLIN_NAMED_ARGUMENT", fg = namedArg),
    IclsAttr("KOTLIN_TYPE_PARAMETER", baseAttributes = "TYPE_PARAMETER_NAME_ATTRIBUTES"),
    IclsAttr("MATCHED_BRACE_ATTRIBUTES", fg = matchingBraceFg, bg = matchingBraceBg, fontType = BOLD),
    IclsAttr("NOT_USED_ELEMENT_ATTRIBUTES", fg = unusedElement, errorStripeColor = unusedElementStripe),
    IclsAttr("PROPERTIES.INVALID_STRING_ESCAPE", baseAttributes = "DEFAULT_INVALID_STRING_ESCAPE"),
    IclsAttr("PROPERTIES.KEY", baseAttributes = "DEFAULT_KEYWORD"),
    IclsAttr("PROPERTIES.KEY_VALUE_SEPARATOR", baseAttributes = "DEFAULT_OPERATION_SIGN"),
    IclsAttr("PROPERTIES.VALID_STRING_ESCAPE", baseAttributes = "DEFAULT_VALID_STRING_ESCAPE"),
    IclsAttr("SEARCH_RESULT_ATTRIBUTES", bg = searchResultBg, errorStripeColor = searchResultStripe),
    IclsAttr("SQL_OUTER_QUERY_COLUMN", fg = implicitParam, bg = functionDecl, effectType = LINE_UNDERSCORE),
    IclsAttr("SQL_SYNTHETIC_ENTITY", baseAttributes = "DEFAULT_PREDEFINED_SYMBOL"),
    IclsAttr("TEXT", fg = fg, bg = editorBg, effectType = BOLD_DOTTED_LINE),
    IclsAttr("TODO_DEFAULT_ATTRIBUTES", fg = todo, fontType = ITALIC, errorStripeColor = todoStripe),
    IclsAttr("TYPE_PARAMETER_NAME_ATTRIBUTES", fg = genericTypeParam),
    IclsAttr("TYPO", effectColor = typoUnderline, effectType = WAVE_UNDERSCORE),
    IclsAttr("WARNING_ATTRIBUTES", bg = warningBg, errorStripeColor = warningStripe, effectType = WAVE_UNDERSCORE),
    IclsAttr("WRONG_REFERENCES_ATTRIBUTES", fg = error),
    IclsAttr("XML_ATTRIBUTE_NAME", baseAttributes = "DEFAULT_ATTRIBUTE"),
    IclsAttr("XML_CUSTOM_TAG_NAME", fg = number),
    IclsAttr("XML_ENTITY_REFERENCE", baseAttributes = "DEFAULT_ENTITY"),
    IclsAttr("XML_PROLOGUE", fg = keyword),
    IclsAttr("XML_TAG", baseAttributes = "DEFAULT_TAG"),
    IclsAttr("XML_TAG_NAME", baseAttributes = "DEFAULT_KEYWORD"),
)

fun generateIcls(): String = buildString {
    fun opt(name: String, value: String, indent: String) = "$indent<option name=\"$name\" value=\"$value\" />"
    fun hex(v: Expr.Var) = hexOf(v).uppercase()

    appendLine("""<scheme name="Darcula Forest" version="142" parent_scheme="Darcula">""")

    appendLine("  <metaInfo>")
    appendLine("    <property name=\"ide\">idea</property>")
    appendLine("    <property name=\"ideVersion\">2025.3.2.0.0</property>")
    appendLine("    <property name=\"originalScheme\">Darcula Forest</property>")
    appendLine("  </metaInfo>")

    appendLine("  <colors>")
    for (c in iclsColors) {
        appendLine(opt(c.name, hex(c.ref), " ".repeat(4)))
    }
    appendLine("  </colors>")

    appendLine("  <attributes>")
    for (attr in iclsAttributes) {
        if (attr.baseAttributes != null) {
            appendLine("    <option name=\"${attr.name}\" baseAttributes=\"${attr.baseAttributes}\" />")
            continue
        }
        appendLine("    <option name=\"${attr.name}\">")
        appendLine("      <value>")
        attr.fg?.let               { appendLine(opt("FOREGROUND", hex(it), " ".repeat(8))) }
        attr.bg?.let               { appendLine(opt("BACKGROUND", hex(it), " ".repeat(8))) }
        attr.fontType?.let         { appendLine(opt("FONT_TYPE", it.value.toString(), " ".repeat(8))) }
        attr.errorStripeColor?.let { appendLine(opt("ERROR_STRIPE_COLOR", hex(it), " ".repeat(8))) }
        attr.effectColor?.let      { appendLine(opt("EFFECT_COLOR", hex(it), " ".repeat(8))) }
        attr.effectType?.let       { appendLine(opt("EFFECT_TYPE", it.value.toString(), " ".repeat(8))) }
        appendLine("      </value>")
        appendLine("    </option>")
    }
    appendLine("  </attributes>")
    append("</scheme>")
}
