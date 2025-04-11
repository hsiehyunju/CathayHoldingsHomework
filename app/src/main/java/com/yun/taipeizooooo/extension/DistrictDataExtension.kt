package com.yun.taipeizooooo.extension

import com.yun.taipeizooooo.models.DistrictData
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun DistrictData.getFormattedUpdateDate(): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")
    val outputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
    val localDateTime = LocalDateTime.parse(importDate.date, inputFormatter)
    val zonedDateTime = localDateTime.atZone(ZoneId.of(importDate.timezone))
    return outputFormatter.format(zonedDateTime)
}