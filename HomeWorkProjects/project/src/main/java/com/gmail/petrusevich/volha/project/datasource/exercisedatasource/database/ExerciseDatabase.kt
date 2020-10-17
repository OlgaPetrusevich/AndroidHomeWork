package com.gmail.petrusevich.volha.project.datasource.exercisedatasource.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gmail.petrusevich.volha.project.data.ExerciseDataModel

@Database(entities = [ExerciseDataModel::class], version = 1)
abstract class ExerciseDatabase : RoomDatabase() {

    abstract fun getExerciseDao(): ExerciseDao

    companion object {
        var instance: ExerciseDatabase? = null

        fun getInstance(context: Context): ExerciseDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(context, ExerciseDatabase::class.java, "ExerciseDatabase18")
                        .createFromAsset("ExerciseDB.db")
                        .build()
            }
            return instance
        }

    }
}