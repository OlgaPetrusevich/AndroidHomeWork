package com.gmail.petrusevich.volha.project.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "HistoryExercises", foreignKeys = [ForeignKey(entity = ExerciseDataModel::class, parentColumns = ["id"],
        childColumns = ["exerciseId"]), ForeignKey(entity = CategoryExerciseData::class, parentColumns = ["categoryId"],
        childColumns = ["categoryId"]), ForeignKey(entity = SetsExerciseData::class, parentColumns = ["setId"],
        childColumns = ["setId"])])
class HistoryExerciseDataModel(
        val date: String,
        val exerciseId: String,
        val categoryId: String,
        val setId: String
) {

    @PrimaryKey
    var historyId: String = UUID.randomUUID().toString()
}