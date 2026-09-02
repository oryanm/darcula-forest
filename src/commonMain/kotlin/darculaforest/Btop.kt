package darculaforest

// Full key set = the Default_theme map in src/btop_theme.cpp; unknown keys are silently
// ignored, so the 1.4.6+ proc_* keys are safe on older versions. Empty *_mid = two-color gradient.
fun Palette.generateBtop() = """
    # Darcula Forest theme for btop++

    # Main background, empty for terminal default, needs to be empty if you want transparent background
    theme[main_bg]="${hex(editorBg)}"

    # Main text color
    theme[main_fg]="${hex(fg)}"

    # Title color for boxes
    theme[title]="${hex(termBrightWhite)}"

    # Highlight color for keyboard shortcuts
    theme[hi_fg]="${hex(keyword)}"

    # Background color of selected item in processes box
    theme[selected_bg]="${hex(selectionBg)}"

    # Foreground color of selected item in processes box
    theme[selected_fg]="${hex(termBrightWhite)}"

    # Color of inactive/disabled text
    theme[inactive_fg]="${hex(textPlaceholder)}"

    # Color of text appearing on top of graphs, i.e uptime and current network graph scaling
    theme[graph_text]="${hex(textMuted)}"

    # Background color of the percentage meters
    theme[meter_bg]="${hex(borderVariant)}"

    # Misc colors for processes box including mini cpu graphs, details memory graph and details status text
    theme[proc_misc]="${hex(constantField)}"

    # Cpu box outline color
    theme[cpu_box]="${hex(borderColor)}"

    # Memory/disks box outline color
    theme[mem_box]="${hex(borderColor)}"

    # Net up/down box outline color
    theme[net_box]="${hex(borderColor)}"

    # Processes box outline color
    theme[proc_box]="${hex(borderColor)}"

    # Box divider line and small boxes line color
    theme[div_line]="${hex(borderVariant)}"

    # Temperature graph colors (cool -> hot)
    theme[temp_start]="${hex(constantField)}"
    theme[temp_mid]="${hex(todo)}"
    theme[temp_end]="${hex(error)}"

    # CPU graph colors (idle -> loaded)
    theme[cpu_start]="${hex(keyword)}"
    theme[cpu_mid]="${hex(todo)}"
    theme[cpu_end]="${hex(error)}"

    # Mem/Disk free meter (two-color gradient: mid left empty)
    theme[free_start]="${hex(keyword)}"
    theme[free_mid]=""
    theme[free_end]="${hex(functionDecl)}"

    # Mem/Disk cached meter
    theme[cached_start]="${hex(constantField)}"
    theme[cached_mid]=""
    theme[cached_end]="${hex(termBrightCyan)}"

    # Mem/Disk available meter
    theme[available_start]="${hex(string)}"
    theme[available_mid]=""
    theme[available_end]="${hex(termBrightYellow)}"

    # Mem/Disk used meter (fills up toward error red)
    theme[used_start]="${hex(todo)}"
    theme[used_mid]=""
    theme[used_end]="${hex(error)}"

    # Download graph colors
    theme[download_start]="${hex(termBlue)}"
    theme[download_mid]=""
    theme[download_end]="${hex(termBrightBlue)}"

    # Upload graph colors
    theme[upload_start]="${hex(termMagenta)}"
    theme[upload_mid]=""
    theme[upload_end]="${hex(termBrightMagenta)}"

    # Process box color gradient for threads, mem and cpu usage (low -> high)
    theme[process_start]="${hex(diffAddStripe)}"
    theme[process_mid]="${hex(todo)}"
    theme[process_end]="${hex(error)}"

    # Paused/followed process indicators (btop >= 1.4.6; silently ignored by older versions)
    theme[proc_pause_bg]="${hex(warningBg)}"
    theme[proc_follow_bg]="${hex(infoBg)}"
    theme[proc_banner_bg]="${hex(elementActive)}"
    theme[proc_banner_fg]="${hex(termBrightWhite)}"
    theme[followed_bg]="${hex(infoBg)}"
    theme[followed_fg]="${hex(termBrightWhite)}"
""".trimIndent()
