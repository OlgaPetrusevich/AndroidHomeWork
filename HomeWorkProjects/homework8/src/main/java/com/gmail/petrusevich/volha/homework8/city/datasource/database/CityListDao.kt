package com.gmail.petrusevich.volha.homework8.city.datasource.database

import androidx.room.*
import com.gmail.petrusevich.volha.homework8.city.data.CityDataModel

@Dao
interface CityListDao {

    @Query("SELECT * FROM CityList")
    fun getCityList(): List<CityDataModel>

    @Insert
    fun insertCity(cityDataModel: CityDataModel)

    @Update
    fun updateCity(cityDataModel: CityDataModel)

    @Delete
    fun deleteCity(cityDataModel: CityDataModel)
}