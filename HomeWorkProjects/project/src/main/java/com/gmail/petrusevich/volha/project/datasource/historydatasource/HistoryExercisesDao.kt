package com.gmail.petrusevich.volha.project.datasource.historydatasource

import androidx.room.*
import com.gmail.petrusevich.volha.project.data.HistoryExerciseDataModel

@Dao
interface HistoryExercisesDao {

    @Query("SELECT * FROM HistoryExercises, Sets, CategoryExercise, Exercise WHERE date = :date AND Sets.setId = HistoryExercises.setId " +
            "AND CategoryExercise.categoryId = HistoryExercises.categoryId AND Exercise.id = HistoryExercises.exerciseId")
    fun getDateHistory(date: String): List<HistoryExerciseDataModel>

    @Query("SELECT * FROM HistoryExercises, Sets, CategoryExercise, Exercise WHERE categoryId = :idCategory " +
            "AND Sets.setId = HistoryExercises.setId AND CategoryExercise.categoryId = HistoryExercises.categoryId " +
            "AND Exercise.id = HistoryExercises.exerciseId")
    fun getCategoryHistory(idCategory: String): List<HistoryExerciseDataModel>

    @Insert
    fun insertHistoryData(historyExerciseDataModel: HistoryExerciseDataModel)

    @Update
    fun updateHistoryData(historyExerciseDataModel: HistoryExerciseDataModel)

    @Delete
    fun deleteHistoryData(historyExerciseDataModel: HistoryExerciseDataModel)

}