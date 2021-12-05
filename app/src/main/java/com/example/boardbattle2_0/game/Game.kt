package com.example.boardbattle2_0.game

import com.example.boardbattle2_0.CELLS_HORIZONTAL
import com.example.boardbattle2_0.CELLS_VERTICAL
import org.jetbrains.kotlinx.multik.ndarray.data.get
import org.jetbrains.kotlinx.multik.ndarray.data.set

@Suppress("ControlFlowWithEmptyBody")
class Game {

    val gameState = GameState()

    fun moveRight() = with(gameState) {
        if (xPos + figureWidth < CELLS_HORIZONTAL) {
            clearActiveFigure()
            ++xPos
            resetActiveFigure()
            true
        } else false
    }

    fun moveLeft() = with(gameState) {
        if (xPos > 0) {
            clearActiveFigure()
            --xPos
            resetActiveFigure()
            true
        } else false
    }

    fun moveUp() = with(gameState) {
        if (yPos > 0) {
            clearActiveFigure()
            --yPos
            resetActiveFigure()
            true
        } else false
    }

    fun moveDown() = with(gameState) {
        if (yPos + figureHeight < CELLS_VERTICAL) {
            clearActiveFigure()
            ++yPos
            resetActiveFigure()
            true
        } else false
    }

    fun moveRightTillEnd() {
        while (moveRight()) {}
    }

    fun moveLeftTillStart() {
        while (moveLeft()) {}
    }

    fun moveDownTillEnd() {
        while (moveDown()) {}
    }

    fun moveUpTillStart() {
        while (moveUp()) {}
    }

    fun nextMove() = with(gameState) {
        playerNum = (playerNum % PLAYERS_COUNT) + 1
        figureWidth = (1..MAX_FIGURE_SIZE).random()
        figureHeight = (1..MAX_FIGURE_SIZE).random()
        xPos = if(playerNum == 1) CELLS_HORIZONTAL - figureWidth else 0
        yPos = if(playerNum == 1) CELLS_VERTICAL - gameState.figureHeight else 0
    }

    fun turn() = with(gameState) {
        if (xPos + figureHeight <= CELLS_HORIZONTAL && yPos + figureWidth <= CELLS_VERTICAL) {
            figureHeight = figureWidth.also { figureWidth = figureHeight }
        }
    }

    fun place() = with(gameState) {
        for(i in yPos until yPos + figureHeight) {
            for (j in xPos until xPos + figureWidth) {
                board[i, j, 1] = board[i, j, 0]
                board[i, j, 0] = 0
            }
        }
    }


}