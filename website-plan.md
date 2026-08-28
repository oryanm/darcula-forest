# Site notes

The preview at https://oryanm.github.io/darcula-forest/site/preview.html is a static page. Colours are
derived in CSS (`oklch(from …)`); the download button runs the Kotlin generator compiled to JS
(`src/jsMain`, bundle `site/darcula-forest.js`, built by `./gradlew copyJsBundle`). No backend.
`.github/workflows/pages.yml` publishes `site/` + `darcula/css/` on every push to `master`.

The old Ktor server and its production-readiness plan were dropped on 2026-08-27: the only server work
was a pure function of three numbers, so compiling it to the browser removed all hosting concerns.

## Remaining

- Repo Settings → Pages → Source must be set to "GitHub Actions" once before the first deploy succeeds.
