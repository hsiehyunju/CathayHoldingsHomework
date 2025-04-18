package com.yun.taipeizooooo.extension

import com.yun.taipeizooooo.R
import com.yun.taipeizooooo.utils.StringUtils

fun String.defaultToEmpty(): String {
    return if (this.isEmpty()) {
        StringUtils.getString(R.string.other_none)
    } else {
        this
    }
}