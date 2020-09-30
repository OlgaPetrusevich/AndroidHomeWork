package com.gmail.petrusevich.volha.homework8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showWeather()
        showHourlyWeather()
    }

    private fun showHourlyWeather() {
        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainerHourly, WeatherListFragment.gteInstance(), WeatherListFragment.TAG)
                .commit()
    }

    private fun showWeather() {
        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, WeatherFragment.gteInstance(), WeatherFragment.TAG)
                .commit()
    }

}
