package com.gmail.petrusevich.volha.project.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gmail.petrusevich.volha.project.R
import com.gmail.petrusevich.volha.project.data.CategoryType
import kotlinx.android.synthetic.main.activity_exercises_list.*

private const val CATEGORY_KEY = "categoryKey"

class ExercisesListActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercises_list)
        loadFragment(ListExerciseFragment.getInstance(), setBundle())
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

    private fun setBundle(): Bundle {
        val categoryType = getCategoryType()
        val bundle = Bundle()
        bundle.putString("keyBundle", categoryType)
        return bundle
    }

    private fun loadFragment(fragment: Fragment, bundle: Bundle): Boolean {
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentExerciseContainer, fragment)
                .commit()
        return true
    }

    companion object {
        fun newIntent(context: Context?, idCategory: CategoryType): Intent {
            val intent = Intent(context, ExercisesListActivity::class.java)
            intent.putExtra(CATEGORY_KEY, idCategory)
            return intent
        }
    }
}
