package com.gmail.petrusevich.volha.homework8.city

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.gmail.petrusevich.volha.homework8.R
import com.gmail.petrusevich.volha.homework8.city.data.CityDataModel
import com.gmail.petrusevich.volha.homework8.city.presentation.CityListViewModel
import com.gmail.petrusevich.volha.homework8.city.presentation.adapter.CityListAdapter
import com.gmail.petrusevich.volha.homework8.city.presentation.adapter.ItemOnClickListener
import com.gmail.petrusevich.volha.homework8.weather.Settings
import kotlinx.android.synthetic.main.fragment_city_list.*
import kotlinx.android.synthetic.main.item_city_list.*
import kotlinx.android.synthetic.main.item_city_list.view.*

const val DEFAULT_CITY = "Minsk"

class CityFragment : Fragment(), View.OnClickListener, ItemOnClickListener {

    private val cityListViewModel by lazy { ViewModelProvider(this).get(CityListViewModel::class.java) }
    private val cityAddDialogFragment by lazy { CityAddDialogFragment() }
    private val settings by lazy { Settings.getInstance() }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_city_list, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settings.getSharedPreferences(activity?.applicationContext!!)
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
        loadCheckedCity()
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
        cityListViewModel.getCityList()
    }


    override fun itemOnClick(position: Int) {
        val itemId = viewCityList.adapter?.getItemId(position)
        if (itemId != null) {
            settings.saveIdItemView(itemId)
        }
        setCheckedCity(viewCityList.findViewHolderForAdapterPosition(position))
        val cityList = cityListViewModel.cityListLiveData.value
        var cityName: String = DEFAULT_CITY
        if (cityList != null) {
            cityName = cityList[position].cityName
        }
        setBundle(cityName)
    }

    private fun loadCheckedCity() {
        val itemId = settings.loadIdItemView()
        val viewHolder = viewCityList.findViewHolderForItemId(itemId)
        setCheckedCity(viewHolder)
    }

    private fun setCheckedCity(viewHolder: RecyclerView.ViewHolder?){
        viewHolder?.itemView?.viewCityText?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_check_24, 0)
    }

    private fun setBundle(cityName: String): Bundle {
        val bundle = Bundle()
        bundle.putString("key", cityName)
        return bundle
    }

    companion object {
        const val TAG = "CityFragment"
        fun gteInstance() = CityFragment()
    }
}