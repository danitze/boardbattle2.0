package com.boardbattle.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.boardbattle.repo.AppRepo
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.lang.Exception

@HiltWorker
class SaverWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val repo: AppRepo
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        return try {
            repo.saveGameState(repo.gameState)
            Result.success()
        } catch (exception: Exception) {
            Result.failure()
        }
    }
}