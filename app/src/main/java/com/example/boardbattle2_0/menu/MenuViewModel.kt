package com.example.boardbattle2_0.menu

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull

class MenuViewModel(application: Application): AndroidViewModel(application) {
    private val _navFlow = MutableStateFlow(NO_ACTION)
    val navFlow = _navFlow.asStateFlow().filterNotNull()

    fun navigate(navAction: Int) {
        _navFlow.value = navAction
    }

    fun reset() {
        _navFlow.value = NO_ACTION
    }
}