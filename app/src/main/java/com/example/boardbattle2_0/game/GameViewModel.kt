package com.example.boardbattle2_0.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {
    private val game = Game()

    private val _gameStatesFlow = MutableSharedFlow<GameState>()
    val gameStatesFlow: SharedFlow<GameState> = _gameStatesFlow.asSharedFlow()

    init {
        game.nextMove()
        emitGameState()
    }

    fun moveLeft() {
        game.moveLeft()
        emitGameState()
    }

    fun moveRight() {
        game.moveRight()
        emitGameState()
    }

    fun moveUp() {
        game.moveUp()
        emitGameState()
    }

    fun moveDown() {
        game.moveDown()
        emitGameState()
    }

    fun moveLeftTillStart() {
        game.moveLeftTillStart()
        emitGameState()
    }

    fun moveRightTillEnd() {
        game.moveRightTillEnd()
        emitGameState()
    }

    fun moveUpTillStart() {
        game.moveUpTillStart()
        emitGameState()
    }

    fun moveDownTillEnd() {
        game.moveDownTillEnd()
        emitGameState()
    }

    fun turn() {
        game.turn()
        emitGameState()
    }

    fun place() {
        if(game.place()) {
            game.nextMove()
        }
        emitGameState()
    }

    private fun emitGameState() = viewModelScope.launch{
        _gameStatesFlow.emit(game.gameState)
    }
}