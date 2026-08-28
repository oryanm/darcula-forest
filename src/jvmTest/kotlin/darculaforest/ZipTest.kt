package darculaforest

import java.io.ByteArrayInputStream
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.util.zip.CRC32
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipInputStream
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ZipTest {
    private val sample = listOf(
        GeneratedFile("a.txt", "hello"),
        GeneratedFile("dir/sub/b.json", "{\"k\": [1, 2, 3]}\n"),
        GeneratedFile("empty.txt", ""),
        GeneratedFile("ünïcödé/naïve → ✓.css", "héllo wörld ✓ 日本語\n"),
        GeneratedFile("big.txt", "0123456789".repeat(10_000)),
    )

    private fun javaCrc(bytes: ByteArray): Long = CRC32().apply { update(bytes) }.value

    // (a) ZipInputStream reads local headers sequentially and verifies the CRC itself.
    @Test
    fun `round-trips through ZipInputStream`() {
        val bytes = zip(sample)
        val seen = mutableListOf<Pair<String, ByteArray>>()
        ZipInputStream(ByteArrayInputStream(bytes), Charsets.UTF_8).use { zin ->
            while (true) {
                val e = zin.nextEntry ?: break
                val data = zin.readAllBytes()
                assertEquals(ZipEntry.STORED, e.method, "method of ${e.name}")
                assertEquals(javaCrc(data), e.crc, "crc of ${e.name}")
                assertEquals(data.size.toLong(), e.size, "size of ${e.name}")
                seen += e.name to data
                zin.closeEntry()
            }
        }
        assertEquals(sample.map { it.path }, seen.map { it.first })
        for ((expected, actual) in sample.zip(seen)) {
            assertEquals(expected.contents, actual.second.toString(Charsets.UTF_8), "contents of ${expected.path}")
        }
    }

    // (b) Our CRC-32 implementation agrees with java.util.zip.CRC32.
    @Test
    fun `crc32 matches java implementation`() {
        val inputs = listOf(
            ByteArray(0),
            "a".encodeToByteArray(),
            "123456789".encodeToByteArray(),     // well-known check value 0xCBF43926
            "The quick brown fox".encodeToByteArray(),
            ByteArray(256) { it.toByte() },
            ByteArray(100_003) { (it * 31 + 7).toByte() },
        ) + sample.map { it.contents.encodeToByteArray() }
        for (input in inputs) {
            assertEquals(javaCrc(input), crc32(input).toLong() and 0xFFFFFFFFL, "crc of ${input.size} bytes")
        }
        assertEquals(0xCBF43926L, crc32("123456789".encodeToByteArray()).toLong() and 0xFFFFFFFFL)
    }

    // (c) ZipFile locates entries via the central directory and EOCD record.
    @Test
    fun `round-trips through ZipFile central directory`() {
        withTempZip(zip(sample)) { file ->
            ZipFile(file, Charsets.UTF_8).use { zf ->
                val entries = zf.entries().toList()
                assertEquals(sample.map { it.path }, entries.map { it.name })
                for ((expected, e) in sample.zip(entries)) {
                    val data = zf.getInputStream(e).use { it.readAllBytes() }
                    assertEquals(ZipEntry.STORED, e.method, "method of ${e.name}")
                    assertEquals(expected.contents, data.toString(Charsets.UTF_8), "contents of ${e.name}")
                    assertEquals(javaCrc(data), e.crc, "crc of ${e.name}")
                    assertEquals(data.size.toLong(), e.size, "size of ${e.name}")
                    assertEquals(data.size.toLong(), e.compressedSize, "compressed size of ${e.name}")
                }
                // Lookup by name exercises the central-directory index, including UTF-8 names.
                for (f in sample) {
                    val e = zf.getEntry(f.path) ?: error("entry ${f.path} not found by name")
                    assertEquals(f.contents, zf.getInputStream(e).use { it.readAllBytes() }.toString(Charsets.UTF_8))
                }
            }
        }
    }

    @Test
    fun `empty archive is valid`() {
        val bytes = zip(emptyList())
        assertEquals(22, bytes.size) // just the EOCD record
        withTempZip(bytes) { file ->
            ZipFile(file).use { zf -> assertEquals(0, zf.size()) }
        }
    }

    @Test
    fun `output is deterministic`() {
        assertContentEquals(zip(sample), zip(sample))
        assertContentEquals(zip(generateAll()), zip(generateAll()))
    }

    @Test
    fun `real generator output round-trips`() {
        val files = generateAll()
        withTempZip(zip(files)) { file ->
            ZipFile(file, Charsets.UTF_8).use { zf ->
                assertEquals(files.map { it.path }.toSet(), zf.entries().toList().map { it.name }.toSet())
                for (f in files) {
                    val e = zf.getEntry(f.path) ?: error("missing ${f.path}")
                    assertEquals(f.contents, zf.getInputStream(e).use { it.readAllBytes() }.toString(Charsets.UTF_8))
                }
            }
        }
    }

    @Test
    fun `unzip -t accepts the archive when available`() {
        withTempZip(zip(sample + generateAll())) { file ->
            val proc = try {
                ProcessBuilder("unzip", "-t", file.absolutePath).redirectErrorStream(true).start()
            } catch (e: IOException) {
                println("unzip not available, skipping: $e")
                return@withTempZip
            }
            val output = proc.inputStream.bufferedReader().readText()
            val code = proc.waitFor()
            println(output.trim())
            assertEquals(0, code, "unzip -t failed:\n$output")
            assertTrue("No errors detected" in output, "unexpected unzip output:\n$output")
        }
    }

    private fun withTempZip(bytes: ByteArray, block: (File) -> Unit) {
        val path = Files.createTempFile("darcula-forest-", ".zip")
        try {
            Files.write(path, bytes)
            block(path.toFile())
        } finally {
            Files.deleteIfExists(path)
        }
    }
}
