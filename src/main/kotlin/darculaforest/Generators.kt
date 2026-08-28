package darculaforest

import kotlinx.serialization.Serializable
import java.io.File

data class GeneratedFile(val path: String, val contents: String)

@Serializable
data class ThemeParams(
  val mainHue: Double = 128.0,
  val complementaryColorOffset: Double = 30.0,
  val baseChroma: Double = 0.110,
)

fun generateAll(params: ThemeParams = ThemeParams()) = Palette(params).let { palette ->
    listOf(
        GeneratedFile("css/palette.css",                     palette.generateCss()),
        GeneratedFile("jetbrains/Darcula_Forest.icls",       palette.generateIcls() + "\n"),
        GeneratedFile("jetbrains/Darcula_Forest.theme.json", palette.generateIntellijTheme() + "\n"),
        GeneratedFile("alacritty/alacritty.toml",            palette.generateAlacritty() + "\n"),
        GeneratedFile("zed/darcula-forest.json",             palette.generateZed() + "\n"),
    )
}

// ── Directories ─────────────────────────────────────────────────────
// Defaults assume the process runs from the repo root. Override with
// -Ddarcula.out=… / -Ddarcula.site=… or DARCULA_OUT / DARCULA_SITE.

object Dirs {
    /** Generated theme files (fully overwritten by the generator). */
    val out: File get() = dir("darcula.out", "DARCULA_OUT", "darcula")

    /** Hand-maintained static site (preview pages). */
    val site: File get() = dir("darcula.site", "DARCULA_SITE", "site")

    private fun dir(prop: String, env: String, default: String) =
        File(System.getProperty(prop) ?: System.getenv(env) ?: default)
}
