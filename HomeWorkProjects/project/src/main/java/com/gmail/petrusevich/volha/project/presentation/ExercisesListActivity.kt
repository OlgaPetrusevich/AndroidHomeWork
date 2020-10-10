package com.gmail.petrusevich.volha.project.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gmail.petrusevich.volha.project.R

class ExercisesListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercises_list)
        loadFragment(WorkoutListFragment.getInstance())
    }


    private fun loadFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentExercisesContainer, fragment)
                .commit()
        return true
    }

}