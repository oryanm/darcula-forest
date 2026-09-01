package darculaforest

// Full key set = the Default_theme map in src/btop_theme.cpp; unknown keys are silently
// ignored, so the 1.4.6+ proc_* keys are safe on older versions. Empty *_mid = two-color gradient.
fun Palette.generateBtop() = """
    # Darcula Forest theme for btop++

    # Main background, empty for terminal default, needs to be empty if you want transparent background
    theme[main_bg]="#${hexOf(editorBg)}"

    # Main text color
    theme[main_fg]="#${hexOf(fg)}"

    # Title color for boxes
    theme[title]="#${hexOf(termBrightWhite)}"

    # Highlight color for keyboard shortcuts
    theme[hi_fg]="#${hexOf(keyword)}"

    # Background color of selected item in processes box
    theme[selected_bg]="#${hexOf(selectionBg)}"

    # Foreground color of selected item in processes box
    theme[selected_fg]="#${hexOf(termBrightWhite)}"

    # Color of inactive/disabled text
    theme[inactive_fg]="#${hexOf(textPlaceholder)}"

    # Color of text appearing on top of graphs, i.e uptime and current network graph scaling
    theme[graph_text]="#${hexOf(textMuted)}"

    # Background color of the percentage meters
    theme[meter_bg]="#${hexOf(borderVariant)}"

    # Misc colors for processes box including mini cpu graphs, details memory graph and details status text
    theme[proc_misc]="#${hexOf(constantField)}"

    # Cpu box outline color
    theme[cpu_box]="#${hexOf(borderColor)}"

    # Memory/disks box outline color
    theme[mem_box]="#${hexOf(borderColor)}"

    # Net up/down box outline color
    theme[net_box]="#${hexOf(borderColor)}"

    # Processes box outline color
    theme[proc_box]="#${hexOf(borderColor)}"

    # Box divider line and small boxes line color
    theme[div_line]="#${hexOf(borderVariant)}"

    # Temperature graph colors (cool -> hot)
    theme[temp_start]="#${hexOf(constantField)}"
    theme[temp_mid]="#${hexOf(todo)}"
    theme[temp_end]="#${hexOf(error)}"

    # CPU graph colors (idle -> loaded)
    theme[cpu_start]="#${hexOf(keyword)}"
    theme[cpu_mid]="#${hexOf(todo)}"
    theme[cpu_end]="#${hexOf(error)}"

    # Mem/Disk free meter (two-color gradient: mid left empty)
    theme[free_start]="#${hexOf(keyword)}"
    theme[free_mid]=""
    theme[free_end]="#${hexOf(functionDecl)}"

    # Mem/Disk cached meter
    theme[cached_start]="#${hexOf(constantField)}"
    theme[cached_mid]=""
    theme[cached_end]="#${hexOf(termBrightCyan)}"

    # Mem/Disk available meter
    theme[available_start]="#${hexOf(string)}"
    theme[available_mid]=""
    theme[available_end]="#${hexOf(termBrightYellow)}"

    # Mem/Disk used meter (fills up toward error red)
    theme[used_start]="#${hexOf(todo)}"
    theme[used_mid]=""
    theme[used_end]="#${hexOf(error)}"

    # Download graph colors
    theme[download_start]="#${hexOf(termBlue)}"
    theme[download_mid]=""
    theme[download_end]="#${hexOf(termBrightBlue)}"

    # Upload graph colors
    theme[upload_start]="#${hexOf(termMagenta)}"
    theme[upload_mid]=""
    theme[upload_end]="#${hexOf(termBrightMagenta)}"

    # Process box color gradient for threads, mem and cpu usage (low -> high)
    theme[process_start]="#${hexOf(diffAddStripe)}"
    theme[process_mid]="#${hexOf(todo)}"
    theme[process_end]="#${hexOf(error)}"

    # Paused/followed process indicators (btop >= 1.4.6; silently ignored by older versions)
    theme[proc_pause_bg]="#${hexOf(warningBg)}"
    theme[proc_follow_bg]="#${hexOf(infoBg)}"
    theme[proc_banner_bg]="#${hexOf(elementActive)}"
    theme[proc_banner_fg]="#${hexOf(termBrightWhite)}"
    theme[followed_bg]="#${hexOf(infoBg)}"
    theme[followed_fg]="#${hexOf(termBrightWhite)}"
""".trimIndent()
