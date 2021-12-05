package com.example.boardbattle2_0.game

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel(application: Application) : AndroidViewModel(application) {
    private val _gameStateFlow: MutableStateFlow<GameState>
    val gameFlow: StateFlow<GameState>

    private val game = Game()
    init {
        game.nextMove()
        _gameStateFlow = MutableStateFlow(game.gameState)
        gameFlow = _gameStateFlow.asStateFlow()
    }

    fun moveLeft() {
        game.moveLeft()
        _gameStateFlow.value = game.gameState
    }

    fun moveRight() {
        game.moveRight()
        _gameStateFlow.value = game.gameState
    }

    fun moveUp() {
        game.moveUp()
        _gameStateFlow.value = game.gameState
    }

    fun moveDown() {
        game.moveDown()
        _gameStateFlow.value = game.gameState
    }

    fun moveLeftTillStart() {
        game.moveLeftTillStart()
        _gameStateFlow.value = game.gameState
    }

    fun moveRightTillEnd() {
        game.moveRightTillEnd()
        _gameStateFlow.value = game.gameState
    }

    fun moveUpTillStart() {
        game.moveUpTillStart()
        _gameStateFlow.value = game.gameState
    }

    fun moveDownTillEnd() {
        game.moveDownTillEnd()
        _gameStateFlow.value = game.gameState
    }

    fun turn() {
        game.turn()
        _gameStateFlow.value = game.gameState
    }

    fun place() {
        if(game.place()) {
            game.nextMove()
        }
        _gameStateFlow.value = game.gameState
    }
}