package com.gmail.petrusevich.volha.homework4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class OptionActivity extends AppCompatActivity {

    private Button saveButton;
    private SwitchCompat switchCompat;
    private Settings settings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        saveButton = findViewById(R.id.viewSaveButton);
        switchCompat = findViewById(R.id.switchMessageSend);
        saveButton.setOnClickListener(listener);
        settings = Settings.getInstance(OptionActivity.this);
        settings.setSwitchCheck(switchCompat);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.viewSaveButton) {
                settings.saveSettings(switchCompat);
                finish();
            }
        }
    };

    public static Intent newIntent(Context context) {
        return new Intent(context, OptionActivity.class);
    }


}
