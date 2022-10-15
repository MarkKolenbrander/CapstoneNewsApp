package com.markkolenbrander.capstonenewsapp.worker

import android.content.Context

import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL

const val IMAGE_PATH = "image_path"

class DownloadWorker(context: Context, workerParameters: WorkerParameters)
    : Worker(context, workerParameters) {

    override fun doWork(): Result {
        val imageDownloadPath = inputData.getString(IMAGE_PATH) ?: return Result.failure()
        val imageUrl = URL(imageDownloadPath)

        val connection = imageUrl.openConnection() as HttpURLConnection
        connection.doInput = true
        connection.connect()

        val imagePath = "news_image_${System.currentTimeMillis()}.jpg"
        val inputStream = connection.inputStream
        val file = File(applicationContext.externalMediaDirs.first(), imagePath)


        val outputStream = FileOutputStream(file)
        outputStream.use { output ->
            val buffer = ByteArray(4 * 1024)

            var byteCount = inputStream.read(buffer)

            while (byteCount > 0) {
                output.write(buffer, 0 , byteCount)

                byteCount = inputStream.read(buffer)
            }
            output.flush()
        }
        val output = workDataOf(IMAGE_PATH to file.absolutePath)
        return Result.success(output)
    }
}