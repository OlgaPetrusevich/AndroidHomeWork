package com.gmail.petrusevich.volha.project.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Exercise")
class ExerciseDataModel(
        val categoryName: String,
        val exerciseName: String,
        val exerciseDescription: String,
        val setsExercise: String,
        val iterationExercise: String,
        val urlToImage: String,
        @PrimaryKey
        val id: String
)

