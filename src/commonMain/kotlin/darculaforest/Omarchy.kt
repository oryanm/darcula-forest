package darculaforest

// Omarchy renders its own alacritty/ghostty/kitty/foot/btop/helix/hyprland/waybar/VS Code/Neovim
// configs from colors.toml at theme-set time (default/themed/*.tpl in basecamp/omarchy). Any
// same-named file shipped alongside colors.toml overrides the template, which is how btop.theme and
// helix.toml below keep the semantic mappings instead of Omarchy's generic ANSI ones.
//
// Keys not set here fall back to sRGB mixes in omarchy-theme-color: darker_background = background
// mixed 50% with black, brown = orange mixed 50% with black, light_foreground = foreground,
// selection_foreground = bright_foreground, cursor = bright_foreground.
fun Palette.generateOmarchyColors() = """
    mode = "dark"

    accent = "${hex(keyword)}"
    selection = "${hex(selectionBg)}"
    selection_foreground = "${hex(fg)}"
    muted = "${hex(comment)}"

    background = "${hex(editorBg)}"
    dark_background = "${hex(pageBg)}"
    lighter_background = "${hex(gutterBg)}"

    foreground = "${hex(fg)}"
    dark_foreground = "${hex(textPlaceholder)}"
    bright_foreground = "${hex(termBrightWhite)}"

    red = "${hex(termRed)}"
    yellow = "${hex(termYellow)}"
    orange = "${hex(number)}"
    green = "${hex(termGreen)}"
    cyan = "${hex(termCyan)}"
    blue = "${hex(termBlue)}"
    magenta = "${hex(termMagenta)}"

    bright_red = "${hex(termBrightRed)}"
    bright_yellow = "${hex(termBrightYellow)}"
    bright_green = "${hex(termBrightGreen)}"
    bright_cyan = "${hex(termBrightCyan)}"
    bright_blue = "${hex(termBrightBlue)}"
    bright_magenta = "${hex(termBrightMagenta)}"
""".trimIndent()

/** Yaru icon variant closest to the main hue; Omarchy's green themes (everforest, osaka-jade) use the same. */
const val OMARCHY_ICONS_THEME = "Yaru-sage"
