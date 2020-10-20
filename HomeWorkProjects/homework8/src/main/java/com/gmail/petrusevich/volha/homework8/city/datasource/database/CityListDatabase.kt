package com.gmail.petrusevich.volha.homework8.city.datasource.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gmail.petrusevich.volha.homework8.city.data.CityDataModel

@Database(entities = [CityDataModel::class], version = 1)
abstract class CityListDatabase : RoomDatabase() {

    abstract fun getCityListDao(): CityListDao

    companion object {
        var instance: CityListDatabase? = null

        fun getInstance(context: Context): CityListDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(context, CityListDatabase::class.java, "CityListDB")
                        .build()
            }
            return instance
        }
    }
}