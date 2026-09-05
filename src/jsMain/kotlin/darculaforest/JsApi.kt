@file:OptIn(ExperimentalJsExport::class)

package darculaforest

import kotlinx.browser.window
import org.khronos.webgl.Int8Array
import kotlin.js.Promise

/**
 * Browser entry point, reached as `window["darcula-forest"].darculaforest.generateZip(...)`; the ByteArray arrives as an Int8Array.
 * The hand-made assets ([BUNDLED_ASSET_PATHS]) are fetched from the page's origin; one that cannot be fetched (e.g. on a file://
 * page, where browsers refuse fetch) is reported to [onMissing] and left out rather than failing the whole zip.
 */
@JsExport
fun generateZip(
    mainHue: Double, complementaryColorOffset: Double, baseChroma: Double, contrast: Double = 0.5,
    onMissing: (String) -> Unit = {},
): Promise<ByteArray> {
    val generated = generateAll(ThemeParams(mainHue, complementaryColorOffset, baseChroma, contrast))
    val assets = BUNDLED_ASSET_PATHS.map { path -> fetchAsset(path).catch<GeneratedFile?> { onMissing(path); null } }
    return Promise.all(assets.toTypedArray()).then { fetched -> zip(generated + fetched.filterNotNull()) }
}

private fun fetchAsset(path: String): Promise<GeneratedFile?> = Promise { resolve, reject ->
    window.fetch(path).then { res ->
        if (!res.ok) throw IllegalStateException("$path: HTTP ${res.status}")
        res.arrayBuffer().then { buf -> resolve(GeneratedFile(path, Int8Array(buf).unsafeCast<ByteArray>())) }
    }.catch(reject)
}

/** One generated file (e.g. `"css/palette.css"`) as text, or null if the path is unknown. */
@JsExport
fun generateFile(path: String, mainHue: Double, complementaryColorOffset: Double, baseChroma: Double, contrast: Double = 0.5): String? =
    generateAll(ThemeParams(mainHue, complementaryColorOffset, baseChroma, contrast)).firstOrNull { it.path == path }?.text
