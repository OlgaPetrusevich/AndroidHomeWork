package com.gmail.petrusevich.volha.homework7

import android.content.Context
import android.content.SharedPreferences
import android.widget.RadioGroup

private const val KEY_SETTINGS = "KEY_SETTINGS"

class Settings private constructor() {

    companion object {
        lateinit var sharedPreferences: SharedPreferences

        fun getInstance(context: Context): Settings {
            sharedPreferences = context.getSharedPreferences(KEY_SETTINGS, Context.MODE_PRIVATE)
            return Settings()
        }
    }

    fun saveSettings(radioGroup: RadioGroup) {
        sharedPreferences.edit().apply {
            putInt(KEY_SETTINGS, radioGroup.checkedRadioButtonId)
            apply()
        }
    }

    fun loadSettings(radioGroup: RadioGroup) {
        radioGroup.check(sharedPreferences.getInt(KEY_SETTINGS, 0))
    }

}