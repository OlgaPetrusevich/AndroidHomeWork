package com.gmail.petrusevich.volha.project.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Exercise", foreignKeys = [ForeignKey(entity = ImageExerciseDataModel::class, parentColumns = ["imageId"],
        childColumns = ["urlToImage"]), ForeignKey(entity = SetsExerciseData::class, parentColumns = ["setId"],
        childColumns = ["setsExercise"]), ForeignKey(entity = IterationExerciseData::class, parentColumns = ["iterationId"],
        childColumns = ["iterationExercise"])])
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

