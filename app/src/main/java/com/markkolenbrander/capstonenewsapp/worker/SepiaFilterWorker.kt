package com.markkolenbrander.capstonenewsapp.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class SepiaFilterWorker(context: Context, workerParameters: WorkerParameters )
    : Worker(context, workerParameters) {

    override fun doWork(): Result {
        TODO("Not yet implemented")
    }
}