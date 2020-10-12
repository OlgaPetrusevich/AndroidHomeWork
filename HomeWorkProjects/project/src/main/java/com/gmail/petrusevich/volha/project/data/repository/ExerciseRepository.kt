package com.gmail.petrusevich.volha.project.data.repository

import com.gmail.petrusevich.volha.project.domain.ExerciseDomainModel
import io.reactivex.Observable

interface ExerciseRepository {

    fun getExerciseList(idCategory: String): Observable<List<ExerciseDomainModel>>

    fun getExerciseDescription(idExercise: String): Observable<ExerciseDomainModel>


}