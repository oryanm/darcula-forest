package darculaforest

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * The committed files under darcula/ are the golden output of the generator.
 * If this fails, either run `./gradlew run` to refresh them, or someone hand-edited a generated file.
 */
class GoldenTest {
    @Test
    fun `generated output matches committed darcula directory`() {
        for (f in generateAll()) {
            val committed = File(OUT_DIR, f.path)
            assertTrue(committed.exists(), "missing $committed — run ./gradlew run")
            assertEquals(f.text, committed.readText(), "$committed is stale — run ./gradlew run")
        }
    }

    @Test
    fun `no unexpected files in generated directory`() {
        val expected = (generateAll().map { it.path } + BUNDLED_ASSET_PATHS).toSet()
        val actual = OUT_DIR.walk().filter { it.isFile && !it.name.startsWith(".") }.map { it.relativeTo(OUT_DIR).path }.toSet()
        assertEquals(expected, actual, "darcula/ must contain only generator output and BUNDLED_ASSET_PATHS")
    }

    @Test
    fun `bundled assets are committed`() {
        for (path in BUNDLED_ASSET_PATHS) assertTrue(File(OUT_DIR, path).length() > 0, "missing or empty asset $path")
    }

    @Test
    fun `hex roundtrips through oklch`() {
        for (hex in listOf("20231e", "acafa9", "5a792c", "ff0000", "000000", "ffffff")) {
            val o = oklchOfHex(hex)
            assertEquals(hex, hexOf(oklch(o.l, o.c, o.h)), "roundtrip $hex")
        }
    }
}
