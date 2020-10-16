package com.gmail.petrusevich.volha.project.presentation.historylist

import com.gmail.petrusevich.volha.project.data.CategoryType
import com.gmail.petrusevich.volha.project.presentation.HistoryExercisesViewModel


class HistoryListController {

    fun showHistory(viewModel: HistoryExercisesViewModel, param: String) {
        var isCategory = false
        for (i in CategoryType.values()) {
            if (param == i.ordinal.toString()) {
                isCategory = true
            }
        }
        if (isCategory) {
            viewModel.getCategoryHistory(param)
        } else {
            viewModel.getDateHistory(param)
        }
    }


}