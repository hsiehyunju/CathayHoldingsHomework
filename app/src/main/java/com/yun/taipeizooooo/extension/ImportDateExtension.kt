package com.yun.taipeizooooo.extension

import com.yun.taipeizooooo.models.ImportDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun ImportDate.getFormattedUpdateDate(): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")
    val outputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
    val localDateTime = LocalDateTime.parse(date, inputFormatter)
    val zonedDateTime = localDateTime.atZone(ZoneId.of(timezone))
    return outputFormatter.format(zonedDateTime)
}