package com.gmail.petrusevich.volha.project.presentation.exerciselist

import com.gmail.petrusevich.volha.project.domain.ExerciseDomainModel


class ExerciseItemModelMapper : (List<ExerciseDomainModel>) -> List<ExerciseItemModel> {

    override fun invoke(exerciseList: List<ExerciseDomainModel>): List<ExerciseItemModel> =
            exerciseList.map { item ->
                ExerciseItemModel(
                        categoryName = item.categoryName,
                        exerciseName = item.exerciseName,
                        exerciseDescription = item.exerciseDescription,
                        setsExercise = item.setsExercise,
                        iterationExercise = item.iterationExercise,
                        urlToImage = item.urlToImage,
                        id = item.id
                )
            }
}