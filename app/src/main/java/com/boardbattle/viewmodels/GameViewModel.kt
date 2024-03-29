package com.boardbattle.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.boardbattle.data.GameState
import com.boardbattle.repo.AppRepo
import com.boardbattle.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class GameViewModel @Inject constructor(
    private val repo: AppRepo,
    private val workManager: WorkManager,
    private val handle: SavedStateHandle,
    @Named(SAVER_WORKER_KEY)
    private val saverWorkerWorkRequest: OneTimeWorkRequest,
    @Named(CLEARER_WORKER_KEY)
    private val clearerWorkerWorkRequest: OneTimeWorkRequest
) : ViewModel() {
    private val boardSpace = CELLS_HORIZONTAL * CELLS_VERTICAL

    val gameStatesFlow = repo.gameStatesFlow
    private val savedGameStateFlow = repo.savedGameStateFlow

    init {
        viewModelScope.launch {
            savedGameStateFlow.collect { gameState ->
                handle.get<Boolean>(CONTINUE_GAME_KEY)?.let { continueGame ->
                    if(continueGame) {
                        repo.uploadGameState(gameState)
                    } else {
                        repo.uploadGameState(GameState())
                        clearGameState()
                    }
                } ?: repo.uploadGameState(gameState)
            }
        }
    }

    fun moveLeft() = viewModelScope.launch {
        repo.moveLeft()
    }

    fun moveRight() = viewModelScope.launch {
        repo.moveRight()
    }

    fun moveUp() = viewModelScope.launch {
        repo.moveUp()
    }

    fun moveDown() = viewModelScope.launch {
        repo.moveDown()
    }

    fun moveLeftTillStart() = viewModelScope.launch {
        repo.moveLeftTillStart()
    }

    fun moveRightTillEnd() = viewModelScope.launch {
        repo.moveRightTillEnd()
    }

    fun moveUpTillStart() = viewModelScope.launch {
        repo.moveUpTillStart()
    }

    fun moveDownTillEnd() = viewModelScope.launch {
        repo.moveDownTillEnd()
    }

    fun turn() = viewModelScope.launch {
        repo.turn()
    }

    fun place() = viewModelScope.launch {
        repo.place()
    }

    fun getPlayerSpace(playerNum: Int): Int = repo.getPlayerSpace(playerNum)

    fun getPlayerSpacePercent(playerNum: Int): Int =
        (getPlayerSpace(playerNum).toDouble()/ boardSpace * 100).toInt()

    fun getPlayerWithBiggestScore() = repo.getPlayerWithBiggestScore()

    fun onGameExit() {
        if(repo.isBoardFilled()) {
            clearGameState()
        } else {
            saveGameState()
        }
    }

    private fun saveGameState() {
        workManager.enqueue(saverWorkerWorkRequest)
    }

    private fun clearGameState() {
        workManager.enqueue(clearerWorkerWorkRequest)
    }
}