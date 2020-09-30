package com.gmail.petrusevich.volha.homework8

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.gmail.petrusevich.volha.homework8.presenter.WeatherListItemModel
import com.gmail.petrusevich.volha.homework8.presenter.WeatherListItemModelMapper
import com.gmail.petrusevich.volha.homework8.presenter.WeatherListPresenterImpl
import com.gmail.petrusevich.volha.homework8.repository.WeatherDataModelMapper
import com.gmail.petrusevich.volha.homework8.repository.WeatherRepositoryImpl
import kotlinx.android.synthetic.main.fragment_weather.*
import kotlinx.android.synthetic.main.item_view.view.*
import okhttp3.OkHttpClient

class WeatherFragment : Fragment(), WeatherListView {

    private val presenter: WeatherListPresenterImpl = WeatherListPresenterImpl(
            weatherRepositoryImpl = WeatherRepositoryImpl(OkHttpClient(), WeatherDataModelMapper()),
            weatherListView = this,
            weatherListItemModelMapper = WeatherListItemModelMapper()
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_weather, container, false)

    override fun onResume() {
        super.onResume()
        presenter.fetchWeatherList()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
    }

    override fun showWeatherList(weatherList: List<WeatherListItemModel>) {
        Glide.with(context!!)
                .load(weatherList[0].urlIcon)
                .into(viewImageWeather)
        viewTextCity.text = weatherList.get(0).cityName
        viewTextDegree.text = weatherList[0].temperature
        viewTextWeather.text = weatherList[0].description
    }

    override fun onError(errorMessage: String) {
        Log.d("errorMessage", errorMessage)
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
    }

    companion object {
        const val TAG = "WeatherFragment"
        fun gteInstance() = WeatherFragment()
    }
}