package com.gmail.petrusevich.volha.project.presentation.exerciselist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmail.petrusevich.volha.project.R
import com.gmail.petrusevich.volha.project.data.CategoryType
import kotlinx.android.synthetic.main.fragment_category_exercise_tab.*

class CategoryExerciseFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_category_exercise_tab, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewCategoryFirst.setOnClickListener(this)
        viewCategorySecond.setOnClickListener(this)
        viewCategoryThird.setOnClickListener(this)
    }


    companion object {
        const val TAG = "CategoryFragment"
        fun getInstance() = CategoryExerciseFragment()
    }

    override fun onClick(itemView: View?) {
        lateinit var categoryType: CategoryType
        when (itemView) {
            viewCategoryFirst -> categoryType = CategoryType.REAR_CATEGORY
            viewCategorySecond -> categoryType = CategoryType.LEGS_CATEGORY
            viewCategoryThird -> categoryType = CategoryType.ARMS_CATEGORY
        }
        startActivity(ExercisesListActivity.newIntent(context, categoryType))
    }
}