 package com.yun.taipeizooooo.repositories

import com.yun.taipeizooooo.models.PlantResult
import com.yun.taipeizooooo.models.RequestData
import com.yun.taipeizooooo.network.ApiService

 /**
  * PlantRepository 用來處理與動物園管區內植物的資料請求
  *
  * @param apiService ApiService 用來發送 API 請求
  */
 class PlantRepository(
    private val apiService: ApiService
) {
    /**
     * Fetches the list of plant from the Taipei Zoo API.
     *
     * @param offset The offset for pagination.
     * @param limit The limit for pagination.
     */
    suspend fun fetchPlant(
        offset: Int,
        limit: Int
    ): PlantResult = apiService.get(
        url = "https://data.taipei/api/v1/dataset/e20706d8-bf89-4e6a-9768-db2a10bb2ba4?scope=resourceAquire",
        request = RequestData(offset, limit)
    )
}