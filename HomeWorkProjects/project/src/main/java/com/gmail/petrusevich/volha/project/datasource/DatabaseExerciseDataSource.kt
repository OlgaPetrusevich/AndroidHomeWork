package com.gmail.petrusevich.volha.project.datasource

import android.content.Context
import com.gmail.petrusevich.volha.project.data.ExerciseDataModel
import com.gmail.petrusevich.volha.project.datasource.database.ExerciseDatabase
import io.reactivex.*
import io.reactivex.schedulers.Schedulers

class DatabaseExerciseDataSource(
        context: Context
) : ExerciseDataSource {

    private val exerciseDao = ExerciseDatabase.getInstance(context)?.getExerciseDao()

    override fun getExercises(): Observable<List<ExerciseDataModel>> {
        return Observable.create(ObservableOnSubscribe<List<ExerciseDataModel>>() {
            val listExercise: List<ExerciseDataModel>? = exerciseDao?.getAllExercise()
            if (listExercise != null) {
                it.onNext(exerciseDao?.getAllExercise()!!)
            } else {
                it.onNext(emptyList())
            }
        }).subscribeOn(Schedulers.computation())

    }
}