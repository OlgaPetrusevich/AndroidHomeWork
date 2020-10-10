package com.gmail.petrusevich.volha.project.domain

import android.content.Context
import com.gmail.petrusevich.volha.project.data.ExerciseRepository
import com.gmail.petrusevich.volha.project.data.ExerciseRepositoryImpl
import io.reactivex.Observable

class ExerciseListUseCaseImpl(
        context: Context
) : ExerciseListUseCase {

    private val repository: ExerciseRepository = ExerciseRepositoryImpl(context)

    override fun getExerciseList(): Observable<List<ExerciseDomainModel>> =
            repository.getExerciseList()

}