package com.gmail.petrusevich.volha.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class WorkoutListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_gym_tab, container, false)


    companion object {
        const val TAG = "WorkoutListFragment"
        fun gteInstance() = WorkoutListFragment()
    }
}
