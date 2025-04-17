package com.yun.taipeizooooo.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class PlantResult(
    val result: PlantList
) : ResponseData()

@Parcelize
@Serializable
data class PlantList(
    val limit: Int,
    val offset: Int,
    val count: Int,
    val sort: String,
    val results: List<Plant>
) : Parcelable

@Parcelize
@Serializable
data class Plant(
    @SerialName("_id") val id: Int,
    @SerialName("_importdate") val importDate: ImportDate,
    @SerialName("f_name_ch") val nameCh: String,
    @SerialName("f_summary") val summary: String,
    @SerialName("f_keywords") val keywords: String,
    @SerialName("f_alsoknown") val alsoKnown: String,
    @SerialName("f_geo") val geo: String,
    @SerialName("f_location") val location: String,
    @SerialName("f_name_en") val nameEn: String,
    @SerialName("f_name_latin") val nameLatin: String,
    @SerialName("f_family") val family: String,
    @SerialName("f_genus") val genus: String,
    @SerialName("f_brief") val brief: String,
    @SerialName("f_feature") val feature: String,
    @SerialName("f_function＆application") val functionAndApplication: String,
    @SerialName("f_code") val code: String,
    @SerialName("f_pic01_alt") val pic01Alt: String,
    @SerialName("f_pic01_url") val pic01Url: String,
    @SerialName("f_pic02_alt") val pic02Alt: String,
    @SerialName("f_pic02_url") val pic02Url: String,
    @SerialName("f_pic03_alt") val pic03Alt: String,
    @SerialName("f_pic03_url") val pic03Url: String,
    @SerialName("f_pic04_alt") val pic04Alt: String,
    @SerialName("f_pic04_url") val pic04Url: String,
    @SerialName("f_pdf01_alt") val pdf01Alt: String,
    @SerialName("f_pdf01_url") val pdf01Url: String,
    @SerialName("f_pdf02_alt") val pdf02Alt: String,
    @SerialName("f_pdf02_url") val pdf02Url: String,
    @SerialName("f_voice01_alt") val voice01Alt: String,
    @SerialName("f_voice01_url") val voice01Url: String,
    @SerialName("f_voice02_alt") val voice02Alt: String,
    @SerialName("f_voice02_url") val voice02Url: String,
    @SerialName("f_voice03_alt") val voice03Alt: String,
    @SerialName("f_voice03_url") val voice03Url: String,
    @SerialName("f_vedio_url") val vedioUrl: String,
    @SerialName("f_update") val update: String,
    @SerialName("f_cid") val cid: String?
) : Parcelable