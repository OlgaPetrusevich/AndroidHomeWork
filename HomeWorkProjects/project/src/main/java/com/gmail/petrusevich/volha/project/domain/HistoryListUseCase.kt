package com.gmail.petrusevich.volha.project.domain

import com.gmail.petrusevich.volha.project.data.HistoryExerciseDataModel
import io.reactivex.Observable

interface HistoryListUseCase {

    fun getDateHistory(date: String): Observable<List<HistoryExerciseDomainModel>>

    fun getCategoryHistory(categoryName: String): Observable<List<HistoryExerciseDomainModel>>

    fun insertExerciseToHistory(historyExerciseData: HistoryExerciseDataModel)

    fun getAllDate(): Observable<List<String>>
}