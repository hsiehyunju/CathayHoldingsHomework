package com.yun.taipeizooooo.domain

import com.yun.taipeizooooo.events.DistrictUiState
import com.yun.taipeizooooo.models.DistrictData
import com.yun.taipeizooooo.repositories.DistrictRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class DistrictUseCase(
    private val repository: DistrictRepository,
    private var ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val pageSize: Int = 10,
) {

    var isOver = false

    private var isCalled = false
    private var currentOffset: Int = 0
    private var totalSize: Int = 0
    private val cachedList = mutableListOf<DistrictData>()

    operator fun invoke(): Flow<DistrictUiState> = flow {
         // 已經全部抓完就不打 API，直接回傳暫存資料
        if (isOver && isCalled && currentOffset >= totalSize) {
            emit(DistrictUiState.Success(cachedList.toList()))
            return@flow
        }

        try {
            isCalled = true
            val response = repository.fetchDistricts(offset = currentOffset, limit = pageSize)
            totalSize = response.result.count

            cachedList.addAll(response.result.results)
            if (cachedList.size >= totalSize) {
                isOver = true
            }
            currentOffset += response.result.results.size

            if (cachedList.isEmpty()) {
                emit(DistrictUiState.Failure("No Data Found"))
            } else {
                emit(DistrictUiState.Success(cachedList.toList()))
            }
        } catch (e: Exception) {
            emit(DistrictUiState.Failure(e.message ?: "Unknown Error"))
        }
    }.flowOn(ioDispatcher)
}