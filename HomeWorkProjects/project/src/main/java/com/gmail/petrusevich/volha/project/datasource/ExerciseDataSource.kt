package com.gmail.petrusevich.volha.project.datasource

import com.gmail.petrusevich.volha.project.data.ExerciseDataModel
import io.reactivex.Observable
import io.reactivex.Single

interface ExerciseDataSource {

    fun getExercises(idCategory: String): Observable<List<ExerciseDataModel>>

    fun getExerciseDescription(idExercise: String): Observable<ExerciseDataModel>


}