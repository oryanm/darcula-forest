# Darcula Forest

A dark green [color scheme][preview-html] based on JetBrains' original Darcula theme. Individual colors are selected
using the [Oklch color space][oklab-wiki]. 

Check out the [preview here][preview-html].

## Regenerating

```sh
./gradlew run
```

Writes `darcula/palette.css`, `darcula/Darcula_Forest.icls`, and `darcula/alacritty.toml` from the sources in `src/main/kotlin/darculaforest/`.

## Screenshots

<img width="800" height="657" alt="image" src="https://github.com/user-attachments/assets/3e35fee4-a84e-4088-bbbc-5d0457eac3e9" />

<img width="810" height="427" alt="image" src="https://github.com/user-attachments/assets/e59c36bd-dbed-484e-b9f0-3bcbe32e8cf3" />

[preview-html]: https://htmlpreview.github.io/?https://github.com/oryanm/darcula-forest/blob/main/darcula/preview.html
[darcula-src]: https://github.com/JetBrains/intellij-community/blob/master/platform/platform-resources/src/DefaultColorSchemesManager.xml
[oklab-wiki]: https://en.wikipedia.org/wiki/Oklab_color_space
[oklch-tool]: https://oklch.com
[apca]: https://apcacontrast.com/
[evilmartians]: https://evilmartians.com/chronicles/oklch-in-css-why-quit-rgb-hsl

[vim-colorscheme]: https://vi.stackexchange.com/questions/2782/how-can-i-create-my-own-colorscheme
[vim-colorscheme-vid]: http://vimcasts.org/episodes/creating-colorschemes-for-vim/
[omarchy-aether]: https://github.com/bjarneo/aether
[intellij-theme]: https://plugins.jetbrains.com/docs/intellij/themes-getting-started.html

[zed-builder]: https://zed.dev/theme-builder
[zed-syntax-highlighting]: https://zed.dev/docs/extensions/languages#syntax-highlighting
[zed-languages]: https://github.com/zed-industries/zed/tree/main/crates/languages
[nvim-ts-highlights]: https://github.com/nvim-treesitter/nvim-treesitter/blob/master/CONTRIBUTING.md#highlights
[helix-themes]: https://docs.helix-editor.com/themes.html
[tree-sitter-highlight]: https://tree-sitter.github.io/tree-sitter/3-syntax-highlighting.html
