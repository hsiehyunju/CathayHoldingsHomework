 package com.yun.taipeizooooo.repositories

import com.yun.taipeizooooo.models.AnimalResponse
import com.yun.taipeizooooo.models.DistrictResponse
import com.yun.taipeizooooo.models.RequestData
import com.yun.taipeizooooo.network.ApiService

 /**
  * AnimalRepository 用來處理與動物園管區內動物的資料請求
  *
  * @param apiService ApiService 用來發送 API 請求
  */
 class AnimalRepository(
    private val apiService: ApiService
) {
    /**
     * Fetches the list of animal from the Taipei Zoo API.
     *
     * @param offset The offset for pagination.
     * @param limit The limit for pagination.
     */
    suspend fun fetchAnimals(
        offset: Int,
        limit: Int
    ): AnimalResponse = apiService.get(
        url = "https://data.taipei/api/v1/dataset/6afa114d-38a2-4e3c-9cfd-29d3bd26b65b?scope=resourceAquire",
        request = RequestData(offset, limit)
    )
}