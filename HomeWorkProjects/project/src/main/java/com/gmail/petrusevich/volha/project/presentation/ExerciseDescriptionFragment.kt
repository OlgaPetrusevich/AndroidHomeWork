package com.gmail.petrusevich.volha.project.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.gmail.petrusevich.volha.project.R
import com.gmail.petrusevich.volha.project.presentation.exerciselist.ExerciseItemModel
import kotlinx.android.synthetic.main.fragment_exercise_description.*

class ExerciseDescriptionFragment : Fragment() {

    private val exerciseViewModel by lazy { ViewModelProvider(this).get(ExerciseViewModel::class.java) }
    private val idExercise by lazy { arguments?.getString("key") }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_exercise_description, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewLifecycleOwner) {
            exerciseViewModel.exerciseDescriptionLiveData.observe(this, Observer {
                item -> setDescription(item)
            })
            exerciseViewModel.exercisesErrorLiveData.observe(this, Observer { throwable ->
                Log.d("Error", throwable.message!!)
            })
        }
        exerciseViewModel.getExerciseDescription(idExercise!!)

    }

    private fun setDescription(exerciseItemModel: ExerciseItemModel){
        Glide.with(context!!)
                .load(exerciseItemModel.urlToImage)
                .into(viewImageExercise)
        viewTextDescription.text = exerciseItemModel.exerciseDescription

    }




    companion object {
        const val TAG = "ExerciseDescriptionFragment"
        fun getInstance() = ExerciseDescriptionFragment()
    }
}
