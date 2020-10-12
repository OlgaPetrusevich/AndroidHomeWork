package com.gmail.petrusevich.volha.project.domain

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*


class HistoryExerciseDomainModel(
        val date: String,
        val exerciseId: String,
        val categoryId: String,
        val setId: String
)
//{
//    var historyId: String = UUID.randomUUID().toString()
//}