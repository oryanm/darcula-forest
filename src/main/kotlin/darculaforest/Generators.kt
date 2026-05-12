package darculaforest

import kotlinx.serialization.Serializable

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
