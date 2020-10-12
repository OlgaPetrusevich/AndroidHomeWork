package com.gmail.petrusevich.volha.project.domain

import android.content.Context
import com.gmail.petrusevich.volha.project.data.repository.HistoryExercisesRepository
import com.gmail.petrusevich.volha.project.data.repository.HistoryExercisesRepositoryImpl
import io.reactivex.Observable

class HistoryListUseCaseImpl(
        context: Context
) : HistoryListUseCase {

    private val repository: HistoryExercisesRepository = HistoryExercisesRepositoryImpl(context)

    override fun getDateHistory(date: String): Observable<List<HistoryExerciseDomainModel>> =
            repository.getDateHistory(date)


    override fun getCategoryHistory(categoryName: String): Observable<List<HistoryExerciseDomainModel>> =
            repository.getCategoryHistory(categoryName)


}