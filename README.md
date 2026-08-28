# 🌲 Darcula Forest 🌲

A dark green [color scheme][preview-html] inspired in part by Solarized and JetBrains' original Darcula themes. Individual colors are selected
using the [Oklch color space][oklab-wiki]. 

We start with green because it is the easiest color on the eyes, minimizing eyestrain for prolonged daily usage.

Accent colors follow the 60/30/10 rule and are derived as analogous complementary colors to the main hue.

Usage of Oklch means the color scheme remains balanced while most of the relationships between syntactic tokens and UI elements are simple linear transformations of each other, specific hex values are derived automatically and the entire scheme can be generated from very few initial constants. Check out the [preview here][preview-html].

### Support

Editors & IDEs
- IntelliJ / JetBrains — color scheme (ICLS) and UI theme
- Zed
- T3 Code

Terminals & multiplexers
- Alacritty, kitty, Ghostty, WezTerm, foot, Warp, Windows Terminal, iTerm2
- tmux, Zellij

Other
- CSS palette

## Screenshots

<img width="1000" alt="IntelliJ Theme" src="https://github.com/user-attachments/assets/2094e336-3f6c-4d1d-b778-9ebe23cee876" />

<img width="1000" alt="HTML Preview" src="https://github.com/user-attachments/assets/88c904ad-8f5e-4b44-9d4f-0d1c8c95a658" />

## Regenerating

```sh
./gradlew run       # regenerate darcula/ from Palette.kt
./gradlew jvmTest   # golden test: darcula/ must match the generator
```

## Preview site

The [preview][preview-html] is a static page: colours are derived in CSS (`oklch(from …)`), and the
download button runs the same Kotlin generator compiled to JavaScript. To use it locally:

```sh
./gradlew copyJsBundle      # builds site/darcula-forest.js (first run downloads Node, takes a few minutes)
open site/preview.html
```

GitHub Actions rebuilds and publishes it to GitHub Pages on every push to `master`
(`.github/workflows/pages.yml`).

[preview-html]: https://oryanm.github.io/darcula-forest/site/preview.html
[darcula-src]: https://github.com/JetBrains/intellij-community/blob/master/platform/platform-resources/src/DefaultColorSchemesManager.xml
[oklab-wiki]: https://en.wikipedia.org/wiki/Oklab_color_space
[oklch-tool]: https://oklch.com
[apca]: https://apcacontrast.com/
[evilmartians]: https://evilmartians.com/chronicles/oklch-in-css-why-quit-rgb-hsl

[vim-colorscheme]: https://vi.stackexchange.com/questions/2782/how-can-i-create-my-own-colorscheme
[vim-colorscheme-vid]: http://vimcasts.org/episodes/creating-colorschemes-for-vim/
[omarchy-aether]: https://github.com/bjarneo/aether
[intellij-theme]: https://plugins.jetbrains.com/docs/intellij/themes-getting-started.html
[intellij-islands-dark]: https://github.com/JetBrains/intellij-community/blob/idea/261.23567.138/platform/platform-resources/src/themes/islands/ManyIslandsDark.theme.json
[zed-builder]: https://zed.dev/theme-builder
[zed-syntax-highlighting]: https://zed.dev/docs/extensions/languages#syntax-highlighting
[zed-languages]: https://github.com/zed-industries/zed/tree/main/crates/languages
[nvim-ts-highlights]: https://github.com/nvim-treesitter/nvim-treesitter/blob/master/CONTRIBUTING.md#highlights
[helix-themes]: https://docs.helix-editor.com/themes.html
[tree-sitter-highlight]: https://tree-sitter.github.io/tree-sitter/3-syntax-highlighting.html
