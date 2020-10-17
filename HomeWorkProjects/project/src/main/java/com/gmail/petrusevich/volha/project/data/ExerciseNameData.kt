package com.gmail.petrusevich.volha.project.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "ExerciseName")
class ExerciseNameData(
        val exerciseName: String,
        val muscleName: String,
        @PrimaryKey
        val id: String
)