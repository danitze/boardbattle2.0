package com.example.boardbattle2_0.data

import com.example.boardbattle2_0.utils.CELLS_HORIZONTAL
import com.example.boardbattle2_0.utils.CELLS_VERTICAL
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GameStateTest {

    private var gameState: GameState = GameState()

    @Before
    fun initGameState() {
        gameState = GameState()
    }

    @Test
    fun testClearActiveFigure() = with(gameState) {
        gameState.board[0][0][0] = 1
        gameState.xPos = 0
        gameState.yPos = 0
        gameState.figureWidth = 1
        gameState.figureHeight = 1
        gameState.clearActiveFigure()
        Assert.assertArrayEquals(gameState.board, GameState().board)
    }

    @Test
    fun testCanPlacePlayer1() = with(gameState) {
        playerNum = 1
        figureHeight = 1
        figureWidth = 1
        xPos = CELLS_HORIZONTAL - figureWidth
        yPos = CELLS_VERTICAL - figureHeight
        Assert.assertEquals(canPlace(), true)

        --yPos
        Assert.assertEquals(canPlace(), false)
    }

    @Test
    fun testCanPlacePlayer2() = with(gameState) {
        playerNum = 2
        figureHeight = 1
        figureWidth = 1
        xPos = 0
        yPos = 0
        Assert.assertEquals(canPlace(), true)

        xPos = 1
        Assert.assertEquals(canPlace(), false)
    }

    @Test
    fun testCanPlaceToFigure() = with(gameState) {
        playerNum = 1
        figureWidth = 1
        figureHeight = 1
        board[0][0][1] = 1
        xPos = 1
        yPos = 0
        Assert.assertEquals(canPlace(), true)

        xPos = 0
        yPos = 1
        Assert.assertEquals(canPlace(), true)
    }

    @Test
    fun testTurn() = with(gameState) {
        figureWidth = 2
        figureHeight = 1
        xPos = 0
        yPos = 0
        turn()
        Assert.assertEquals(figureWidth, 1)
        Assert.assertEquals(figureHeight, 2)
    }

    @Test
    fun testResetFigure() = with(gameState) {
        playerNum = 1
        figureWidth = 1
        figureHeight = 1
        xPos = 0
        yPos = 0
        resetActiveFigure()
        val newBoard = GameState().board
        newBoard[0][0][0] = 1
        Assert.assertArrayEquals(board, newBoard)
    }

}