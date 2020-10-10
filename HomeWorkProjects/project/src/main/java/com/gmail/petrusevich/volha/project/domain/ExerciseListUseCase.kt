package com.gmail.petrusevich.volha.project.domain

import io.reactivex.Observable

interface ExerciseListUseCase {

    fun getExerciseList(): Observable<List<ExerciseDomainModel>>
}