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

    override fun getExercises(idCategory: String): Observable<List<ExerciseDataModel>> {
        return Observable.create(ObservableOnSubscribe<List<ExerciseDataModel>>() {
            val listExercise: List<ExerciseDataModel>? = exerciseDao?.getCategoryExercises(idCategory)
            if (listExercise != null) {
                it.onNext(listExercise)
            } else {
                it.onNext(emptyList())
            }
        }).subscribeOn(Schedulers.computation())

    }

    override fun getExerciseDescription(idExercise: String): Observable<ExerciseDataModel> {
        return Observable.create(ObservableOnSubscribe<ExerciseDataModel>() {
            val exerciseDataModel: ExerciseDataModel? = exerciseDao?.getExercise(idExercise)
            if(exerciseDataModel != null){
                it.onNext(exerciseDataModel)
            } else{
                it.onNext(emptyList<ExerciseDataModel>()[0])
            }
        }).subscribeOn(Schedulers.computation())
    }
}