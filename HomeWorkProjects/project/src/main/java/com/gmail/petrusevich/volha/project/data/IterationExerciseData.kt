package com.gmail.petrusevich.volha.project.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "IterationExercise")
class IterationExerciseData(
        val iterationExercise: String,
        @PrimaryKey
        val iterationId: String
)