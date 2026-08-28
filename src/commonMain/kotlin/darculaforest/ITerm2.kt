package darculaforest

// iTerm2 wants sRGB components as 0..1 floats. Derive them from the 8-bit hex so every
// target shares one quantization step.
private fun itermColor(key: String, v: Expr.Var): String {
    val hex = hexOf(v)
    fun comp(i: Int) = fmtFixed(hex.substring(i, i + 2).toInt(16) / 255.0, 6)
    return """
    |	<key>$key</key>
    |	<dict>
    |		<key>Alpha Component</key>
    |		<real>1</real>
    |		<key>Blue Component</key>
    |		<real>${comp(4)}</real>
    |		<key>Color Space</key>
    |		<string>sRGB</string>
    |		<key>Green Component</key>
    |		<real>${comp(2)}</real>
    |		<key>Red Component</key>
    |		<real>${comp(0)}</real>
    |	</dict>
    """.trimMargin()
}

fun Palette.generateITerm2(): String {
    val ansi = listOf(
        termBlack, termRed, termGreen, termYellow, termBlue, termMagenta, termCyan, termWhite,
        termBrightBlack, termBrightRed, termBrightGreen, termBrightYellow,
        termBrightBlue, termBrightMagenta, termBrightCyan, termBrightWhite,
    )
    val entries = ansi.mapIndexed { i, v -> itermColor("Ansi $i Color", v) } + listOf(
        itermColor("Background Color", editorBg),
        itermColor("Bold Color", termBrightWhite),
        itermColor("Cursor Color", keyword),
        itermColor("Cursor Guide Color", caretRow),
        itermColor("Cursor Text Color", editorBg),
        itermColor("Foreground Color", fg),
        itermColor("Link Color", keyword),
        itermColor("Selected Text Color", fg),
        itermColor("Selection Color", selectionBg),
    )
    return buildString {
        appendLine("""<?xml version="1.0" encoding="UTF-8"?>""")
        appendLine("""<!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">""")
        appendLine("""<plist version="1.0">""")
        appendLine("<dict>")
        entries.forEach { appendLine(it) }
        appendLine("</dict>")
        append("</plist>")
    }
}
