 package com.yun.taipeizooooo.events

 import com.yun.taipeizooooo.models.Animal
 import com.yun.taipeizooooo.models.DistrictData
 import com.yun.taipeizooooo.models.Plant

 sealed interface TaipeiZooActivityEvents {
     data class ToDistrictDetail(val data: DistrictData) : TaipeiZooActivityEvents
     data class ToItemDetailWithPlant(val item: Plant) : TaipeiZooActivityEvents
     data class ToItemDetailWithAnimal(val item: Animal) : TaipeiZooActivityEvents
}