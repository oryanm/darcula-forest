package darculaforest

import java.nio.file.Files
import java.util.zip.CRC32
import java.util.zip.ZipFile
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class ZipTest {
    private val files = generateAll() +
        GeneratedFile("ünïcödé/naïve → ✓.txt", "héllo ✓ 日本語\n") +
        GeneratedFile("empty", "") +
        GeneratedFile("bin/noise.bin", ByteArray(4099) { (it * 131 + 17).toByte() })

    /** java.util.zip reads via the central directory and checks every CRC, so this validates the whole structure. */
    @Test
    fun `archive round-trips through ZipFile`() {
        val path = Files.createTempFile("darcula-forest-", ".zip")
        try {
            Files.write(path, zip(files))
            ZipFile(path.toFile(), Charsets.UTF_8).use { zf ->
                assertEquals(files.map { it.path }, zf.entries().toList().map { it.name })
                for (f in files) {
                    assertContentEquals(f.bytes, zf.getInputStream(zf.getEntry(f.path)).use { it.readAllBytes() }, f.path)
                }
            }
        } finally {
            Files.deleteIfExists(path)
        }
    }

    @Test
    fun `crc32 matches java implementation`() {
        val inputs = listOf(ByteArray(0), "123456789".encodeToByteArray(), ByteArray(100_003) { (it * 31 + 7).toByte() }) +
            files.map { it.bytes }
        for (input in inputs) {
            val expected = CRC32().apply { update(input) }.value
            assertEquals(expected, crc32(input).toLong() and 0xFFFFFFFFL, "crc of ${input.size} bytes")
        }
    }
}
