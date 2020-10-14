package com.gmail.petrusevich.volha.project.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import com.gmail.petrusevich.volha.project.R
import com.gmail.petrusevich.volha.project.data.CategoryType
import kotlinx.android.synthetic.main.fragment_history_tab.*
import java.time.LocalDate

class HistoryTabFragment : Fragment(), View.OnClickListener {

    private val calendarController by lazy { CalendarController() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_history_tab, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewHistoryCategoryRear.setOnClickListener(this)
        viewHistoryCategoryLegs.setOnClickListener(this)
        viewHistoryCategoryArms.setOnClickListener(this)
        viewCalendar.setOnDateChangeListener(CalendarView.OnDateChangeListener { calendarView, year, month, dayOfMonth ->
            val dateText = calendarController.getCalendarDate(year, month, dayOfMonth)
            loadFragment(HistoryListFragment.getInstance(), setBundle(dateText))
        })
    }

    companion object {
        const val TAG = "HistoryTabFragment"
        fun getInstance() = HistoryTabFragment()
    }

    override fun onClick(view: View?) {
        when (view) {
            viewHistoryCategoryRear -> loadFragment(HistoryListFragment.getInstance(), setBundle(CategoryType.REAR_CATEGORY.ordinal.toString()))
            viewHistoryCategoryLegs -> loadFragment(HistoryListFragment.getInstance(), setBundle(CategoryType.LEGS_CATEGORY.ordinal.toString()))
            viewHistoryCategoryArms -> loadFragment(HistoryListFragment.getInstance(), setBundle(CategoryType.ARMS_CATEGORY.ordinal.toString()))
        }
    }


    private fun loadFragment(fragment: Fragment, bundle: Bundle): Boolean {
        fragment.arguments = bundle
        activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit()
        return true
    }

    private fun setBundle(param: String): Bundle {
        val bundle = Bundle()
        bundle.putString("keyCategoryHistory", param)
        return bundle
    }

}