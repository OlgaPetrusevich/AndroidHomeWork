package com.gmail.petrusevich.volha.project.data

import androidx.room.Entity

@Entity(tableName = "Categories")
class CategoryExerciseData(
        val categoryName: String,
        val id: String
)