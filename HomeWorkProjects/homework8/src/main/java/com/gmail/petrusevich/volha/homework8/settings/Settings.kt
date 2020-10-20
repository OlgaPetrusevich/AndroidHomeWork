package com.gmail.petrusevich.volha.homework8.settings

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.widget.SwitchCompat

private const val SAVE_KEY = "SAVE_KEY"
private const val SAVE_POSITION_VIEW = "SAVE_POSITION_VIEW"
private const val SAVE_CITY = "SAVE_CITY_NAME"
private const val DEFAULT_CITY = "Minsk"

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

    fun saveItemPosition(position: Int) {
        sharedPreferences.edit().let {
            it.putInt(SAVE_POSITION_VIEW, position)
            it.apply()
        }
    }

    fun saveCityName(name: String) {
        sharedPreferences.edit().let {
            it.putString(SAVE_CITY, name)
            it.apply()
        }
    }

    fun loadCityName(): String? = sharedPreferences.getString(SAVE_CITY, DEFAULT_CITY)

    fun loadItemPosition(): Int = sharedPreferences.getInt(SAVE_POSITION_VIEW, 0)

    fun setSwitch(switchCompat: SwitchCompat) {
        switchCompat.isChecked = sharedPreferences.getBoolean(SAVE_KEY, false)
    }

    fun loadSettings(): Boolean = sharedPreferences.getBoolean(SAVE_KEY, false)
}