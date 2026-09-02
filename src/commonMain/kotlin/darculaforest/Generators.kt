package darculaforest

data class GeneratedFile(val path: String, val contents: String)

data class ThemeParams(
    val mainHue: Double = 128.0,
    val complementaryColorOffset: Double = 30.0,
    val baseChroma: Double = 0.110,
    /** Lightness gap between editor background and foreground, centered on L=0.5. */
    val contrast: Double = 0.5,
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
        GeneratedFile("btop/darcula-forest.theme",           palette.generateBtop() + "\n"),
        GeneratedFile("fzf/darcula-forest.fzf.sh",           palette.generateFzf() + "\n"),
        GeneratedFile("lazygit/darcula-forest.yml",          palette.generateLazygit() + "\n"),
        GeneratedFile("starship/darcula-forest.toml",        palette.generateStarship() + "\n"),
        GeneratedFile("delta/darcula-forest.gitconfig",      palette.generateDelta() + "\n"),
        GeneratedFile("helix/darcula-forest.toml",           palette.generateHelix() + "\n"),
        GeneratedFile("vim/colors/darcula-forest.vim",       palette.generateVim() + "\n"),
        GeneratedFile("nvim/colors/darcula-forest.lua",      palette.generateNeovim() + "\n"),
        GeneratedFile("vscode/package.json",                  vsCodePackageJson + "\n"),
        GeneratedFile("vscode/themes/darcula-forest-color-theme.json", palette.generateVsCodeTheme() + "\n"),
    )
}
