package com.gmail.petrusevich.volha.project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_exercise.view.*

class ExerciseListAdapter : RecyclerView.Adapter<ExerciseListAdapter.ExerciseListViewHolder>() {

    private val exerciseDataModelList = mutableListOf<ExerciseDataModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false)
        return ExerciseListViewHolder(view)
    }

    override fun getItemCount(): Int = exerciseDataModelList.size

    override fun onBindViewHolder(holder: ExerciseListViewHolder, position: Int) {
        holder.bind(exerciseDataModelList[position])
    }

    fun updateExerciseList(exerciseList: List<ExerciseDataModel>) {
        exerciseDataModelList.apply {
            clear()
            addAll(exerciseList)
        }
        notifyDataSetChanged()
    }

    class ExerciseListViewHolder(
            itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(exerciseDataModel: ExerciseDataModel) {
            itemView.viewCategoryExercise.text = exerciseDataModel.nameExercise
            itemView.viewNameExercise.text = exerciseDataModel.nameExercise
            itemView.viewDescriptionExercise.text = exerciseDataModel.descriptionExercise
        }
    }

}