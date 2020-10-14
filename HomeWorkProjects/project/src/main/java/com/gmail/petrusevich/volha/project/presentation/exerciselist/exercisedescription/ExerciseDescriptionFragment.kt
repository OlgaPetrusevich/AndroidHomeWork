package com.gmail.petrusevich.volha.project.presentation.exerciselist.exercisedescription

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
import com.gmail.petrusevich.volha.project.presentation.ExerciseViewModel
import com.gmail.petrusevich.volha.project.presentation.HistoryExercisesViewModel
import com.gmail.petrusevich.volha.project.presentation.exerciselist.itemmodel.ExerciseItemModel
import kotlinx.android.synthetic.main.activity_exercises_list.*
import kotlinx.android.synthetic.main.fragment_exercise_description.*

class ExerciseDescriptionFragment : Fragment(), View.OnClickListener {

    private val exerciseViewModel by lazy { ViewModelProvider(this).get(ExerciseViewModel::class.java) }
    private val historyExercisesViewModel by lazy { ViewModelProvider(this).get(HistoryExercisesViewModel::class.java) }
    private val idExercise by lazy { arguments?.getString("key") }
    private val categoryType by lazy { arguments?.getString("keyCategory") }
    private val exerciseDescriptionController by lazy { ExerciseDescriptionController() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_exercise_description, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewLifecycleOwner) {
            exerciseViewModel.exerciseDescriptionLiveData.observe(this, Observer { item ->
                setDescription(item)
            })
            exerciseViewModel.exercisesErrorLiveData.observe(this, Observer { throwable ->
                Log.d("Error", throwable.message!!)
            })
        }
        exerciseViewModel.getExerciseDescription(idExercise!!)
        viewStartExerciseButton.setOnClickListener(this)
        viewEndExerciseButton.setOnClickListener(this)
    }

    private fun setDescription(exerciseItemModel: ExerciseItemModel) {
        Glide.with(context!!)
                .load(exerciseItemModel.urlToImage)
                .into(viewImageExercise)
        viewTextDescription.text = exerciseItemModel.exerciseDescription
        activity?.viewActivityExercisesToolbar?.title = exerciseItemModel.exerciseName
    }


    companion object {
        const val TAG = "ExerciseDescriptionFragment"
        fun getInstance() = ExerciseDescriptionFragment()
    }

    override fun onClick(view: View?) {
        when (view) {
            viewStartExerciseButton -> {
                exerciseDescriptionController.getStartTime()
                exerciseDescriptionController.updateSetAmount(viewSetAmountExercise)
                exerciseDescriptionController.getWeightSet(viewWeightText)
            }
            viewEndExerciseButton -> {
                val historyExerciseDataModel = exerciseDescriptionController.writeHistoryExercise(idExercise!!, categoryType!!, viewSetAmountExercise)
                historyExercisesViewModel.insertExerciseToHistory(historyExerciseDataModel)
                activity?.supportFragmentManager?.popBackStack()
            }
        }


    }
}
