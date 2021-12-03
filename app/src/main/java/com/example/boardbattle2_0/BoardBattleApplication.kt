package com.example.boardbattle2_0

import android.app.Application
import com.example.boardbattle2_0.views.AppButton

class BoardBattleApplication : Application() {
    private val soundOn = true

    val AppButton.soundOn
    get() = (context.applicationContext as BoardBattleApplication).soundOn
}