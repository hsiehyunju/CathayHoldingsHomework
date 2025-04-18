package com.yun.taipeizooooo.extension

import com.yun.taipeizooooo.R
import com.yun.taipeizooooo.itemdetail.ItemDetailUiData
import com.yun.taipeizooooo.models.Animal
import com.yun.taipeizooooo.utils.StringUtils

fun Animal.toItemDetailUiData(): ItemDetailUiData {
    return ItemDetailUiData(
        name = nameCh,
        imageUrl = pic01Url,
        englishName = nameEn.defaultToEmpty(),
        intro = habitat.defaultToEmpty(),
        feature = feature.defaultToEmpty(),
        function = behavior.defaultToEmpty(),
        functionTitle = StringUtils.getString(R.string.item_detail_function_animal),
        alsoKnown = alsoKnown.defaultToEmpty(),
        updateTime = importDate.getFormattedUpdateDate()
    )
}