package darculaforest

import kotlinx.serialization.Serializable

data class GeneratedFile(val path: String, val contents: String)

@Serializable
data class ThemeParams(
  val mainHue: Double = 128.0,
  val complementaryColorOffset: Double = 30.0,
  val baseChroma: Double = 0.110,
) {
  /**
   * Keeps user-supplied parameters inside the ranges the palette math is designed for.
   */
  fun clamped() = ThemeParams(
    mainHue = mainHue.finiteOr(128.0).coerceIn(0.0, 360.0),
    complementaryColorOffset = complementaryColorOffset.finiteOr(30.0).coerceIn(0.0, 180.0),
    baseChroma = baseChroma.finiteOr(0.110).coerceIn(0.0, 0.4),
  )
}

fun generateAll(params: ThemeParams = ThemeParams()) = Palette(params).let { palette ->
    listOf(
        GeneratedFile("css/palette.css",                     palette.generateCss()),
        GeneratedFile("jetbrains/Darcula_Forest.icls",       palette.generateIcls() + "\n"),
        GeneratedFile("jetbrains/Darcula_Forest.theme.json", palette.generateIntellijTheme() + "\n"),
        GeneratedFile("alacritty/alacritty.toml",            palette.generateAlacritty() + "\n"),
        GeneratedFile("zed/darcula-forest.json",             palette.generateZed() + "\n"),
    )
}

private fun Double.finiteOr(default: Double) = if (isFinite()) this else default
