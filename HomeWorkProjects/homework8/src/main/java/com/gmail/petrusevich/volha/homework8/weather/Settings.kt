package com.gmail.petrusevich.volha.homework8.weather

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.widget.SwitchCompat

private const val SAVE_KEY = "SAVE_KEY"

class Settings private constructor() {

    private lateinit var sharedPreferences: SharedPreferences

    companion object {
        fun getInstance(): Settings = Settings()
    }

    fun getSharedPreferences(context: Context) {
        sharedPreferences = context.getSharedPreferences(SAVE_KEY, Context.MODE_PRIVATE)
    }

    fun saveSettings(switchCompat: SwitchCompat) {
        sharedPreferences.edit().let {
            it.putBoolean(SAVE_KEY, switchCompat.isChecked)
            it.apply()
        }
    }

    fun setSwitch(switchCompat: SwitchCompat){
        switchCompat.isChecked = sharedPreferences.getBoolean(SAVE_KEY, false)
    }

    fun loadSettings(): Boolean = sharedPreferences.getBoolean(SAVE_KEY, false)
}