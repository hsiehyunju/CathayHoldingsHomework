 package com.yun.taipeizooooo.events

 import com.yun.taipeizooooo.models.DistrictData

 sealed interface TaipeiZooActivityEvents {
     data class ToDistrictDetail(val data: DistrictData) : TaipeiZooActivityEvents
     data class ToItemDetail(val item: Any) : TaipeiZooActivityEvents
}