package com.gmail.petrusevich.volha.project.datasource.historydatasource

import io.reactivex.Observable

interface HistoryExercisesDataSource<T> {

    fun getDateHistory(date: String): Observable<List<T>>

    fun getCategoryHistory(categoryName: String):  Observable<List<T>>


}