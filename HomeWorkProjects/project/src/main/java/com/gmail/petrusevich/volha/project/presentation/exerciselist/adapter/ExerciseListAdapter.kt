package com.gmail.petrusevich.volha.project.presentation.exerciselist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.petrusevich.volha.project.R
import com.gmail.petrusevich.volha.project.presentation.exerciselist.itemmodel.ExerciseItemModel
import kotlinx.android.synthetic.main.item_exercise.view.*

class ExerciseListAdapter(
        private val itemOnClickListener: ItemOnClickListener
) : RecyclerView.Adapter<ExerciseListAdapter.ExerciseListViewHolder>() {

    private val exerciseItemList = mutableListOf<ExerciseItemModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false)
        return ExerciseListViewHolder(view, itemOnClickListener)
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
            itemView: View,  private val itemOnClickListener: ItemOnClickListener
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(exerciseItemList: ExerciseItemModel) {
            itemView.viewNameExercise.text = exerciseItemList.exerciseName
        }

        override fun onClick(view: View?) {
            itemOnClickListener.itemOnClick(adapterPosition)
        }
    }

}