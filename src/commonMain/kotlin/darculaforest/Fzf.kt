package darculaforest

// Newest --color names used: sectional list-*/input-*/header-* (0.58), ghost (0.61), footer-* (0.63).
fun Palette.generateFzf() = $$"""
    # Darcula Forest — fzf colors (requires fzf >= 0.63)
    export FZF_DEFAULT_OPTS="${FZF_DEFAULT_OPTS:+$FZF_DEFAULT_OPTS } \
    --color=dark \
    --color=fg:$${hex(fg)},bg:$${hex(editorBg)},hl:$${hex(todo)} \
    --color=fg+:$${hex(fg)},bg+:$${hex(caretRow)},hl+:$${hex(todo)}:bold \
    --color=selected-fg:$${hex(fg)},selected-bg:$${hex(selectionBg)},selected-hl:$${hex(todo)} \
    --color=query:$${hex(fg)},ghost:$${hex(textPlaceholder)},disabled:$${hex(fgMuted)} \
    --color=prompt:$${hex(keyword)},pointer:$${hex(keyword)},marker:$${hex(diffAddStripe)} \
    --color=spinner:$${hex(constantField)},info:$${hex(comment)} \
    --color=header:$${hex(textMuted)},gutter:$${hex(gutterBg)} \
    --color=border:$${hex(borderColor)},label:$${hex(textMuted)},separator:$${hex(borderVariant)},scrollbar:$${hex(scrollbarThumb)} \
    --color=preview-fg:$${hex(fg)},preview-bg:$${hex(panelBg)},preview-border:$${hex(borderVariant)},preview-scrollbar:$${hex(scrollbarThumb)},preview-label:$${hex(textMuted)} \
    --color=list-border:$${hex(borderVariant)},list-label:$${hex(textMuted)} \
    --color=input-border:$${hex(borderVariant)},input-label:$${hex(textMuted)} \
    --color=header-border:$${hex(borderVariant)},header-label:$${hex(textMuted)} \
    --color=footer:$${hex(textMuted)},footer-border:$${hex(borderVariant)},footer-label:$${hex(textMuted)}"
""".trimIndent()
