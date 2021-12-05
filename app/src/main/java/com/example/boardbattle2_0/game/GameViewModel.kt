package com.example.boardbattle2_0.game

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel(application: Application) : AndroidViewModel(application) {
    val _gameStateFlow = MutableStateFlow(Game())
    val gameFlow = _gameStateFlow.asStateFlow()
}