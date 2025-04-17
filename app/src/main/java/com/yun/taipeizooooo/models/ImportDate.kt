package com.yun.taipeizooooo.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class ImportDate(
    val date: String,
    @SerialName("timezone_type") val timezoneType: Int,
    val timezone: String
) : Parcelable