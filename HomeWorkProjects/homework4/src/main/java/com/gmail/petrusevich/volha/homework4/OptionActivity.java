package com.gmail.petrusevich.volha.homework4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class OptionActivity extends AppCompatActivity {

    public static final String SAVE_KEY = "SAVE_KEY";
    private Button saveButton;
    private SwitchCompat switchCompat;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        saveButton = findViewById(R.id.viewSaveButton);
        switchCompat = findViewById(R.id.switchMessageSend);
        saveButton.setOnClickListener(listener);
        setSwitchCheck();
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.viewSaveButton) {
                saveSettings();
                finish();
            }
        }
    };

    public static Intent newIntent(Context context) {
        return new Intent(context, OptionActivity.class);
    }

    private void saveSettings() {
        sharedPreferences = getSharedPreferences(SAVE_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(SAVE_KEY, switchCompat.isChecked());
        editor.apply();
    }

    private void setSwitchCheck() {
        sharedPreferences = getSharedPreferences(SAVE_KEY, Context.MODE_PRIVATE);
        switchCompat.setChecked(sharedPreferences.getBoolean(SAVE_KEY, false));
    }

}
