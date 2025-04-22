package com.test.people.mycomposeapplication.home

import androidx.lifecycle.ViewModel
import com.test.people.mycomposeapplication.model.HomeScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState> = _uiState

    fun onCounterClick() {
        _uiState.update {
            it.copy(count = it.count + 1)
        }
    }

    fun setEnabled(enabled: Boolean) {
        _uiState.update {
            it.copy(enabled = enabled)
        }
    }
}