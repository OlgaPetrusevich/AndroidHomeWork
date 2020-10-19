package com.gmail.petrusevich.volha.homework8.city

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gmail.petrusevich.volha.homework8.R
import com.gmail.petrusevich.volha.homework8.city.data.CityDataModel
import com.gmail.petrusevich.volha.homework8.city.presentation.CityListViewModel
import com.gmail.petrusevich.volha.homework8.city.presentation.adapter.CityListAdapter
import com.gmail.petrusevich.volha.homework8.city.presentation.adapter.ItemOnClickListener
import kotlinx.android.synthetic.main.fragment_city_list.*


class CityFragment : Fragment(), View.OnClickListener, ItemOnClickListener {

    private val cityListViewModel by lazy { ViewModelProvider(this).get(CityListViewModel::class.java) }
    private val cityAddDialogFragment by lazy { CityAddDialogFragment() }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_city_list, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewCityList.adapter = CityListAdapter(this)
        viewActionAddButton.setOnClickListener(this)
        with(viewLifecycleOwner) {
            cityListViewModel.cityListLiveData.observe(this, Observer { items ->
                (viewCityList.adapter as? CityListAdapter)?.updateCityList(items)
            })
            cityListViewModel.cityErrorLiveData.observe(this, Observer { throwable ->
                Log.d("Error", throwable.message!!)
            })
        }
        cityListViewModel.getCityList()
    }


    override fun onClick(view: View?) {
        when (view) {
            viewActionAddButton -> {
                cityAddDialogFragment.setTargetFragment(this, 1)
                cityAddDialogFragment.show(activity?.supportFragmentManager!!, "dialog")
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val cityName = data?.getStringExtra("KEY")
        if (cityName != null) {
            cityListViewModel.insertCity(CityDataModel(cityName))
        }
    }

    companion object {
        const val TAG = "CityFragment"
        fun gteInstance() = CityFragment()
    }

    override fun itemOnClick(position: Int) {

    }
}