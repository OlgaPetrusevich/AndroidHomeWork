package com.gmail.petrusevich.volha.project.presentation.exerciselist.exercisedescription

import android.widget.EditText
import android.widget.TextView
import com.gmail.petrusevich.volha.project.data.HistoryExerciseDataModel
import java.util.*

class ExerciseDescriptionController {

    private val timeStart: Long by lazy { Date().time }
    private val weightList = mutableListOf<Int>()

    private fun getTimeExercise(): Long {
        val dateFinish = Date().time
        return dateFinish - timeStart
    }

    fun updateSetAmount(view: TextView) {
        var currentAmount: Int = view.text.toString().toInt()
        val amountSets = ++currentAmount
        view.text = amountSets.toString()
    }

    fun getStartTime(): Long = timeStart

    fun writeHistoryExercise(exerciseId: String, categoryId: String, view: TextView): HistoryExerciseDataModel {
        val sets = view.text.toString()
        return HistoryExerciseDataModel(timeStart.toString(), exerciseId, getTimeExercise(), sets, categoryId, getMaxWeight())
    }

    fun getWeightSet(view: EditText) {
        val weight: Int = view.text.toString().toInt()
        weightList.add(weight)
    }

    private fun getMaxWeight(): String {
        val sortList = weightList.sorted()
        return sortList.last().toString()
    }


}