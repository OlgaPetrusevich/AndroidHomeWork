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
import com.gmail.petrusevich.volha.project.presentation.exerciselist.adapter.HistoryListAdapter
import kotlinx.android.synthetic.main.fragment_history_list.*

class HistoryListFragment : Fragment() {

    private val historyExercisesViewModel by lazy { ViewModelProvider(this).get(HistoryExercisesViewModel::class.java) }
    private val historyListController by lazy { HistoryListController() }
    private val categoryType by lazy { arguments?.getString("keyCategoryHistory") }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_history_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewHistoryList.adapter = HistoryListAdapter()
        with(viewLifecycleOwner) {
            historyExercisesViewModel.historyLiveData.observe(this, Observer { items ->
                (viewHistoryList.adapter as? HistoryListAdapter)?.updateExerciseList(items)
            })
            historyExercisesViewModel.historyErrorLiveData.observe(this, Observer { throwable ->
                Log.d("Error", throwable.message!!)
            })
        }
        historyListController.showHistory(historyExercisesViewModel, categoryType!!)
    }


    companion object {
        const val TAG = "HistoryListFragment"
        fun getInstance() = HistoryListFragment()
    }
}