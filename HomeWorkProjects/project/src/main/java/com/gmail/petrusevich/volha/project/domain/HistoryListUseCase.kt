package com.gmail.petrusevich.volha.project.domain

import io.reactivex.Observable

interface HistoryListUseCase {

    fun getDateHistory(date: String): Observable<List<HistoryExerciseDomainModel>>

    fun getCategoryHistory(categoryName: String): Observable<List<HistoryExerciseDomainModel>>
}