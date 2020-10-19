package com.gmail.petrusevich.volha.homework8.city.domain

import com.gmail.petrusevich.volha.homework8.city.data.CityDataModel

class CityDomainModelMapper : (List<CityDataModel>) -> List<CityDomainModel> {

    override fun invoke(cityList: List<CityDataModel>): List<CityDomainModel> =
            cityList.map { item -> CityDomainModel(
                    cityName = item.cityName
            ) }
}