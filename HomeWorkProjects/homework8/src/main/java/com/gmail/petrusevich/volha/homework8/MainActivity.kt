package com.gmail.petrusevich.volha.homework8

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.gmail.petrusevich.volha.homework8.fragmentsweather.WeatherFragment
import com.gmail.petrusevich.volha.homework8.fragmentsweather.WeatherListFragment
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

    private fun showSettings() {
        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainerSettings, SettingsFragment.gteInstance(), SettingsFragment.TAG)
                .remove(weatherListFragment)
                .remove(weatherFragment)
                .addToBackStack(null)
                .commit()
//        viewActionButton.visibility = View.GONE
    }

    override fun onClick(view: View?) {
        when (view){
            viewSettings -> showSettings()
        }

    }

}
