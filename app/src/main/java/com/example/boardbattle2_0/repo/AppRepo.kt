package com.example.boardbattle2_0.repo

import com.example.boardbattle2_0.game.GameService
import com.example.boardbattle2_0.game.data.GameState
import com.example.boardbattle2_0.storage.GameDataStoreService
import javax.inject.Inject

class AppRepo @Inject constructor(
    private val gameService: GameService,
    private val gameDataStoreService: GameDataStoreService
) {
    val gameStatesFlow = gameService.gameStatesFlow
    val savedGameStateFlow = gameDataStoreService.savedGameStateFlow

    var gameState
    get() = gameService.gameState
    set(value) {
        gameService.gameState = value
    }

    suspend fun moveRight() = gameService.moveRight()

    suspend fun moveLeft() = gameService.moveLeft()

    suspend fun moveUp() = gameService.moveUp()

    suspend fun moveDown() = gameService.moveDown()

    suspend fun moveRightTillEnd() = gameService.moveRightTillEnd()

    suspend fun moveLeftTillStart() = gameService.moveLeftTillStart()

    suspend fun moveUpTillStart() = gameService.moveUpTillStart()

    suspend fun moveDownTillEnd() = gameService.moveDownTillEnd()

    suspend fun nextMove() = gameService.nextMove()

    suspend fun turn() = gameService.turn()

    suspend fun place() = gameService.place()

    suspend fun saveGameState(gameState: GameState) = gameDataStoreService.saveGameState(gameState)

    fun getPlayerSpace(playerNum: Int) = gameService.getPlayerSpace(playerNum)

    fun getPlayerWithBiggestScore() = gameService.getPlayerWithBiggestScore()
}