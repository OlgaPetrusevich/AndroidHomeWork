package com.gmail.petrusevich.volha.project.domain

import com.gmail.petrusevich.volha.project.data.HistoryExerciseDataModel


class HistoryDomainModelMapper : (List<HistoryExerciseDataModel>) -> List<HistoryExerciseDomainModel> {
    override fun invoke(historyDataModelList: List<HistoryExerciseDataModel>): List<HistoryExerciseDomainModel> =
            historyDataModelList.map { item ->
                HistoryExerciseDomainModel(
                        date = item.date,
                        exerciseId = item.exerciseId,
                        categoryId = item.categoryId,
                        setId = item.setId
                )
            }


}