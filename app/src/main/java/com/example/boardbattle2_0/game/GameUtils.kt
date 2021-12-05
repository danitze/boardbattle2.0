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