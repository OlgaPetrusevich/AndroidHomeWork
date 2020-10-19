package com.gmail.petrusevich.volha.homework8.city.domain

import java.util.*

class CityDomainModel(
        val cityName: String
) {
     var idCity: String = UUID.randomUUID().toString()
}