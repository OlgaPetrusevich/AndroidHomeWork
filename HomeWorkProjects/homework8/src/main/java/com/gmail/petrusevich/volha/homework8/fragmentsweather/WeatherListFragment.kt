package com.gmail.petrusevich.volha.homework8.fragmentsweather

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.petrusevich.volha.homework8.R
import com.gmail.petrusevich.volha.homework8.presenter.WeatherListItemModel
import com.gmail.petrusevich.volha.homework8.presenter.WeatherListItemModelMapper
import com.gmail.petrusevich.volha.homework8.presenter.WeatherListPresenterImpl
import com.gmail.petrusevich.volha.homework8.repository.WeatherDataModelMapper
import com.gmail.petrusevich.volha.homework8.repository.WeatherListAdapter
import com.gmail.petrusevich.volha.homework8.repository.WeatherRepositoryImpl
import kotlinx.android.synthetic.main.fragment_weather_list.*
import okhttp3.OkHttpClient

class WeatherListFragment : Fragment(), WeatherListView {


    private val presenter: WeatherListPresenterImpl = WeatherListPresenterImpl(
            weatherRepositoryImpl = WeatherRepositoryImpl(OkHttpClient(), WeatherDataModelMapper()),
            weatherListView = this,
            weatherListItemModelMapper = WeatherListItemModelMapper()
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_weather_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewWeatherList.apply {
            adapter = WeatherListAdapter()
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.fetchWeatherList()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
    }

    override fun showWeatherList(weatherList: List<WeatherListItemModel>) {
        (viewWeatherList.adapter as? WeatherListAdapter)?.updateWeatherList(weatherList)
    }

    override fun onError(errorMessage: String) {
        Log.d("errorMessage", errorMessage)
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
    }

    companion object {
        const val TAG = "WeatherListFragment"
        fun gteInstance() = WeatherListFragment()
    }
}