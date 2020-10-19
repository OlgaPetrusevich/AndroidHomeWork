package com.gmail.petrusevich.volha.homework8.weather.fragmentsweather

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.gmail.petrusevich.volha.homework8.*
import com.gmail.petrusevich.volha.homework8.weather.CELSIUS
import com.gmail.petrusevich.volha.homework8.weather.FAHRENHEIT
import com.gmail.petrusevich.volha.homework8.weather.Settings
import com.gmail.petrusevich.volha.homework8.weather.presenter.WeatherListItemModel
import com.gmail.petrusevich.volha.homework8.weather.presenter.WeatherListItemModelMapper
import com.gmail.petrusevich.volha.homework8.weather.presenter.WeatherListPresenterImpl
import com.gmail.petrusevich.volha.homework8.weather.repository.WeatherDataModelMapper
import com.gmail.petrusevich.volha.homework8.weather.repository.WeatherRepositoryImpl
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_weather.*
import okhttp3.OkHttpClient

class WeatherFragment : Fragment(), WeatherListView {

    private lateinit var units: String
    private lateinit var city: String
    private val settings = Settings.getInstance()

    private val presenter: WeatherListPresenterImpl = WeatherListPresenterImpl(
            weatherRepositoryImpl = WeatherRepositoryImpl(OkHttpClient(), WeatherDataModelMapper()),
            weatherListView = this,
            weatherListItemModelMapper = WeatherListItemModelMapper()
    )


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_weather, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getUnits()
        activity?.findViewById<FloatingActionButton>(R.id.viewActionButton)?.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        presenter.fetchWeatherList(units, "Minsk")
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
    }

    private fun getUnits() {
        settings.getSharedPreferences(activity?.applicationContext!!)
        units = if(settings.loadSettings()){
            CELSIUS
        }else{
            FAHRENHEIT
        }
    }

    override fun showWeatherList(weatherList: List<WeatherListItemModel>) {
        Glide.with(context!!)
                .load(weatherList[0].urlIcon)
                .into(viewImageWeather)
        viewTextCity.text = weatherList[0].cityName
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