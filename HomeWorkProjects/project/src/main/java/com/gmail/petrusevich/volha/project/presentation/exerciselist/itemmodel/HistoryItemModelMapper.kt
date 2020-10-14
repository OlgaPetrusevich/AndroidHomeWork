package com.gmail.petrusevich.volha.project.presentation.exerciselist.itemmodel

import com.gmail.petrusevich.volha.project.domain.HistoryExerciseDomainModel
import java.text.SimpleDateFormat
import java.util.*


class HistoryItemModelMapper : (List<HistoryExerciseDomainModel>) -> List<HistoryExerciseItemModel> {
    override fun invoke(historyDataModelList: List<HistoryExerciseDomainModel>): List<HistoryExerciseItemModel> =
            historyDataModelList.map { item ->
                HistoryExerciseItemModel(
                        date = formatter.format(item.date.toLong()),
                        exerciseName = item.exerciseName,
                        categoryName = item.categoryName,
                        timeExercise = item.timeExercise,
                        setId = item.setId,
                        maxWeight = item.maxWeight
                )
            }

//    formatter.format(item.date.toLong())
    private val format = "dd/MM/yyyy"
    private val formatter = SimpleDateFormat(format, Locale.getDefault())

}