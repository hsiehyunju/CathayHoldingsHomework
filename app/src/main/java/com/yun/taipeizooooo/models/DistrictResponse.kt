package com.yun.taipeizooooo.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class DistrictResponse(
    @SerialName("result") val result: DistrictResult,
) : ResponseData()

@Parcelize
@Serializable
data class DistrictResult(
    val limit: Int,
    val offset: Int,
    val count: Int,
    @SerialName("results") val results: List<DistrictData>
) : Parcelable

@Parcelize
@Serializable
data class DistrictData (
    @SerialName("_id") val id: Int,
    @SerialName("_importdate") val importDate: ImportDate,
    @SerialName("e_no") val number: String,
    @SerialName("e_category") val category: String,
    @SerialName("e_name") val name: String,
    @SerialName("e_pic_url") val pictureUrl: String,
    @SerialName("e_info") val info: String,
    @SerialName("e_memo") val memo: String? = null,
    @SerialName("e_geo") val geo: String,
    @SerialName("e_url") val url: String
) : Parcelable