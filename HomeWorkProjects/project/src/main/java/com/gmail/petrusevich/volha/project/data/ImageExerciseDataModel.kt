package com.gmail.petrusevich.volha.project.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Images")
class ImageExerciseDataModel (
        val urlToImage: String,
        @PrimaryKey
        val imageId: String
)