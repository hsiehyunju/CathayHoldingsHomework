package com.yun.taipeizooooo.viewModels

import androidx.lifecycle.ViewModel
import com.yun.taipeizooooo.events.TaipeiZooActivityEvents
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class TaipeiZooActivityViewModel : ViewModel() {

    private val _events: MutableSharedFlow<TaipeiZooActivityEvents> = MutableSharedFlow()
    val events = _events.asSharedFlow()

}