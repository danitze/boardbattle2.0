package com.example.boardbattle2_0.game

import com.example.boardbattle2_0.CELLS_HORIZONTAL
import com.example.boardbattle2_0.CELLS_VERTICAL
import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.zeros
import org.jetbrains.kotlinx.multik.ndarray.data.D3Array

data class GameState(
    //board[i, j, 0] - це поточний хід гравця, board[i, j, 1] - це доска з встановленими на попередніх ходах фігурами
    val board: D3Array<Int> = mk.zeros(CELLS_VERTICAL, CELLS_HORIZONTAL, 2),
    var playerNum: Int = 0, //1 or 2
    var xPos: Int = 0,
    var yPos: Int = 0,
    var figureWidth: Int = 0,
    var figureHeight: Int = 0,
    var freeSpace: Int = CELLS_HORIZONTAL * CELLS_VERTICAL
)
