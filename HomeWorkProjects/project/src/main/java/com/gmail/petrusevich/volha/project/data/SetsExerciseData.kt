package com.gmail.petrusevich.volha.project.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Sets")
class SetsExerciseData(
        val setsExercise: String,
        @PrimaryKey
        val setId: String
)