package com.example.boardbattle2_0.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boardbattle2_0.CELLS_HORIZONTAL
import com.example.boardbattle2_0.CELLS_VERTICAL
import com.example.boardbattle2_0.game.data.GameState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.jetbrains.kotlinx.multik.ndarray.data.get

class GameViewModel : ViewModel() {
    private val game = Game()
    val boardSpace = CELLS_HORIZONTAL * CELLS_VERTICAL

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

    fun getPlayerSpace(playerNum: Int): Int = game.gameState.spaces[playerNum - 1]

    fun getPlayerSpacePercent(playerNum: Int): Int =
        (getPlayerSpace(playerNum).toDouble()/ boardSpace * 100).toInt()

    private fun emitGameState() = viewModelScope.launch{
        _gameStatesFlow.emit(game.gameState)
    }
}