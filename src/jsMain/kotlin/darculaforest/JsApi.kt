@file:OptIn(ExperimentalJsExport::class)

package darculaforest

/** Browser entry point, reached as `window["darcula-forest"].darculaforest.generateZip(...)`; the ByteArray arrives as an Int8Array. */
@JsExport
fun generateZip(mainHue: Double, complementaryColorOffset: Double, baseChroma: Double): ByteArray =
    zip(generateAll(ThemeParams(mainHue, complementaryColorOffset, baseChroma)))

/** One generated file (e.g. `"css/palette.css"`) as text, or null if the path is unknown. */
@JsExport
fun generateFile(path: String, mainHue: Double, complementaryColorOffset: Double, baseChroma: Double): String? =
    generateAll(ThemeParams(mainHue, complementaryColorOffset, baseChroma)).firstOrNull { it.path == path }?.contents
