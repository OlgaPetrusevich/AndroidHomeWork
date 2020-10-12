package com.gmail.petrusevich.volha.project.presentation.exerciselist.itemmodel

import com.gmail.petrusevich.volha.project.domain.HistoryExerciseDomainModel


class HistoryItemModelMapper : (List<HistoryExerciseDomainModel>) -> List<HistoryExerciseItemModel> {
    override fun invoke(historyDataModelList: List<HistoryExerciseDomainModel>): List<HistoryExerciseItemModel> =
            historyDataModelList.map { item ->
                HistoryExerciseItemModel(
                        date = item.date,
                        exerciseId = item.exerciseId,
                        categoryId = item.categoryId,
                        setId = item.setId
                )
            }


}