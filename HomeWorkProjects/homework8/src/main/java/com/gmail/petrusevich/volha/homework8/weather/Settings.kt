package com.gmail.petrusevich.volha.homework8.weather

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.widget.SwitchCompat

private const val SAVE_KEY = "SAVE_KEY"
private const val SAVE_ID_VIEW = "SAVE_ID_VIEW"

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

    fun saveIdItemView(itemViewId: Long){
        sharedPreferences.edit().let {
            it.putLong(SAVE_ID_VIEW, itemViewId)
            it.apply()
        }
    }

    fun loadIdItemView(): Long = sharedPreferences.getLong(SAVE_ID_VIEW, 0)

    fun setSwitch(switchCompat: SwitchCompat){
        switchCompat.isChecked = sharedPreferences.getBoolean(SAVE_KEY, false)
    }

    fun loadSettings(): Boolean = sharedPreferences.getBoolean(SAVE_KEY, false)
}