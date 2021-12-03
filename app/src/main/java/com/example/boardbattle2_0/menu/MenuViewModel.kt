package com.example.boardbattle2_0.menu

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull

class MenuViewModel(application: Application): AndroidViewModel(application) {
    private val _navFlow = MutableStateFlow<Int?>(null)
    val navFlow = _navFlow.filterNotNull()

    fun navigate(navAction: Int) {
        _navFlow.value = navAction
    }
}