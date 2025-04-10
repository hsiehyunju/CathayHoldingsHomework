package com.yun.taipeizooooo.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yun.taipeizooooo.domain.DistrictUseCase
import com.yun.taipeizooooo.events.DistrictUiState
import com.yun.taipeizooooo.models.RequestData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class DistrictViewModel(
    private val useCase: DistrictUseCase
) : ViewModel() {

    private val _fetchDistrictData = MutableStateFlow<RequestData?>(null)
    val uiState: StateFlow<DistrictUiState> = _fetchDistrictData
        .filterNotNull()
        .flatMapLatest { useCase() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = DistrictUiState.Loading
        )

    fun fetchDistrictData() {
        if (useCase.isOver.not()) {
            viewModelScope.launch {
                _fetchDistrictData.emit(RequestData(0,0))
            }
        }
    }
}