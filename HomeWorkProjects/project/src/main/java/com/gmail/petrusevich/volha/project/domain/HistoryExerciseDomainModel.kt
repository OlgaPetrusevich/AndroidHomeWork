package com.gmail.petrusevich.volha.project.domain

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*


class HistoryExerciseDomainModel(
        val date: String,
        val exerciseName: String,
        val timeExercise: Long,
        val categoryName: String,
        val setId: String,
        val maxWeight: String
)
//{
//    var historyId: String = UUID.randomUUID().toString()
//}