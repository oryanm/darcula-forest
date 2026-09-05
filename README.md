# 🌲 Darcula Forest 🌲

A dark green [color scheme][site] inspired in part by Solarized and JetBrains' original Darcula themes. Individual
colors are selected using the [Oklch color space][oklab-wiki].

We start with green because it is the easiest color on the eyes, minimizing eyestrain for prolonged daily usage.

Accent colors follow the 60/30/10 rule and are derived as analogous complementary colors to the main hue.

Usage of Oklch means the color scheme remains balanced while most of the relationships between syntactic tokens and UI
elements are simple linear transformations of each other, specific hex values are derived automatically and the entire
scheme can be generated from very few initial constants. See it in action [here][site].

### Support

Editors & IDEs

- IntelliJ / JetBrains — color scheme (ICLS) and UI theme
- VS Code
- Zed
- Neovim, Vim
- Helix
- T3 Code

Terminals & multiplexers

- Alacritty, kitty, Ghostty, WezTerm, foot, Warp, Windows Terminal, iTerm2
- tmux, Zellij

CLI tools

- btop, fzf, lazygit, starship, delta (git pager)

Other

- Omarchy
- CSS palette

## IntelliJ / JetBrains

`darcula/jetbrains/` contains two files:

- `Darcula_Forest.icls` — the editor color scheme (syntax highlighting, editor colors). Use it standalone:
  **Settings → Editor → Color Scheme → ⚙ → Import Scheme…** and pick the file, then select "Darcula Forest"
  from the scheme dropdown. The IDE chrome keeps whatever UI theme you already use.
- `Darcula_Forest.theme.json` — the UI theme (tool windows, tabs, buttons, etc.), extending Islands Darcula.
  It references the color scheme via `editorScheme`, so applying it gives you both. Theme JSON can't be
  imported directly; it has to be packaged as a [theme plugin][intellij-theme] and installed from disk
  (**Settings → Plugins → ⚙ → Install Plugin from Disk…**).

If you only want the syntax colors, import the `.icls`. For the complete look, build/install the theme plugin.

## Screenshots

<img width="1000" alt="IntelliJ Theme" src="https://github.com/user-attachments/assets/2094e336-3f6c-4d1d-b778-9ebe23cee876" />

<img width="1000" alt="Site" src="https://github.com/user-attachments/assets/88c904ad-8f5e-4b44-9d4f-0d1c8c95a658" />

## Regenerating

```sh
./gradlew run       # regenerate darcula/ from Palette.kt
./gradlew jvmTest   # golden test: darcula/ must match the generator
```

## Site

The [site][site] is a static page: colors are derived in CSS (`oklch(from …)`), and the
download button runs the same Kotlin generator compiled to JavaScript. To use it locally:

```sh
./gradlew copyJsBundle      # builds site/darcula-forest.js and copies palette.css (first run downloads Node, takes a few minutes)
open site/index.html        # or: ./gradlew site
```

GitHub Actions rebuilds and publishes it to GitHub Pages on every push to `master`
(`.github/workflows/pages.yml`).

[site]: https://oryanm.github.io/darcula-forest/
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
