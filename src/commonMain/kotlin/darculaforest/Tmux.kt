package darculaforest

fun Palette.generateTmux() = """
    set -g status-style "bg=${hex(tabBarBg)},fg=${hex(fg)}"
    set -g status-left-style "bg=${hex(keyword)},fg=${hex(editorBg)}"
    set -g status-right-style "bg=${hex(tabBarBg)},fg=${hex(textMuted)}"
    set -g window-status-style "bg=${hex(tabBarBg)},fg=${hex(textMuted)}"
    set -g window-status-current-style "bg=${hex(editorBg)},fg=${hex(fg)},bold"
    set -g window-status-activity-style "bg=${hex(tabBarBg)},fg=${hex(todo)}"
    set -g pane-border-style "fg=${hex(borderColor)}"
    set -g pane-active-border-style "fg=${hex(keyword)}"
    set -g message-style "bg=${hex(searchResultBg)},fg=${hex(fg)}"
    set -g message-command-style "bg=${hex(searchResultBg)},fg=${hex(fg)}"
    set -g mode-style "bg=${hex(selectionBg)},fg=${hex(fg)}"
    set -g clock-mode-colour "${hex(keyword)}"
    set -g copy-mode-match-style "bg=${hex(searchResultBg)},fg=${hex(fg)}"
    set -g copy-mode-current-match-style "bg=${hex(keyword)},fg=${hex(editorBg)}"
""".trimIndent()
