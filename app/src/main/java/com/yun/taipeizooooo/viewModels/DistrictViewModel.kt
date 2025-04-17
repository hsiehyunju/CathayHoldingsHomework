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
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class DistrictViewModel(
    private val useCase: DistrictUseCase
) : ViewModel() {

    private var canNextPage: Boolean = true

    private val _fetchTrigger = MutableStateFlow<RequestData?>(null)
    val uiState: StateFlow<DistrictUiState> = _fetchTrigger
        .filterNotNull()
        .flatMapLatest { useCase() }
        .onEach {
            canNextPage = useCase.isOver.not()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = DistrictUiState.Loading
        )

    fun fetchDistrictData() {
        if (getCanNextPage()) {
            viewModelScope.launch {
                _fetchTrigger.emit(RequestData(0, 0))
            }
        }
    }


    fun getCanNextPage() = canNextPage
}