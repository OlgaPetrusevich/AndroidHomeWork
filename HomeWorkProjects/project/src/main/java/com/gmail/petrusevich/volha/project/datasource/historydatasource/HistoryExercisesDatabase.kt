package com.gmail.petrusevich.volha.project.datasource.historydatasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gmail.petrusevich.volha.project.data.*

@Database(entities = [ExerciseDataModel::class, HistoryExerciseDataModel::class, SetsExerciseData::class,
    CategoryExerciseData::class], version = 1)
abstract class HistoryExercisesDatabase : RoomDatabase() {

    abstract fun getHistoryExercisesDao(): HistoryExercisesDao

    companion object {
        var instance: HistoryExercisesDatabase? = null

        fun getInstance(context: Context): HistoryExercisesDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(context, HistoryExercisesDatabase::class.java, "HistoryExercisesDatabase")
                        .build()
            }
            return instance
        }

    }
}