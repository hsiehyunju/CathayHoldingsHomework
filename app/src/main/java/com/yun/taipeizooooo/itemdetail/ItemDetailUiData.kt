package com.yun.taipeizooooo.itemdetail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemDetailUiData(
    val type: ItemDetailDataType = ItemDetailDataType.Animal,
    val name: String = "",
    val englishName: String = "",
    val intro: String = "",
    val alsoKnown: String = "",
    val feature: String = "",
    val function: String = "",
    val imageUrl: String = "",
    val updateTime: String = "",
) : Parcelable
