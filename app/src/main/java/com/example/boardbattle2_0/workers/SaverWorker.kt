package com.example.boardbattle2_0.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class SaverWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        TODO("Not yet implemented")
    }
}