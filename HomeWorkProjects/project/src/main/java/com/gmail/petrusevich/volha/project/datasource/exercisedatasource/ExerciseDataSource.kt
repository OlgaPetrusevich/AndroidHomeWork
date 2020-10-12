package com.gmail.petrusevich.volha.project.datasource.exercisedatasource

import io.reactivex.Observable

interface ExerciseDataSource<T> {

    fun getExercises(param: String): Observable<List<T>>

    fun getExerciseDescription(param: String): Observable<T>


}