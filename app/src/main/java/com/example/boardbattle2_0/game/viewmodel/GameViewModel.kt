package com.example.boardbattle2_0.game.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boardbattle2_0.CELLS_HORIZONTAL
import com.example.boardbattle2_0.CELLS_VERTICAL
import com.example.boardbattle2_0.game.GameService
import com.example.boardbattle2_0.game.data.GameState
import com.example.boardbattle2_0.repo.AppRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.jetbrains.kotlinx.multik.ndarray.data.get
import org.jetbrains.kotlinx.multik.ndarray.operations.indexOf
import org.jetbrains.kotlinx.multik.ndarray.operations.max
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val repo: AppRepo
) : ViewModel() {
    private val boardSpace = CELLS_HORIZONTAL * CELLS_VERTICAL

    val gameStatesFlow = repo.gameStatesFlow

    init {
        viewModelScope.launch {
            repo.nextMove()
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
}