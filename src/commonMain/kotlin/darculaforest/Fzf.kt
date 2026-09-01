package darculaforest

// Newest --color names used: sectional list-*/input-*/header-* (0.58), ghost (0.61), footer-* (0.63).
fun Palette.generateFzf() = $$"""
    # Darcula Forest — fzf colors (requires fzf >= 0.63)
    export FZF_DEFAULT_OPTS="${FZF_DEFAULT_OPTS:+$FZF_DEFAULT_OPTS } \
    --color=dark \
    --color=fg:#$${hexOf(fg)},bg:#$${hexOf(editorBg)},hl:#$${hexOf(todo)} \
    --color=fg+:#$${hexOf(fg)},bg+:#$${hexOf(caretRow)},hl+:#$${hexOf(todo)}:bold \
    --color=selected-fg:#$${hexOf(fg)},selected-bg:#$${hexOf(selectionBg)},selected-hl:#$${hexOf(todo)} \
    --color=query:#$${hexOf(fg)},ghost:#$${hexOf(textPlaceholder)},disabled:#$${hexOf(fgMuted)} \
    --color=prompt:#$${hexOf(keyword)},pointer:#$${hexOf(keyword)},marker:#$${hexOf(diffAddStripe)} \
    --color=spinner:#$${hexOf(constantField)},info:#$${hexOf(comment)} \
    --color=header:#$${hexOf(textMuted)},gutter:#$${hexOf(gutterBg)} \
    --color=border:#$${hexOf(borderColor)},label:#$${hexOf(textMuted)},separator:#$${hexOf(borderVariant)},scrollbar:#$${hexOf(scrollbarThumb)} \
    --color=preview-fg:#$${hexOf(fg)},preview-bg:#$${hexOf(panelBg)},preview-border:#$${hexOf(borderVariant)},preview-scrollbar:#$${hexOf(scrollbarThumb)},preview-label:#$${hexOf(textMuted)} \
    --color=list-border:#$${hexOf(borderVariant)},list-label:#$${hexOf(textMuted)} \
    --color=input-border:#$${hexOf(borderVariant)},input-label:#$${hexOf(textMuted)} \
    --color=header-border:#$${hexOf(borderVariant)},header-label:#$${hexOf(textMuted)} \
    --color=footer:#$${hexOf(textMuted)},footer-border:#$${hexOf(borderVariant)},footer-label:#$${hexOf(textMuted)}"
""".trimIndent()
