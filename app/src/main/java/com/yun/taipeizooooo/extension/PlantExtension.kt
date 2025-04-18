package com.yun.taipeizooooo.extension

import com.yun.taipeizooooo.itemdetail.ItemDetailDataType
import com.yun.taipeizooooo.itemdetail.ItemDetailUiData
import com.yun.taipeizooooo.models.Plant

fun Plant.toItemDetailUiData(): ItemDetailUiData {
    return ItemDetailUiData(
        name = nameCh,
        imageUrl = pic01Url,
        englishName = nameEn.defaultToEmpty(),
        intro = brief.defaultToEmpty(),
        feature = feature.defaultToEmpty(),
        function = functionAndApplication.defaultToEmpty(),
        alsoKnown = alsoKnown.defaultToEmpty(),
        type = ItemDetailDataType.Plant,
        updateTime = importDate.getFormattedUpdateDate()
    )
}