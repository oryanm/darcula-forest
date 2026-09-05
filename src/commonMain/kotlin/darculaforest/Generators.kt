package darculaforest

/** One file of the theme bundle, relative to darcula/. Bytes are canonical so binary assets and generated text share one type. */
class GeneratedFile(val path: String, val bytes: ByteArray) {
    /** UTF-8 view, for the text generators' output (golden diffs, single-file download). */
    val text get() = bytes.decodeToString()
}

fun GeneratedFile(path: String, text: String) = GeneratedFile(path, text.encodeToByteArray())

/** Hand-made binaries committed under darcula/ (not generated). The golden test allows them; the site fetches them into the zip. */
val BUNDLED_ASSET_PATHS = listOf("omarchy/backgrounds/1-darcula-forest.webp", "omarchy/backgrounds/2-darcula-forest-muted.webp")

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
        GeneratedFile("omarchy/colors.toml",                  palette.generateOmarchyColors() + "\n"),
        GeneratedFile("omarchy/btop.theme",                   palette.generateBtop() + "\n"),
        GeneratedFile("omarchy/helix.toml",                   palette.generateHelix() + "\n"),
        GeneratedFile("omarchy/icons.theme",                  OMARCHY_ICONS_THEME + "\n"),
    )
}
