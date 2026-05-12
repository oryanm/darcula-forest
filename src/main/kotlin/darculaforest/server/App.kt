package darculaforest.server

import darculaforest.ThemeParams
import darculaforest.generateAll
import io.ktor.http.ContentDisposition
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.install
import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer
import io.ktor.server.http.content.staticFiles
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.request.receive
import io.ktor.server.response.header
import io.ktor.server.response.respondBytesWriter
import io.ktor.server.response.respondRedirect
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import io.ktor.utils.io.jvm.javaio.toOutputStream
import java.io.File
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

fun main() {
    embeddedServer(CIO, port = 8080) {
        install(ContentNegotiation) { json() }

        routing {
            get("/") {
                call.respondRedirect("/css/preview.html")
            }

            staticFiles("/", File("darcula"))

            post("/generate") {
                val params = call.receive<ThemeParams>()
                val files = generateAll(params)

                call.response.header(
                    HttpHeaders.ContentDisposition,
                    ContentDisposition.Attachment
                        .withParameter(ContentDisposition.Parameters.FileName, "darcula-forest.zip")
                        .toString()
                )
                call.respondBytesWriter(ContentType.Application.Zip) {
                    ZipOutputStream(toOutputStream()).use { zip ->
                        for (f in files) {
                            zip.putNextEntry(ZipEntry(f.path))
                            zip.write(f.contents.toByteArray())
                            zip.closeEntry()
                        }
                    }
                }
            }
        }
    }.start(wait = true)
}
