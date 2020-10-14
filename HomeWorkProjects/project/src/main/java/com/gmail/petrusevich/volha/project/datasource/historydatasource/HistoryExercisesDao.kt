package com.gmail.petrusevich.volha.project.datasource.historydatasource

import androidx.room.*
import com.gmail.petrusevich.volha.project.data.HistoryDatabaseModel
import com.gmail.petrusevich.volha.project.data.HistoryExerciseDataModel

@Dao
interface HistoryExercisesDao {

    @Query("SELECT * FROM HistoryExercises, CategoryExercise, ExerciseName WHERE HistoryExercises.date = :date AND CategoryExercise.categoryId = HistoryExercises.categoryId AND ExerciseName.id = HistoryExercises.exerciseId")
    fun getDateHistory(date: String): List<HistoryDatabaseModel>

    @Query("SELECT * FROM HistoryExercises, CategoryExercise, ExerciseName WHERE HistoryExercises.categoryId = :idCategory AND CategoryExercise.categoryId = HistoryExercises.categoryId AND ExerciseName.id = HistoryExercises.exerciseId")
    fun getCategoryHistory(idCategory: String): List<HistoryDatabaseModel>

    @Insert
    fun insertHistoryData(historyExerciseDataModel: HistoryExerciseDataModel)

    @Update
    fun updateHistoryData(historyExerciseDataModel: HistoryExerciseDataModel)

    @Delete
    fun deleteHistoryData(historyExerciseDataModel: HistoryExerciseDataModel)



}