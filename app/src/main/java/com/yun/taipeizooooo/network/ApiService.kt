package com.yun.taipeizooooo.network

import com.yun.taipeizooooo.models.RequestData
import com.yun.taipeizooooo.models.ResponseData
import com.yun.taipeizooooo.models.toQueryMap
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiService(
    private val networkClient: NetworkClient
) {
    /**
     * GET request
     * @param url The URL to send the request to
     * @param request The request data to be sent as query parameters
     * @return The response data
     */
    internal suspend inline fun <reified R : ResponseData> get(
        url: String,
        request: RequestData
    ) : R {
        return networkClient.client.get(url) {
            url {
                request.toQueryMap().forEach { (key, value) ->
                    parameters.append(key, value)
                }
            }
        }.body()
    }
}