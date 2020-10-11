package com.gmail.petrusevich.volha.project.data

import android.content.Context
import com.gmail.petrusevich.volha.project.datasource.DatabaseExerciseDataSource
import com.gmail.petrusevich.volha.project.datasource.ExerciseDataSource
import com.gmail.petrusevich.volha.project.domain.ExerciseDomainModel
import com.gmail.petrusevich.volha.project.domain.ExerciseDomainModelMapper
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


class ExerciseRepositoryImpl(
        context: Context
) : ExerciseRepository {

    private val exerciseDataSource: ExerciseDataSource = DatabaseExerciseDataSource(context)
    private val exerciseDomainModelMapper: (List<ExerciseDataModel>) -> List<ExerciseDomainModel> = ExerciseDomainModelMapper()

    override fun getExerciseList(idCategory: String): Observable<List<ExerciseDomainModel>> =
            exerciseDataSource.getExercises(idCategory)
                    .subscribeOn(Schedulers.computation())
                    .map { list -> exerciseDomainModelMapper(list) }

    override fun getExerciseDescription(idExercise: String): Observable<ExerciseDomainModel> =
            exerciseDataSource.getExerciseDescription(idExercise)
                    .subscribeOn(Schedulers.computation())
                    .map { exerciseData -> exerciseDomainModelMapper(mutableListOf(exerciseData))[0] }


}