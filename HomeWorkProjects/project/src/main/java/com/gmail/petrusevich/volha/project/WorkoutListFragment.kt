package com.gmail.petrusevich.volha.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_gym_tab.*

class WorkoutListFragment : Fragment() {

    private val exerciseViewModel by lazy { ViewModelProvider(this).get(ExerciseViewModel::class.java) }
    private val exerciseListAdapter by lazy { ExerciseListAdapter() }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_gym_tab, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewGymList.adapter = exerciseListAdapter
        exerciseViewModel.liveDataExerciseList.observe(viewLifecycleOwner, Observer {
            it.let {
                exerciseListAdapter.updateExerciseList(it)
            }
        })
        exerciseViewModel.fetchExerciseList()
    }


    companion object {
        const val TAG = "WorkoutListFragment"
        fun gteInstance() = WorkoutListFragment()
    }
}
