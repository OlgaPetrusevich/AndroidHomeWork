package com.gmail.petrusevich.volha.project.datasource.historydatasource

import android.content.Context
import com.gmail.petrusevich.volha.project.data.HistoryDatabaseModel
import com.gmail.petrusevich.volha.project.data.HistoryExerciseDataModel
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.schedulers.Schedulers

class DatabaseHistoryDataSource(
        context: Context
) : HistoryExercisesDataSource{

    private val historyExercisesDao = HistoryExercisesDatabase.getInstance(context)?.getHistoryExercisesDao()

    override fun getDateHistory(date: String): Observable<List<HistoryDatabaseModel>> {
        return Observable.create(ObservableOnSubscribe<List<HistoryDatabaseModel>>() {
            val listExercise: List<HistoryDatabaseModel>? = historyExercisesDao?.getDateHistory(date)
            if (listExercise != null) {
                it.onNext(listExercise)
            } else {
                it.onNext(emptyList())
            }
        }).subscribeOn(Schedulers.computation())
    }

    override fun getCategoryHistory(categoryName: String): Observable<List<HistoryDatabaseModel>> {
        return Observable.create(ObservableOnSubscribe<List<HistoryDatabaseModel>>() {
            val listExercise: List<HistoryDatabaseModel>? = historyExercisesDao?.getCategoryHistory(categoryName)
            if (listExercise != null) {
                it.onNext(listExercise)
            } else {
                it.onNext(emptyList())
            }
        }).subscribeOn(Schedulers.computation())
    }

    override fun insertExerciseToHistory(historyExerciseData: HistoryExerciseDataModel){
        Observable.create(ObservableOnSubscribe<HistoryExerciseDataModel>() {
            historyExercisesDao?.insertHistoryData(historyExerciseData)
        })
                .subscribeOn(Schedulers.computation())
                .subscribe()
    }


}