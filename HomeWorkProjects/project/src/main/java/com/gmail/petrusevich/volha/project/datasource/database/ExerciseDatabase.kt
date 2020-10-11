package com.gmail.petrusevich.volha.project.datasource.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gmail.petrusevich.volha.project.data.ExerciseDataModel
import com.gmail.petrusevich.volha.project.data.ImageExerciseDataModel
import com.gmail.petrusevich.volha.project.data.IterationExerciseData
import com.gmail.petrusevich.volha.project.data.SetsExerciseData

@Database(entities = [ExerciseDataModel::class, ImageExerciseDataModel::class, SetsExerciseData::class,
    IterationExerciseData::class], version = 1)
abstract class ExerciseDatabase : RoomDatabase() {

    abstract fun getExerciseDao(): ExerciseDao

    companion object {
        var instance: ExerciseDatabase? = null

        fun getInstance(context: Context): ExerciseDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(context, ExerciseDatabase::class.java, "ExerciseDatabase9")
                        .createFromAsset("ExerciseDatabasecopy.db")
                        .build()
            }
            return instance
        }

    }
}