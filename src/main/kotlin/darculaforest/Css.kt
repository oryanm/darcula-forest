package darculaforest

fun generateCss(): String = buildString {
    appendLine(":root {")
    for (entry in paletteEntries) {
        when (entry) {
            is Section -> {
                appendLine()
                appendLine("    /* ${entry.title} */")
            }
            is Def -> {
                val value = cssOf(entry.variable.value)
                val comment = entry.comment?.let { " /* $it */" } ?: ""
                appendLine("    --${entry.variable.name}: $value;$comment")
            }
            Blank -> appendLine()
        }
    }
    appendLine("}")
}

// ── Palette ─────────────────────────────────────────────────────────
// Ordered list of CSS palette entries.

val paletteEntries: List<PaletteEntry> = listOf(
    Section("Hues"),
    Def(mainHue),
    Def(complementaryColorOffset),
    Def(secondaryHue),
    Def(tertiaryHue),
    Def(baseChroma),
    Def(redHue),
    Def(blueHue),

    Section("Editor"),
    Def(editorBg),
    Def(caretRow),
    Def(selectionBg),
    Def(searchResultBg),
    Def(searchResultStripe),
    Def(injectedLangBg),
    Def(lineNumber),
    Def(fg),
    Def(foldedTextBg),
    Def(tearline),
    Def(templateLang),

    Section("Preview backgrounds"),
    Def(pageBg),
    Def(tabBarBg),
    Def(gutterBg),
    Def(fgMuted),

    Section("Syntax"),
    Def(keyword),
    Def(functionDecl),
    Def(constantField),
    Def(implicitParam),
    Def(number),
    Def(string),
    Def(stringEscape),
    Def(stringEscBad),
    Def(staticFunc),
    Def(namedArg),
    Blank,
    Def(`class`),
    Def(parameter),
    Def(functionCall),
    Def(localVar),
    Def(operator),
    Def(parens, "brackets also"),
    Def(punctuation, "comma, semicolon"),
    Def(genericTypeParam),
    Def(annotation),
    Def(annotationNamedAtt),

    Section("Effects"),
    Def(error),
    Def(warningBg),
    Def(warningStripe),
    Def(mutableUnderline),
    Def(typoUnderline),
    Def(deprecatedStrikethrough),

    Section("Comments"),
    Def(comment),
    Def(javadoc),
    Def(javadocMarkup),
    Def(javadocTag),
    Def(javadocTagVal),
    Def(todo),
    Def(todoStripe),

    Section("Diff"),
    Def(diffDelete),
    Def(diffChange),
    Def(diffAdd),
    Def(diffConflict),
    Def(diffDeleteStripe),
    Def(diffChangeStripe),
    Def(diffAddStripe),
    Def(diffConflictStripe),
)
