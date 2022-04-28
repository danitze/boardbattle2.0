package com.example.boardbattle2_0.game.data

import com.example.boardbattle2_0.CELLS_HORIZONTAL
import com.example.boardbattle2_0.CELLS_VERTICAL
import com.example.boardbattle2_0.game.PLAYERS_COUNT

data class GameState (
    //board[i, j, 0] - це поточний хід гравця, board[i, j, 1] - це доска з встановленими на попередніх ходах фігурами
    val board: Array<Array<IntArray>> = Array(CELLS_VERTICAL) {
        Array(CELLS_HORIZONTAL) {
            IntArray(2) { 0 }
        }
    },
    val spaces: IntArray = IntArray(PLAYERS_COUNT) {0},
    var playerNum: Int = 0, //1 or 2
    var xPos: Int = 0,
    var yPos: Int = 0,
    var figureWidth: Int = 0,
    var figureHeight: Int = 0,
    var freeSpace: Int = CELLS_HORIZONTAL * CELLS_VERTICAL
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GameState

        if (!board.contentDeepEquals(other.board)) return false
        if (!spaces.contentEquals(other.spaces)) return false
        if (playerNum != other.playerNum) return false
        if (xPos != other.xPos) return false
        if (yPos != other.yPos) return false
        if (figureWidth != other.figureWidth) return false
        if (figureHeight != other.figureHeight) return false
        if (freeSpace != other.freeSpace) return false

        return true
    }

    override fun hashCode(): Int {
        var result = board.contentDeepHashCode()
        result = 31 * result + spaces.contentHashCode()
        result = 31 * result + playerNum
        result = 31 * result + xPos
        result = 31 * result + yPos
        result = 31 * result + figureWidth
        result = 31 * result + figureHeight
        result = 31 * result + freeSpace
        return result
    }
}
