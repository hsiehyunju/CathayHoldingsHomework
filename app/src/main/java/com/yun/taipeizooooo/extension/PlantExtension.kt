package com.yun.taipeizooooo.extension

import com.yun.taipeizooooo.R
import com.yun.taipeizooooo.itemdetail.ItemDetailUiData
import com.yun.taipeizooooo.models.Plant
import com.yun.taipeizooooo.utils.StringUtils

fun Plant.toItemDetailUiData(): ItemDetailUiData {
    return ItemDetailUiData(
        name = nameCh,
        imageUrl = pic01Url,
        englishName = nameEn.defaultToEmpty(),
        intro = brief.defaultToEmpty(),
        feature = feature.defaultToEmpty(),
        function = functionAndApplication.defaultToEmpty(),
        functionTitle = StringUtils.getString(R.string.item_detail_function_plant),
        alsoKnown = alsoKnown.defaultToEmpty(),
        updateTime = importDate.getFormattedUpdateDate()
    )
}