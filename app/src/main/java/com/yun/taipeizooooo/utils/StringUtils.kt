package com.yun.taipeizooooo.utils

import com.yun.taipeizooooo.TaipeiZooApplication

object StringUtils {

    fun getString(
        resId: Int
    ): String {
        return TaipeiZooApplication.getInstance().getString(resId)
    }

    fun getString(
        resId: Int,
        vararg formatArgs: Any?
    ): String {
        return TaipeiZooApplication.getInstance().getString(resId, *formatArgs)
    }
}