package com.gmail.petrusevich.volha.project.domain

import android.content.Context
import com.gmail.petrusevich.volha.project.data.repository.ExerciseRepository
import com.gmail.petrusevich.volha.project.data.repository.ExerciseRepositoryImpl
import io.reactivex.Observable

class ExerciseListUseCaseImpl(
        context: Context
) : ExerciseListUseCase {

    private val repository: ExerciseRepository = ExerciseRepositoryImpl(context)

    override fun getExerciseList(idCategory: String): Observable<List<ExerciseDomainModel>> =
            repository.getExerciseList(idCategory)

    override fun getExerciseDescription(idExercise: String): Observable<ExerciseDomainModel> =
            repository.getExerciseDescription(idExercise)


}