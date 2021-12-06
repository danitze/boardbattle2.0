package com.example.boardbattle2_0.game.viewmodel

import androidx.lifecycle.ViewModel
import com.example.boardbattle2_0.game.CONTINUE_GAME
import com.example.boardbattle2_0.game.NAVIGATE_TO_MENU
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameNavViewModel : ViewModel() {
    private val _navFlow = MutableStateFlow(CONTINUE_GAME)
    val navFlow = _navFlow.asStateFlow()

    fun navToMenu() {
        _navFlow.value = NAVIGATE_TO_MENU
    }

    fun reset() {
        _navFlow.value = CONTINUE_GAME
    }
}