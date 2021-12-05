package com.example.boardbattle2_0.game

import com.example.boardbattle2_0.CELLS_HORIZONTAL
import com.example.boardbattle2_0.CELLS_VERTICAL
import org.jetbrains.kotlinx.multik.ndarray.data.get
import org.jetbrains.kotlinx.multik.ndarray.data.set

fun GameState.clearActiveFigure() = fillActiveMoveFigureWith(0)

fun GameState.resetActiveFigure() = fillActiveMoveFigureWith(playerNum)

fun GameState.fillActiveMoveFigureWith(number: Int) {
    for(i in yPos until yPos + figureHeight) {
        for (j in xPos until xPos + figureWidth) {
            board[i, j, 0] = number
        }
    }
}

fun GameState.canPlace(): Boolean {
    //Перевірка чи не перетинає наша форма переміщену фігуру
    for(i in yPos until yPos + figureHeight) {
        for (j in xPos until xPos + figureWidth) {
            if(board[i, j, 1] != 0) {
                return false
            }
        }
    }

    //Перевірка початкового ходу
    if(
        playerNum == 1 &&
        xPos == CELLS_HORIZONTAL - figureWidth &&
        yPos == CELLS_VERTICAL - figureHeight
    ) {
        return true
    }

    if(playerNum == 2 && xPos == 0 && yPos == 2) {
        return true
    }

    //Перевірка по краях згори і знизу фігури
    if(yPos > 0) {
        for(i in xPos until xPos + figureWidth) {
            if(board[yPos - 1, i, 1] == playerNum) {
                return true
            }
        }
    }

    if(yPos + figureHeight < CELLS_VERTICAL) {
        for(i in xPos until xPos + figureWidth) {
            if(board[yPos + figureHeight, i, 1] == playerNum) {
                return true
            }
        }
    }

    //Перевірка по краях зліва і справа фігури
    if(xPos > 0) {
        for(i in yPos until yPos + figureHeight) {
            if(board[i, xPos - 1, 1] == playerNum) {
                return true
            }
        }
    }

    if(xPos + figureWidth < CELLS_HORIZONTAL) {
        for(i in yPos until yPos + figureHeight) {
            if(board[i, xPos + figureWidth, 1] == playerNum) {
                return true
            }
        }
    }

    return false
}

fun GameState.turn() {
    if (xPos + figureHeight <= CELLS_HORIZONTAL && yPos + figureWidth <= CELLS_VERTICAL) {
        figureHeight = figureWidth.also { figureWidth = figureHeight }
    }
}