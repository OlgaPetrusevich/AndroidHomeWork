package com.gmail.petrusevich.volha.homework4;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.widget.SwitchCompat;

public class Settings {

    private static Settings instance;
    private static SharedPreferences sharedPreferences;
    public static final String SAVE_KEY = "SAVE_KEY";

    private Settings() {
    }

    public static Settings getInstance(Context context) {
        if (instance == null) {
            instance = new Settings();
        }
        sharedPreferences = context.getSharedPreferences(SAVE_KEY, Context.MODE_PRIVATE);
        return instance;
    }

    public void saveSettings(SwitchCompat switchCompat) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(SAVE_KEY, switchCompat.isChecked());
        editor.apply();
    }


    public void setSwitchCheck(SwitchCompat switchCompat) {
        switchCompat.setChecked(sharedPreferences.getBoolean(SAVE_KEY, false));
    }

    public boolean loadSettings() {
        return sharedPreferences.getBoolean(SAVE_KEY, false);
    }

}
