package com.yun.taipeizooooo.extension

import com.yun.taipeizooooo.itemdetail.ItemDetailDataType
import com.yun.taipeizooooo.itemdetail.ItemDetailUiData
import com.yun.taipeizooooo.models.Animal

fun Animal.toItemDetailUiData(): ItemDetailUiData {
    return ItemDetailUiData(
        name = nameCh,
        imageUrl = pic01Url,
        englishName = nameEn.defaultToEmpty(),
        intro = habitat.defaultToEmpty(),
        feature = feature.defaultToEmpty(),
        function = behavior.defaultToEmpty(),
        alsoKnown = alsoKnown.defaultToEmpty(),
        type = ItemDetailDataType.Animal,
        updateTime = importDate.getFormattedUpdateDate()
    )
}