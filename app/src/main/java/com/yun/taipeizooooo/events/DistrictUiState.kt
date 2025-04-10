package com.yun.taipeizooooo.events

import com.yun.taipeizooooo.models.DistrictData

sealed class DistrictUiState {

    object Loading : DistrictUiState()

    data class Failure(
        val message: String,
    ) : DistrictUiState()

    data class Success(
        val districts: List<DistrictData>
    ): DistrictUiState()
}