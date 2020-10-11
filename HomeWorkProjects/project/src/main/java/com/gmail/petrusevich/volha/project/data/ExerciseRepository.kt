package com.gmail.petrusevich.volha.project.data

import com.gmail.petrusevich.volha.project.data.ExerciseDataModel
import com.gmail.petrusevich.volha.project.domain.ExerciseDomainModel
import io.reactivex.Observable
import io.reactivex.Single

interface ExerciseRepository {

    fun getExerciseList(idCategory: String): Observable<List<ExerciseDomainModel>>

    fun getExerciseDescription(idExercise: String): Observable<ExerciseDomainModel>


}