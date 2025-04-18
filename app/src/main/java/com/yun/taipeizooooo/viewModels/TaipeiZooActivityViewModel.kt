package com.yun.taipeizooooo.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yun.taipeizooooo.domain.AnimalUseCase
import com.yun.taipeizooooo.domain.PlantUseCase
import com.yun.taipeizooooo.events.TaipeiZooActivityEvents
import com.yun.taipeizooooo.models.Animal
import com.yun.taipeizooooo.models.Plant
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaipeiZooActivityViewModel(
    private val plantUseCase: PlantUseCase,
    private val animalUseCase: AnimalUseCase,
) : ViewModel() {

    private val _events: MutableSharedFlow<TaipeiZooActivityEvents> = MutableSharedFlow()
    val events = _events.asSharedFlow()

    private val _plantsData: MutableStateFlow<List<Plant>> = MutableStateFlow(emptyList())
    val plantsData = _plantsData.asStateFlow()

    private val _animationData: MutableStateFlow<List<Animal>> = MutableStateFlow(emptyList())
    val animalData = _animationData.asStateFlow()

    fun loadData() {
        viewModelScope.launch {
            plantUseCase.invoke().collect { newList ->
                val currentList = _plantsData.value
                _plantsData.value = currentList + newList
            }
        }
        viewModelScope.launch {
            animalUseCase.invoke().collect { newList ->
                val currentList = _animationData.value
                _animationData.value = currentList + newList
            }
        }
    }

    fun go (
        events: TaipeiZooActivityEvents
    ) {
        viewModelScope.launch {
            _events.emit(events)
        }
    }
}