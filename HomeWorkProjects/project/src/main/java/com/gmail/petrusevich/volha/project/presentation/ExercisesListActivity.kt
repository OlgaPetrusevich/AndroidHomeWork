package com.gmail.petrusevich.volha.project.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gmail.petrusevich.volha.project.R
import com.gmail.petrusevich.volha.project.data.CategoryType
import com.gmail.petrusevich.volha.project.data.ExerciseDataModel
import com.gmail.petrusevich.volha.project.presentation.exerciselist.ExerciseItemModel
import com.gmail.petrusevich.volha.project.presentation.exerciselist.ExerciseListAdapter
import com.gmail.petrusevich.volha.project.presentation.exerciselist.ExerciseViewModel
import com.gmail.petrusevich.volha.project.presentation.exerciselist.ItemOnClickListener
import kotlinx.android.synthetic.main.activity_exercises_list.*

private const val CATEGORY_KEY = "categoryKey"

class ExercisesListActivity : AppCompatActivity(), ItemOnClickListener {

    private val exerciseViewModel by lazy { ViewModelProvider(this).get(ExerciseViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercises_list)
        viewGymList.adapter = ExerciseListAdapter(this)
        with(this) {
            exerciseViewModel.exercisesLiveData.observe(this, Observer { items ->
                (viewGymList.adapter as? ExerciseListAdapter)?.updateExerciseList(items)
            })
            exerciseViewModel.exercisesErrorLiveData.observe(this, Observer { throwable ->
                Log.d("Error", throwable.message!!)
            })
        }
        exerciseViewModel.getCategoryExercises(getCategoryType())
        getTitleToolbar()
    }

    private fun getCategoryType(): String {
        val categoryType: CategoryType = intent.getSerializableExtra(CATEGORY_KEY) as CategoryType
        return categoryType.ordinal.toString()
    }

    private fun getTitleToolbar() {
        when (intent.getSerializableExtra(CATEGORY_KEY) as CategoryType) {
            CategoryType.REAR_CATEGORY -> viewActivityExercisesToolbar.setTitle(R.string.category_rear_text)
            CategoryType.LEGS_CATEGORY -> viewActivityExercisesToolbar.setTitle(R.string.category_legs_text)
            CategoryType.ARMS_CATEGORY -> viewActivityExercisesToolbar.setTitle(R.string.category_arms_text)
        }
    }

    override fun itemOnClick(position: Int) {
        val idExercise: String = exerciseViewModel.exercisesLiveData.value!![position].id
        viewGymList.visibility = View.GONE
        loadFragment(ExerciseDescriptionFragment.getInstance(), setBundle(idExercise))
    }

    private fun loadFragment(fragment: Fragment, bundle: Bundle): Boolean {
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentExerciseContainer, fragment)
                .addToBackStack(null)
                .commit()
        return true
    }

    private fun setBundle(idExercise: String): Bundle{
        val bundle = Bundle()
        bundle.putString("key", idExercise)
        return bundle
    }

    companion object {
        fun newIntent(context: Context?, idCategory: CategoryType): Intent {
            val intent = Intent(context, ExercisesListActivity::class.java)
            intent.putExtra(CATEGORY_KEY, idCategory)
            return intent
        }
    }
}
