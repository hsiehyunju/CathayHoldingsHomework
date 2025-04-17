package com.yun.taipeizooooo.domain

import com.yun.taipeizooooo.models.Animal
import com.yun.taipeizooooo.repositories.AnimalRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AnimalUseCase(
    private val repository: AnimalRepository,
    private var ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val pageSize: Int = 10,
) {
    private var currentItemSize: Int = 0
    private var totalSize: Int = 0
    private var isLastPage = false

    operator fun invoke(): Flow<List<Animal>> = flow {
        while (isLastPage.not()) {
            val response = repository.fetchAnimals(offset = currentItemSize, limit = pageSize)
            emit(response.result.results)

            totalSize = response.result.count
            currentItemSize += response.result.results.size
            isLastPage = currentItemSize >= totalSize
        }
    }.flowOn(ioDispatcher)
}