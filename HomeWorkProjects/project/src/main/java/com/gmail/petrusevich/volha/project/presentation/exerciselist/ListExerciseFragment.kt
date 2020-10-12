package com.gmail.petrusevich.volha.project.presentation.exerciselist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gmail.petrusevich.volha.project.R
import com.gmail.petrusevich.volha.project.data.CategoryType
import com.gmail.petrusevich.volha.project.presentation.ExerciseViewModel
import com.gmail.petrusevich.volha.project.presentation.exerciselist.adapter.ExerciseListAdapter
import com.gmail.petrusevich.volha.project.presentation.exerciselist.adapter.ItemOnClickListener
import com.gmail.petrusevich.volha.project.presentation.exerciselist.exercisedescription.ExerciseDescriptionFragment
import kotlinx.android.synthetic.main.activity_exercises_list.*
import kotlinx.android.synthetic.main.fragment_exercises_list.*

class ListExerciseFragment : Fragment(), ItemOnClickListener {

    private val exerciseViewModel by lazy { ViewModelProvider(this).get(ExerciseViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_exercises_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewGymList.adapter = ExerciseListAdapter(this)
        with(viewLifecycleOwner) {
            exerciseViewModel.exercisesLiveData.observe(this, Observer { items ->
                (viewGymList.adapter as? ExerciseListAdapter)?.updateExerciseList(items)
            })
            exerciseViewModel.exercisesErrorLiveData.observe(this, Observer { throwable ->
                Log.d("Error", throwable.message!!)
            })
        }
        val categoryType = arguments?.getString("keyBundle")
        exerciseViewModel.getCategoryExercises(categoryType!!)
        getTitleToolbar(categoryType)
    }

    override fun itemOnClick(position: Int) {
        val idExercise: String = exerciseViewModel.exercisesLiveData.value!![position].id
        loadFragment(ExerciseDescriptionFragment.getInstance(), setBundle(idExercise))
    }

    private fun loadFragment(fragment: Fragment, bundle: Bundle): Boolean {
        fragment.arguments = bundle
        activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentExerciseContainer, fragment)
                .addToBackStack(null)
                .commit()
        return true
    }

    private fun getTitleToolbar(categoryType: String) {
        when (categoryType) {
            CategoryType.REAR_CATEGORY.ordinal.toString() -> activity?.viewActivityExercisesToolbar?.setTitle(R.string.category_rear_text)
            CategoryType.LEGS_CATEGORY.ordinal.toString() -> activity?.viewActivityExercisesToolbar?.setTitle(R.string.category_legs_text)
            CategoryType.ARMS_CATEGORY.ordinal.toString() -> activity?.viewActivityExercisesToolbar?.setTitle(R.string.category_arms_text)
        }
    }

    private fun setBundle(idExercise: String): Bundle {
        val bundle = Bundle()
        bundle.putString("key", idExercise)
        return bundle
    }

    companion object {
        const val TAG = "ListExerciseFragment"
        fun getInstance() = ListExerciseFragment()
    }
}