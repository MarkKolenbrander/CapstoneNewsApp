package com.markkolenbrander.capstonenewsapp.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class FileClearWorker (context: Context, workerParameters: WorkerParameters)
    : Worker(context, workerParameters) {

    override fun doWork(): Result {
        @Suppress("DEPRECATION") val root = applicationContext.externalMediaDirs.first()

        return try {
            root.listFiles()?.forEach { child ->
                if (child.isDirectory) {
                    child.deleteRecursively()
                }else {
                    child.delete()
                }
            }
            Result.success()
        }catch (error: Throwable){
            error.printStackTrace()
            Result.failure()
        }
    }
}