@file:OptIn(ExperimentalJsExport::class)

package darculaforest

/** Browser entry point, reached as `window["darcula-forest"].darculaforest.generateZip(...)`; the ByteArray arrives as an Int8Array. */
@JsExport
fun generateZip(mainHue: Double, complementaryColorOffset: Double, baseChroma: Double): ByteArray =
    zip(generateAll(ThemeParams(mainHue, complementaryColorOffset, baseChroma)))
