package darculaforest

data class GeneratedFile(val path: String, val contents: String)

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
        GeneratedFile("t3code/darcula-forest.json",          palette.generateT3Code() + "\n"),
        GeneratedFile("kitty/darcula-forest.conf",           palette.generateKitty() + "\n"),
        GeneratedFile("ghostty/darcula-forest",              palette.generateGhostty() + "\n"),
        GeneratedFile("wezterm/darcula-forest.toml",         palette.generateWezTerm() + "\n"),
        GeneratedFile("foot/darcula-forest.ini",             palette.generateFoot() + "\n"),
        GeneratedFile("warp/darcula_forest.yaml",            palette.generateWarp() + "\n"),
        GeneratedFile("windows-terminal/darcula-forest.json", palette.generateWindowsTerminal() + "\n"),
        GeneratedFile("iterm2/Darcula Forest.itermcolors",   palette.generateITerm2() + "\n"),
        GeneratedFile("tmux/darcula-forest.tmux.conf",       palette.generateTmux() + "\n"),
        GeneratedFile("zellij/darcula-forest.kdl",           palette.generateZellij() + "\n"),
    )
}
