package com.example.boardbattle2_0.viewmodels

import androidx.lifecycle.ViewModel
import com.example.boardbattle2_0.repo.AppRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    repo: AppRepo
) : ViewModel() {
    val savedGameStateFlow = repo.savedGameStateFlow
}