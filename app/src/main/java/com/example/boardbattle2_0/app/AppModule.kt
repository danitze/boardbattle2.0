package com.example.boardbattle2_0.app

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.boardbattle2_0.utils.CLEARER_WORKER_KEY
import com.example.boardbattle2_0.utils.SAVER_WORKER_KEY
import com.example.boardbattle2_0.workers.ClearerWorker
import com.example.boardbattle2_0.workers.SaverWorker
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

    @Singleton
    @Provides
    @Named(SAVER_WORKER_KEY)
    fun provideSaverWorkerWorkRequest() = OneTimeWorkRequestBuilder<SaverWorker>().build()

    @Singleton
    @Provides
    @Named(CLEARER_WORKER_KEY)
    fun provideClearerWorkerWorkRequest() = OneTimeWorkRequestBuilder<ClearerWorker>().build()

    @Singleton
    @Provides
    fun provideWorkManager(@ApplicationContext context: Context) = WorkManager.getInstance(context)
}