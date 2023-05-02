package com.boardbattle.app

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.boardbattle.utils.CLEARER_WORKER_KEY
import com.boardbattle.utils.SAVER_WORKER_KEY
import com.boardbattle.workers.ClearerWorker
import com.boardbattle.workers.SaverWorker
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGson() = Gson()

    @Provides
    @Named(SAVER_WORKER_KEY)
    fun provideSaverWorkerWorkRequest() = OneTimeWorkRequestBuilder<SaverWorker>().build()

    @Provides
    @Named(CLEARER_WORKER_KEY)
    fun provideClearerWorkerWorkRequest() = OneTimeWorkRequestBuilder<ClearerWorker>().build()

    @Singleton
    @Provides
    fun provideWorkManager(@ApplicationContext context: Context) = WorkManager.getInstance(context)
}