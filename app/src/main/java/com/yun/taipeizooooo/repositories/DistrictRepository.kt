 package com.yun.taipeizooooo.repositories

import com.yun.taipeizooooo.models.DistrictResponse
import com.yun.taipeizooooo.models.RequestData
import com.yun.taipeizooooo.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

 /**
  * DistrictRepository 用來處理與動物園管區相關的資料請求
  *
  * @param apiService ApiService 用來發送 API 請求
  */
 class DistrictRepository(
    private val apiService: ApiService
) {
    /**
     * Fetches the list of districts from the Taipei Zoo API.
     *
     * @param offset The offset for pagination.
     * @param limit The limit for pagination.
     */
    suspend fun fetchDistricts(
        offset: Int,
        limit: Int
    ): DistrictResponse = apiService.get(
        url = "https://data.taipei/api/v1/dataset/9683ba26-109e-4cb8-8f3d-03d1b349db9f?scope=resourceAquire",
        request = RequestData(offset, limit)
    )

}