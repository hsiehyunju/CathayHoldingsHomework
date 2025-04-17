package com.yun.taipeizooooo.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class AnimalResponse(
    val result: AnimalResult
) : ResponseData()

@Parcelize
@Serializable
data class AnimalResult(
    val limit: Int,
    val offset: Int,
    val count: Int,
    val sort: String,
    val results: List<Animal>
) : Parcelable

@Parcelize
@Serializable
data class Animal(
    @SerialName("_id") val id: Int,
    @SerialName("_importdate") val importDate: ImportDate,
    @SerialName("a_name_ch") val nameCh: String,
    @SerialName("a_summary") val summary: String,
    @SerialName("a_keywords") val keywords: String,
    @SerialName("a_alsoknown") val alsoKnown: String,
    @SerialName("a_geo") val geo: String,
    @SerialName("a_location") val location: String,
    @SerialName("a_poigroup") val poigroup: String,
    @SerialName("a_name_en") val nameEn: String,
    @SerialName("a_name_latin") val nameLatin: String,
    @SerialName("a_phylum") val phylum: String,
    @SerialName("a_class") val clazz: String,
    @SerialName("a_order") val order: String,
    @SerialName("a_family") val family: String,
    @SerialName("a_conservation") val conservation: String,
    @SerialName("a_distribution") val distribution: String,
    @SerialName("a_habitat") val habitat: String,
    @SerialName("a_feature") val feature: String,
    @SerialName("a_behavior") val behavior: String,
    @SerialName("a_diet") val diet: String,
    @SerialName("a_crisis") val crisis: String,
    @SerialName("a_interpretation") val interpretation: String,
    @SerialName("a_theme_name") val themeName: String,
    @SerialName("a_theme_url") val themeUrl: String,
    @SerialName("a_adopt") val adopt: String,
    @SerialName("a_code") val code: String,
    @SerialName("a_pic01_alt") val pic01Alt: String,
    @SerialName("a_pic01_url") val pic01Url: String,
    @SerialName("a_pic02_alt") val pic02Alt: String,
    @SerialName("a_pic02_url") val pic02Url: String,
    @SerialName("a_pic03_alt") val pic03Alt: String,
    @SerialName("a_pic03_url") val pic03Url: String,
    @SerialName("a_pic04_alt") val pic04Alt: String,
    @SerialName("a_pic04_url") val pic04Url: String,
    @SerialName("a_pdf01_alt") val pdf01Alt: String,
    @SerialName("a_pdf01_url") val pdf01Url: String,
    @SerialName("a_pdf02_alt") val pdf02Alt: String,
    @SerialName("a_pdf02_url") val pdf02Url: String,
    @SerialName("a_voice01_alt") val voice01Alt: String,
    @SerialName("a_voice01_url") val voice01Url: String,
    @SerialName("a_voice02_alt") val voice02Alt: String,
    @SerialName("a_voice02_url") val voice02Url: String,
    @SerialName("a_voice03_alt") val voice03Alt: String,
    @SerialName("a_voice03_url") val voice03Url: String,
    @SerialName("a_vedio_url") val videoUrl: String,
    @SerialName("a_update") val update: String,
    @SerialName("a_cid") val cid: String?
) : Parcelable
