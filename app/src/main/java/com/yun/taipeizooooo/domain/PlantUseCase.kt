package com.yun.taipeizooooo.domain

import com.yun.taipeizooooo.models.Plant
import com.yun.taipeizooooo.repositories.PlantRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class PlantUseCase(
    private val repository: PlantRepository,
    private var ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val pageSize: Int = 10,
) {
    private var currentItemSize: Int = 0
    private var totalSize: Int = 0
    private var isLastPage = false

    operator fun invoke(): Flow<List<Plant>> = flow {
        while (isLastPage.not()) {
            val response = repository.fetchPlant(offset = currentItemSize, limit = pageSize)
            emit(response.result.results)

            totalSize = response.result.count
            currentItemSize += response.result.results.size
            isLastPage = currentItemSize >= totalSize
        }
    }.flowOn(ioDispatcher)
}