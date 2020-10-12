package com.gmail.petrusevich.volha.project.data.repository

import android.content.Context
import com.gmail.petrusevich.volha.project.data.HistoryExerciseDataModel
import com.gmail.petrusevich.volha.project.datasource.historydatasource.DatabaseHistoryDataSource
import com.gmail.petrusevich.volha.project.datasource.historydatasource.HistoryExercisesDataSource
import com.gmail.petrusevich.volha.project.domain.HistoryDomainModelMapper
import com.gmail.petrusevich.volha.project.domain.HistoryExerciseDomainModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


class HistoryExercisesRepositoryImpl(
        context: Context
) : HistoryExercisesRepository {

    private val historyExercisesDataSource: HistoryExercisesDataSource<HistoryExerciseDataModel> = DatabaseHistoryDataSource(context)
    private val historyDomainModelMapper: (List<HistoryExerciseDataModel>) -> List<HistoryExerciseDomainModel> = HistoryDomainModelMapper()

    override fun getDateHistory(date: String): Observable<List<HistoryExerciseDomainModel>> =
            historyExercisesDataSource.getDateHistory(date)
                    .subscribeOn(Schedulers.computation())
                    .map { list -> historyDomainModelMapper(list) }

    override fun getCategoryHistory(categoryName: String): Observable<List<HistoryExerciseDomainModel>> =
            historyExercisesDataSource.getCategoryHistory(categoryName)
                    .subscribeOn(Schedulers.computation())
                    .map { list -> historyDomainModelMapper(list) }
}