package com.example.boardbattle2_0.repo

import com.example.boardbattle2_0.services.GameService
import com.example.boardbattle2_0.data.GameState
import com.example.boardbattle2_0.services.GameDataStoreService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepo @Inject constructor(
    private val gameService: GameService,
    private val gameDataStoreService: GameDataStoreService
) {
    val gameStatesFlow = gameService.gameStatesFlow
    val savedGameStateFlow = gameDataStoreService.savedGameStateFlow

    val gameState
    get() = gameService.gameState

    suspend fun moveRight() = gameService.moveRight()

    suspend fun moveLeft() = gameService.moveLeft()

    suspend fun moveUp() = gameService.moveUp()

    suspend fun moveDown() = gameService.moveDown()

    suspend fun moveRightTillEnd() = gameService.moveRightTillEnd()

    suspend fun moveLeftTillStart() = gameService.moveLeftTillStart()

    suspend fun moveUpTillStart() = gameService.moveUpTillStart()

    suspend fun moveDownTillEnd() = gameService.moveDownTillEnd()

    suspend fun turn() = gameService.turn()

    suspend fun place() = gameService.place()

    suspend fun uploadGameState(gameState: GameState) = gameService.uploadGameState(gameState)

    suspend fun saveGameState(gameState: GameState) = gameDataStoreService.saveGameState(gameState)

    suspend fun clearGameState() = gameDataStoreService.clearGameState()

    fun getPlayerSpace(playerNum: Int) = gameService.getPlayerSpace(playerNum)

    fun getPlayerWithBiggestScore() = gameService.getPlayerWithBiggestScore()

    fun isBoardFilled() = gameService.isBoardFilled()
}