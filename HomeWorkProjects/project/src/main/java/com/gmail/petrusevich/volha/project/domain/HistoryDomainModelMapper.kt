package com.gmail.petrusevich.volha.project.domain

import com.gmail.petrusevich.volha.project.data.HistoryDatabaseModel
import java.text.SimpleDateFormat
import java.util.*


class HistoryDomainModelMapper : (List<HistoryDatabaseModel>) -> List<HistoryExerciseDomainModel> {
    override fun invoke(historyDataModelList: List<HistoryDatabaseModel>): List<HistoryExerciseDomainModel> =
            historyDataModelList.map { item ->
                HistoryExerciseDomainModel(
                        date = item.date,
                        exerciseName = item.exerciseName,
                        categoryName = item.categoryName,
                        timeExercise = item.timeExercise,
                        setId = item.setId,
                        maxWeight = item.maxWeight
                )
            }

}