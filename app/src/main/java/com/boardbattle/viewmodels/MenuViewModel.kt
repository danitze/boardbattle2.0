package com.boardbattle.viewmodels

import androidx.lifecycle.ViewModel
import com.boardbattle.repo.AppRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    repo: AppRepo
) : ViewModel() {
    val savedGameStateFlow = repo.savedGameStateFlow
}