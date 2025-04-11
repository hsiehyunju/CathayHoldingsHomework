package com.yun.taipeizooooo.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yun.taipeizooooo.events.TaipeiZooActivityEvents
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class TaipeiZooActivityViewModel : ViewModel() {

    private val _events: MutableSharedFlow<TaipeiZooActivityEvents> = MutableSharedFlow()
    val events = _events.asSharedFlow()



    fun go (
        events: TaipeiZooActivityEvents
    ) {
        viewModelScope.launch {
            _events.emit(events)
        }
    }
}