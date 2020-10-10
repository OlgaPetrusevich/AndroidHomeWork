package com.gmail.petrusevich.volha.project.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gmail.petrusevich.volha.project.R
import com.gmail.petrusevich.volha.project.presentation.exerciselist.ExerciseListAdapter
import com.gmail.petrusevich.volha.project.presentation.exerciselist.ExerciseViewModel
import kotlinx.android.synthetic.main.fragment_exercises.*

class WorkoutListFragment : Fragment() {

    private val exerciseViewModel by lazy { ViewModelProvider(this).get(ExerciseViewModel::class.java) }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_exercises, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewGymList.adapter = ExerciseListAdapter()
        with(viewLifecycleOwner) {
            exerciseViewModel.exercisesLiveData.observe(this, Observer { items ->
                (viewGymList.adapter as? ExerciseListAdapter)?.updateExerciseList(items)
            })
            exerciseViewModel.exercisesErrorLiveData.observe(this, Observer { throwable ->
                Log.d("Error", throwable.message!!)
            })
        }
        exerciseViewModel.getAllExercises()
    }


    companion object {
        const val TAG = "WorkoutListFragment"
        fun getInstance() = WorkoutListFragment()
    }
}
