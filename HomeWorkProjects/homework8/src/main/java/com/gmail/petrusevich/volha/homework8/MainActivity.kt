package com.gmail.petrusevich.volha.homework8

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gmail.petrusevich.volha.homework8.city.CityFragment
import com.gmail.petrusevich.volha.homework8.settings.SettingsFragment
import com.gmail.petrusevich.volha.homework8.weather.fragmentsweather.WeatherFragment
import com.gmail.petrusevich.volha.homework8.weather.fragmentsweather.WeatherListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val weatherListFragment: WeatherListFragment by lazy { WeatherListFragment.gteInstance() }
    private val weatherFragment: WeatherFragment by lazy { WeatherFragment.gteInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showWeather()
        showHourlyWeather()
        viewSettings.setOnClickListener(this)
        viewActionButton.setOnClickListener(this)
    }

    private fun showHourlyWeather() {
        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainerHourly, weatherListFragment, WeatherListFragment.TAG)
                .commit()
    }

    private fun showWeather() {
        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, weatherFragment, WeatherFragment.TAG)
                .commit()
    }

    private fun showFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainerSettings, fragment, tag)
                .remove(weatherListFragment)
                .remove(weatherFragment)
                .addToBackStack(null)
                .commit()
        viewActionButton.visibility = View.GONE
    }

    override fun onClick(view: View?) {
        when (view) {
            viewSettings -> showFragment(SettingsFragment.gteInstance(), SettingsFragment.TAG)
            viewActionButton -> showFragment(CityFragment.gteInstance(), CityFragment.TAG)
        }

    }

}
