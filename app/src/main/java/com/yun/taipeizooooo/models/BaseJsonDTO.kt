package com.yun.taipeizooooo.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.serializer

@Parcelize
@Serializable
open class BaseJsonDTO : Parcelable

@Serializable
class RequestData(
    val offset: Int,
    val limit: Int,
) : BaseJsonDTO()

@Parcelize
@Serializable
open class ResponseData : BaseJsonDTO()


fun  RequestData.toQueryMap(): Map<String, String> {
    val jsonElement = Json.encodeToJsonElement(serializer(), this)
    val json = jsonElement as JsonObject
    return json.mapValues { it.value.jsonPrimitive.content }
}