package com.gmail.petrusevich.volha.homework8.city.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "CityList")
class CityDataModel(
        val cityName: String
) {
    @PrimaryKey
    var idCity: String = UUID.randomUUID().toString()
}