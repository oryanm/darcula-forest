# Static-site plan

Replace the Ktor server with the generator compiled to the browser. The live preview is already
client-side (CSS relative colors); only `POST /generate` needed a server. Compile `generateAll()`
with Kotlin/JS, build the zip in the page, host `site/` + `darcula/` on GitHub Pages. No backend.

## Steps — all done 2026-08-27

1. **Portable formatting** (pure refactor, golden test must stay byte-identical)
   - Replace `String.format` in `Expr.kt` with a common `fmtFixed(v, decimals)` / hex helpers.
     Must reproduce Java's HALF_UP rounding exactly, not JS `toFixed`.
   - `Math.cbrt` → `kotlin.math.cbrt`.
2. **Kotlin Multiplatform layout**
   - `commonMain`: Expr, Palette, Css, Alacritty, Zed, IntelliJColorScheme, IntelliJTheme, Generators, Zip.
   - `jvmMain`: `Main.kt` (CLI: regenerate with `--hue/--offset/--chroma/--out`, `zip`, `hex`), `Dirs`.
   - `jvmTest`: GoldenTest, ZipTest, FmtTest (fmtFixed vs `String.format` on ~4.5M values).
   - `jsMain`: `@JsExport fun generateZip(mainHue, compOffset, baseChroma): ByteArray` (an `Int8Array` in JS).
   - Delete `server/App.kt`, Ktor deps, `serve` task.
3. **Zip in common Kotlin** — stored (no compression) writer with CRC32; ~50 lines, no deps.
4. **Page integration** — `site/preview.html` `downloadTheme()` calls the JS bundle instead of
   `fetch("/generate")`; bundle copied into `site/` by a Gradle task. Works from `file://`.
   Clamp inputs client-side (hue 0–360, offset 0–180, chroma 0–0.4).
5. **Deploy** — GitHub Actions: build JS, publish `site/` + `darcula/css` to Pages. Update README link.

## Verification (done)
- `./gradlew jvmTest` (golden) passes after every step.
- `./gradlew jsBrowserProductionWebpack` produces a bundle; open `site/preview.html` from disk,
  download a zip, compare its contents to `./gradlew run` output for the same params.

## Remaining

- Repo Settings → Pages → Source must be set to "GitHub Actions" once before the first deploy succeeds.
- `kotlin-js-store/yarn.lock` is committed on purpose (KGP convention); `site/darcula-forest.js` is build output and ignored.

## Todo / notes (moved from README)
- fix zed status bluesz

⏺ A few solid options, roughly in order of effort-to-reach ratio:

- r/unixporn — by far the biggest audience for terminal/editor themes. Post a screenshot of your Alacritty + IntelliJ setup with "[OC]" and a link to the repo. They
  love OKLCH/principled-palette stories.
- Hacker News (Show HN) — frame it around the idea (OKLCH-derived dark green scheme, generator-based) rather than "another theme." Your PRODUCTION.md / generator
  approach is the hook.
- JetBrains Marketplace — publish the IntelliJ theme as a plugin. This is where IntelliJ users actually discover themes; much higher install rate than GitHub stars.
- Alacritty themes repo (alacritty/alacritty-theme) — submit a PR. Free distribution to every Alacritty user.
- r/JetBrains, r/IntelliJIDEA, r/Kotlin — smaller but well-targeted.
- Lobste.rs — if you have an invite; the color-theory angle plays well there.
- Bluesky / Mastodon with #unixporn and screenshots — low effort, occasional traction.

The single highest-leverage move is probably JetBrains Marketplace + a r/unixporn post on the same day, with the OKLCH-derivation story as your differentiator.
