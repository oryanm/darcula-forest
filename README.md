# Darcula Forest

A dark green [color scheme][preview-html] inspired in part by Solarized and JetBrains' original Darcula themes. Individual colors are selected
using the [Oklch color space][oklab-wiki]. 

We start with green because it is the easiest color on the eyes, minimizing eyestrain for prolonged daily usage.

Accent colors follow the 60/30/10 rule and are derived as analogous complementary colors to the main hue.

Usage of Oklch means the color scheme remains balanced while most of the relationships between syntactic tokens and UI elements are simple linear transformations of each other, specific hex values are derived automatically and the entire scheme can be generated from very few initial constants. Check out the [preview here][preview-html].

### Support
1. Alacritty
2. CSS palette
3. IntelliJ Color Scheme (ICLS) and theme (soon)
4. Zed

## Regenerating

```sh
./gradlew run
```
## Screenshots

<img width="800" alt="image" src="https://github.com/user-attachments/assets/e59c36bd-dbed-484e-b9f0-3bcbe32e8cf3" />

<img width="800" alt="image" src="https://github.com/user-attachments/assets/3e35fee4-a84e-4088-bbbc-5d0457eac3e9" />


[preview-html]: https://htmlpreview.github.io/?https://github.com/oryanm/darcula-forest/blob/main/darcula/css/preview.html
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
