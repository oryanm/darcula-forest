@file:OptIn(ExperimentalJsExport::class)

package darculaforest

/**
 * Browser entry point: builds the theme zip for the given parameters, entirely client-side.
 * Exposed on the UMD bundle as `window["darcula-forest"].generateZip(...)`; the ByteArray arrives as an Int8Array.
 */
@JsExport
fun generateZip(mainHue: Double, complementaryColorOffset: Double, baseChroma: Double): ByteArray =
    zip(generateAll(ThemeParams(mainHue, complementaryColorOffset, baseChroma).clamped()))
