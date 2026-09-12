package com.emrp.client

import android.content.Context
import java.io.File
import java.net.HttpURLConnection
import java.net.URL
import java.util.zip.ZipInputStream

class ClientInstaller(private val context: Context) {

    fun install(
        downloadUrl: String,
        onProgress: (Int) -> Unit,
        onComplete: (Boolean) -> Unit
    ) {
        Thread {
            try {
                val filesDir = File(context.getExternalFilesDir(null), "EM-SAMP")
                filesDir.mkdirs()

                val zipFile = File(filesDir, "client.zip")

                val connection = URL(downloadUrl).openConnection() as HttpURLConnection
                connection.connectTimeout = 15000
                connection.readTimeout = 30000
                connection.connect()

                if (connection.responseCode !in 200..299) {
                    throw Exception("Download failed: HTTP ${connection.responseCode}")
                }

                val total = connection.contentLengthLong
                var downloaded = 0L

                connection.inputStream.use { input ->
                    zipFile.outputStream().use { output ->
                        val buffer = ByteArray(8192)
                        var count: Int

                        while (input.read(buffer).also { count = it } != -1) {
                            output.write(buffer, 0, count)
                            downloaded += count

                            if (total > 0) {
                                onProgress(((downloaded * 100) / total).toInt())
                            }
                        }
                    }
                }

                ZipInputStream(zipFile.inputStream().buffered()).use { zip ->
                    var entry = zip.nextEntry

                    while (entry != null) {
                        val target = File(filesDir, entry.name)

                        if (entry.isDirectory) {
                            target.mkdirs()
                        } else {
                            target.parentFile?.mkdirs()
                            target.outputStream().use { output ->
                                zip.copyTo(output)
                            }
                        }

                        zip.closeEntry()
                        entry = zip.nextEntry
                    }
                }

                zipFile.delete()

                onComplete(true)
            } catch (e: Exception) {
                e.printStackTrace()
                onComplete(false)
            }
        }.start()
    }
}
