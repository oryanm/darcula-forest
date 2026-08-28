package darculaforest

import java.io.File

// ── Directories ─────────────────────────────────────────────────────
// Defaults assume the process runs from the repo root. Override with
// -Ddarcula.out=… or DARCULA_OUT.

object Dirs {
    /** Generated theme files (fully overwritten by the generator). */
    val out: File get() = dir("darcula.out", "DARCULA_OUT", "darcula")

    private fun dir(prop: String, env: String, default: String) =
        File(System.getProperty(prop) ?: System.getenv(env) ?: default)
}
