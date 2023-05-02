package com.boardbattle.data

import com.boardbattle.utils.CELLS_HORIZONTAL
import com.boardbattle.utils.CELLS_VERTICAL
import com.boardbattle.utils.PLAYERS_COUNT

/**
 * class which represents all the information about current game
 */
data class GameState (
    //board[i][j][0] - current figure, board[i][j][1] - board with already placed figures
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

    /**
     * Remove active figure (which is not placed yet) from the board
     */
    fun clearActiveFigure() = fillActiveMoveFigureWith(0)

    /**
     * Restore active figure on the board
     */
    fun resetActiveFigure() = fillActiveMoveFigureWith(playerNum)

    /**
     * Check if active figure can be placed
     */
    fun canPlace(): Boolean {
        //Check if active figure does not overlap other figures
        for(i in yPos until yPos + figureHeight) {
            for (j in xPos until xPos + figureWidth) {
                if(board[i][j][1] != 0) {
                    return false
                }
            }
        }

        //Check initial step
        if(
            playerNum == 1 &&
            xPos == CELLS_HORIZONTAL - figureWidth &&
            yPos == CELLS_VERTICAL - figureHeight
        ) {
            return true
        }

        if(playerNum == 2 && xPos == 0 && yPos == 0) {
            return true
        }

        //Checking for neighbour cells of current player
        //Check from top
        if(yPos > 0) {
            for(i in xPos until xPos + figureWidth) {
                if(board[yPos - 1][i][1] == playerNum) {
                    return true
                }
            }
        }

        //Check from bottom
        if(yPos + figureHeight < CELLS_VERTICAL) {
            for(i in xPos until xPos + figureWidth) {
                if(board[yPos + figureHeight][i][1] == playerNum) {
                    return true
                }
            }
        }

        //Check from left
        if(xPos > 0) {
            for(i in yPos until yPos + figureHeight) {
                if(board[i][xPos - 1][1] == playerNum) {
                    return true
                }
            }
        }

        //Check from right
        if(xPos + figureWidth < CELLS_HORIZONTAL) {
            for(i in yPos until yPos + figureHeight) {
                if(board[i][xPos + figureWidth][1] == playerNum) {
                    return true
                }
            }
        }

        return false
    }

    fun turn() {
        if (xPos + figureHeight <= CELLS_HORIZONTAL && yPos + figureWidth <= CELLS_VERTICAL) {
            figureHeight = figureWidth.also { figureWidth = figureHeight }
        }
    }

    private fun fillActiveMoveFigureWith(number: Int) {
        for(i in yPos until yPos + figureHeight) {
            for (j in xPos until xPos + figureWidth) {
                board[i][j][0] = number
            }
        }
    }
}
