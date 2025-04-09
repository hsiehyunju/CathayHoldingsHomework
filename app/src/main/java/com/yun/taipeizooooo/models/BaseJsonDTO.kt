package com.yun.taipeizooooo.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.serializer

@Serializable
open class BaseJsonDTO {}
open class RequestData(): BaseJsonDTO()
open class ResponseData(): BaseJsonDTO()


inline fun <reified T : BaseJsonDTO> T.toQueryMap(): Map<String, String> {
    val jsonElement = Json.encodeToJsonElement(serializer(), this)
    val json = jsonElement as JsonObject
    return json.mapValues { it.value.jsonPrimitive.content }
}