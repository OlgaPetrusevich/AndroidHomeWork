package com.gmail.petrusevich.volha.project.datasource.historydatasource

import com.gmail.petrusevich.volha.project.data.HistoryDatabaseModel
import com.gmail.petrusevich.volha.project.data.HistoryExerciseDataModel
import io.reactivex.Observable

interface HistoryExercisesDataSource {

    fun getDateHistory(date: String): Observable<List<HistoryDatabaseModel>>

    fun getCategoryHistory(categoryName: String):  Observable<List<HistoryDatabaseModel>>

    fun insertExerciseToHistory(historyExerciseData: HistoryExerciseDataModel)


}