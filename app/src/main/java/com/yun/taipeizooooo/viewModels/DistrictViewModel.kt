package com.yun.taipeizooooo.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yun.taipeizooooo.domain.DistrictUseCase
import com.yun.taipeizooooo.events.DistrictUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class DistrictViewModel(
    private val useCase: DistrictUseCase
) : ViewModel() {

    private val _fetchTrigger = MutableSharedFlow<Unit>()
    val uiState: StateFlow<DistrictUiState> = _fetchTrigger
        .flatMapLatest { useCase() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = DistrictUiState.Loading
        )

    fun fetchDistrictData() {
        if (useCase.isOver.not()) {
            viewModelScope.launch {
                _fetchTrigger.emit(Unit)
            }
        }
    }
}