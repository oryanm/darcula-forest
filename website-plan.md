# Production-readiness plan

Path from the current Ktor prototype (`./gradlew serve`) to something safe to put on the public internet. Roughly priority-ordered; do P0 before exposing it, P1 before sharing the URL widely, P2 once it has real users.

## P0 — must-have before exposing publicly

### Input validation on `ThemeParams`
Today any `Double` is accepted. `NaN`, `Infinity`, or extreme values either produce garbage colors or throw deep in the renderer.

- Validate ranges in `App.kt` after `receive<ThemeParams>()`:
  - `mainHue`, `redHue`, `blueHue`: finite, `0.0..360.0`
  - `complementaryColorOffset`: finite, `0.0..180.0`
  - `baseChroma`: finite, `0.0..0.4`
- Return `400` with a small JSON error body on failure. Don't leak field paths from the deserializer.

### Global exception handler
Currently any `error(...)` in the palette/oklch code surfaces as a 500 with a stack trace.

- `install(StatusPages)` and map `IllegalArgumentException`/`IllegalStateException` to `400`/`500` with a generic message.
- Log the underlying exception server-side; never echo it to the client.

### Request size limit
`call.receive<ThemeParams>()` will parse arbitrarily large bodies.

- Add a `Content-Length` check or use `RequestBodyLimit` to cap at a few KB. The expected body is ~150 bytes.

### Rate limiting
`POST /generate` does real CPU work (color math + JSON serialization + zip). One tab in a loop can pin a core.

- `install(RateLimit)` with a per-IP bucket: e.g. 10 requests / minute on `/generate`.
- Static files (`/`) can be unlimited or have a much higher cap.

## P1 — before sharing the URL

### CORS
Today the route is same-origin. The moment the page is served from elsewhere (CDN, GitHub Pages, etc.) any site can call `/generate`.

- `install(CORS)` with an explicit allow-list of origins. Don't use `anyHost`.

### Request logging
No visibility into what's hitting the server.

- `install(CallLogging)` at INFO. Include path, status, duration, IP. Exclude request bodies.
- Pipe to stdout; let the deployment layer collect it.

### Configuration
Hardcoded port 8080 (dirs now configurable via `darcula.out` / `darcula.site`).

- Move both to `application.conf` (HOCON) or env vars: `PORT`, `STATIC_DIR`.
- Resolve `STATIC_DIR` to an absolute path at startup so the JVM isn't tied to a specific working directory.

### Packaging
`./gradlew serve` is a dev-only `JavaExec` task — no artifact ships.

- Add the `application` plugin's `installDist`/`distZip` for a runnable distribution, or a Shadow jar (`com.gradleup.shadow`).
- Write a `Dockerfile` (multi-stage: build with JDK, run with `eclipse-temurin:21-jre`). Copy `darcula/` and `site/` into the image alongside the jar.

### Graceful shutdown
SIGTERM should drain in-flight requests before exiting.

- `embeddedServer { ... }.start(wait = true)` already supports this via Ktor's shutdown hook; verify it's wired (no overrides) and test with `docker stop`.

## P2 — once it has users

### HTTPS / reverse proxy
Don't terminate TLS in Ktor.

- Front with Caddy, nginx, or a managed load balancer. Document the expected `X-Forwarded-*` headers and trust them via Ktor's `ForwardedHeaders`/`XForwardedHeaders` plugins.

### Tests
`GoldenTest` byte-diffs `generateAll()` against `darcula/`.

- **Unit:** snapshot tests on `generateAll(ThemeParams())` — expected output checked in under `src/test/resources/snapshots/`. Fail on any byte diff.
- **Property:** for any `ThemeParams` with hues in `[0, 360)` and chroma in `[0, 0.4]`, every generated file parses (CSS parses, JSON parses, TOML parses, ICLS is well-formed XML) and every Var resolves to a 6-char hex.
- **Integration:** spin up Ktor with `testApplication`, POST a few payloads, assert the zip contains all 5 entries and content-type/disposition headers are correct.

### Metrics / health
- `GET /health` returning 200.
- `install(MicrometerMetrics)` exposing `/metrics` for Prometheus scraping. Track request count, latency, generation time.

### Streaming zip
Current zip is built in-memory inside the response writer. Fine for ~35 KB output but lazy.

- The current code already writes via `ZipOutputStream(toOutputStream())` — verify it actually streams (not buffered) by watching the response with a slow client. If buffered, switch to `respondOutputStream`.

### Cache-busting for previews
`site/preview.html` is served with no cache headers; users get stale HTML after deploys.

- Add ETags via Ktor's `ConditionalHeaders` or set explicit `Cache-Control` on `/`.

## Out of scope (intentionally)

- **Authentication.** The endpoint is functionally read-only and produces deterministic output from inputs. No reason to require accounts unless we add saved-preset features.
- **Database / persistence.** Same reason.
- **Horizontal scaling.** A single small instance handles thousands of requests/day at this workload. Revisit only if traffic warrants it.
- **CDN for the zip.** Generation is fast and inputs are unique per request; caching would rarely hit.

## Definition of done

Before flipping a DNS record at this:

- [ ] All P0 items shipped
- [ ] All P1 items shipped
- [ ] Snapshot test in CI
- [ ] Container image builds and runs locally
- [ ] Reverse proxy + TLS in front
- [ ] One round of `curl` fuzzing against `/generate` (huge bodies, malformed JSON, NaN/Infinity, missing fields, wrong types) — all return 4xx, no 5xx
