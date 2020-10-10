package com.gmail.petrusevich.volha.project.presentation.exerciselist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.petrusevich.volha.project.R
import kotlinx.android.synthetic.main.item_exercise.view.*

class ExerciseListAdapter : RecyclerView.Adapter<ExerciseListAdapter.ExerciseListViewHolder>() {

    private val exerciseItemList = mutableListOf<ExerciseItemModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false)
        return ExerciseListViewHolder(view)
    }

    override fun getItemCount(): Int = exerciseItemList.size

    override fun onBindViewHolder(holder: ExerciseListViewHolder, position: Int) {
        holder.bind(exerciseItemList[position])
    }

    fun updateExerciseList(exerciseList: List<ExerciseItemModel>) {
        exerciseItemList.apply {
            clear()
            addAll(exerciseList)
        }
        notifyDataSetChanged()
    }

    class ExerciseListViewHolder(
            itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(exerciseItemList: ExerciseItemModel) {
            itemView.viewCategoryExercise.text = exerciseItemList.categoryName
            itemView.viewNameExercise.text = exerciseItemList.exerciseName
            itemView.viewDescriptionExercise.text = exerciseItemList.exerciseDescription
        }
    }

}