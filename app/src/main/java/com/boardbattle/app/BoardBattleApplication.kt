package com.boardbattle.app

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.boardbattle.views.AppButton
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class BoardBattleApplication : Application(), Configuration.Provider {

    @Inject lateinit var workerFactory: HiltWorkerFactory

    override fun getWorkManagerConfiguration(): Configuration = Configuration.Builder()
        .setWorkerFactory(workerFactory)
        .build()

}