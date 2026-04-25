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
