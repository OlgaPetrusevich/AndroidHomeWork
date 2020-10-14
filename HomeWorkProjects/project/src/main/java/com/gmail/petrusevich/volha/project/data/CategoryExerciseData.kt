package com.gmail.petrusevich.volha.project.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "CategoryExercise")
class CategoryExerciseData(
        val categoryName: String,
        @PrimaryKey
        val categoryId: String
)