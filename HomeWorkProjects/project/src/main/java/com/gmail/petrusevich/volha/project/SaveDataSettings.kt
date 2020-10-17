package com.gmail.petrusevich.volha.project

import android.content.Context
import android.content.SharedPreferences
import android.widget.EditText

private const val KEY_SETTINGS = "KEY_SETTINGS"
private const val KEY_SETTINGS1 = "KEY_SETTINGS1"
private const val KEY_SETTINGS2 = "KEY_SETTINGS2"
class SaveDataSettings {

    companion object {
        lateinit var sharedPreferences: SharedPreferences

        fun getInstance(context: Context): SaveDataSettings {
            sharedPreferences = context.getSharedPreferences(KEY_SETTINGS, Context.MODE_PRIVATE)
            return SaveDataSettings()
        }
    }

    fun saveHeight(text: EditText) {
        sharedPreferences.edit().apply {
            putString(KEY_SETTINGS1, text.text.toString())
            apply()
        }
    }

    fun saveWeight(text: EditText) {
        sharedPreferences.edit().apply {
            putString(KEY_SETTINGS2, text.text.toString())
            apply()
        }
    }

    fun loadHeight(text: EditText) {
        text.setText(sharedPreferences.getString(KEY_SETTINGS1, null))
    }

    fun loadWeight(text: EditText) {
        text.setText(sharedPreferences.getString(KEY_SETTINGS2, null))
    }
}