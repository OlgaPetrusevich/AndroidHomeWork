package com.gmail.petrusevich.volha.homework8.city.presentation.itemmodel

import com.gmail.petrusevich.volha.homework8.city.domain.CityDomainModel

class CityItemModelMapper : (List<CityDomainModel>) -> List<CityItemModel> {

    override fun invoke(cityList: List<CityDomainModel>): List<CityItemModel> =
            cityList.map { item ->
                CityItemModel(
                        cityName = item.cityName
                )
            }
}