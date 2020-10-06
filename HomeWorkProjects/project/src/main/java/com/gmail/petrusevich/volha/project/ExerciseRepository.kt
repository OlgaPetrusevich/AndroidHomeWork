package com.gmail.petrusevich.volha.project

import io.reactivex.Single

interface ExerciseRepository {

    fun getExerciseList(language: String): Single<List<ExerciseDataModel>>

}