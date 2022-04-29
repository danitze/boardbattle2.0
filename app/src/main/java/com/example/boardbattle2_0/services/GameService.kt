package com.example.boardbattle2_0.services

import com.example.boardbattle2_0.utils.CELLS_HORIZONTAL
import com.example.boardbattle2_0.utils.CELLS_VERTICAL
import com.example.boardbattle2_0.utils.MAX_FIGURE_SIZE
import com.example.boardbattle2_0.utils.PLAYERS_COUNT
import com.example.boardbattle2_0.data.GameState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Suppress("ControlFlowWithEmptyBody")
class GameService @Inject constructor() {

    lateinit var gameState: GameState

    private val _gameStatesFlow = MutableSharedFlow<GameState>()
    val gameStatesFlow: SharedFlow<GameState> = _gameStatesFlow.asSharedFlow()

    suspend fun moveRight() {
        if(actionRight()) {
            emitGameState()
        }
    }

    suspend fun moveLeft() {
        if(actionLeft()) {
            emitGameState()
        }
    }

    suspend fun moveUp() {
        if(actionUp()) {
            emitGameState()
        }
    }

    suspend fun moveDown() {
        if(actionDown()) {
            emitGameState()
        }
    }

    suspend fun moveRightTillEnd() {
        if(actionRightTillEnd()) {
            emitGameState()
        }
    }

    suspend fun moveLeftTillStart() {
        if(actionLeftTillStart()) {
            emitGameState()
        }
    }

    suspend fun moveDownTillEnd() {
        if(actionDownTillEnd()) {
            emitGameState()
        }
    }

    suspend fun moveUpTillStart() {
        if(actionUpTillStart()) {
            emitGameState()
        }
    }

    suspend fun turn() {
        gameState.clearActiveFigure()
        gameState.turn()
        gameState.resetActiveFigure()
        emitGameState()
    }

    suspend fun nextMove() = with(gameState) {
        do {
            playerNum = (playerNum % PLAYERS_COUNT) + 1
            figureWidth = (1..MAX_FIGURE_SIZE).random()
            figureHeight = (1..MAX_FIGURE_SIZE).random()
            xPos = if (playerNum == 1) CELLS_HORIZONTAL - figureWidth else 0
            yPos = if (playerNum == 1) CELLS_VERTICAL - gameState.figureHeight else 0
        } while (!isPlaceForFigure())
        resetActiveFigure()
        emitGameState()
    }

    suspend fun place() = with(gameState) {
        if(canPlace()) {
            for (i in yPos until yPos + figureHeight) {
                for (j in xPos until xPos + figureWidth) {
                    board[i][j][1] = board[i][j][0]
                    board[i][j][0] = 0
                }
            }
            val figureSpace = figureWidth * figureHeight
            freeSpace -= figureSpace
            spaces[playerNum - 1] += figureSpace
            if(freeSpace == 0) {
                emitGameState()
            } else {
                nextMove()
            }
        }
    }

    fun getPlayerSpace(playerNum: Int) = gameState.spaces[playerNum - 1]

    fun getPlayerWithBiggestScore() = gameState.spaces.maxOrNull()?.let {
        gameState.spaces.indexOf(it) + 1
    } ?: 1

    suspend fun uploadGameState(gameState: GameState) {
        this.gameState = gameState
        if(gameState == GameState()) {
            nextMove()
        } else {
            emitGameState()
        }
    }

    fun isBoardFilled() = gameState.freeSpace == 0

    private fun actionRight() = with(gameState) {
        if (xPos + figureWidth < CELLS_HORIZONTAL) {
            clearActiveFigure()
            ++xPos
            resetActiveFigure()
            true
        } else false
    }

    private fun actionLeft() = with(gameState) {
        if (xPos > 0) {
            clearActiveFigure()
            --xPos
            resetActiveFigure()
            true
        } else false
    }

    private fun actionUp() = with(gameState) {
        if (yPos > 0) {
            clearActiveFigure()
            --yPos
            resetActiveFigure()
            true
        } else false
    }

    private fun actionDown() = with(gameState) {
        if (yPos + figureHeight < CELLS_VERTICAL) {
            clearActiveFigure()
            ++yPos
            resetActiveFigure()
            true
        } else false
    }

    private fun actionRightTillEnd(): Boolean {
        var moved = false
        while (actionRight()) {
            moved = true
        }
        return moved
    }

    private fun actionLeftTillStart(): Boolean {
        var moved = false
        while (actionLeft()) {
            moved = true
        }
        return moved
    }

    private fun actionDownTillEnd(): Boolean {
        var moved = false
        while (actionDown()) {
            moved = true
        }
        return moved
    }

    private fun actionUpTillStart(): Boolean {
        var moved = false
        while (actionUp()) {
            moved = true
        }
        return moved
    }

    private suspend fun emitGameState() = _gameStatesFlow.emit(gameState)

    private fun isPlaceForFigure() = with(gameState.copy()) {
        for (i in 0..CELLS_VERTICAL - figureHeight) {
            for (j in 0..CELLS_HORIZONTAL - figureWidth) {
                xPos = j
                yPos = i
                if (canPlace()) {
                    return@with true
                }
            }
        }

        xPos = 0
        yPos = 0
        turn()
        for (i in 0..CELLS_VERTICAL - figureHeight) {
            for (j in 0..CELLS_HORIZONTAL - figureWidth) {
                xPos = j
                yPos = i
                if (canPlace()) {
                    return@with true
                }
            }
        }
        false
    }
}