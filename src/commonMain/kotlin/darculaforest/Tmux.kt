package darculaforest

fun Palette.generateTmux() = """
    set -g status-style "bg=#${hexOf(tabBarBg)},fg=#${hexOf(fg)}"
    set -g status-left-style "bg=#${hexOf(keyword)},fg=#${hexOf(editorBg)}"
    set -g status-right-style "bg=#${hexOf(tabBarBg)},fg=#${hexOf(textMuted)}"
    set -g window-status-style "bg=#${hexOf(tabBarBg)},fg=#${hexOf(textMuted)}"
    set -g window-status-current-style "bg=#${hexOf(editorBg)},fg=#${hexOf(fg)},bold"
    set -g window-status-activity-style "bg=#${hexOf(tabBarBg)},fg=#${hexOf(todo)}"
    set -g pane-border-style "fg=#${hexOf(borderColor)}"
    set -g pane-active-border-style "fg=#${hexOf(keyword)}"
    set -g message-style "bg=#${hexOf(searchResultBg)},fg=#${hexOf(fg)}"
    set -g message-command-style "bg=#${hexOf(searchResultBg)},fg=#${hexOf(fg)}"
    set -g mode-style "bg=#${hexOf(selectionBg)},fg=#${hexOf(fg)}"
    set -g clock-mode-colour "#${hexOf(keyword)}"
    set -g copy-mode-match-style "bg=#${hexOf(searchResultBg)},fg=#${hexOf(fg)}"
    set -g copy-mode-current-match-style "bg=#${hexOf(keyword)},fg=#${hexOf(editorBg)}"
""".trimIndent()
