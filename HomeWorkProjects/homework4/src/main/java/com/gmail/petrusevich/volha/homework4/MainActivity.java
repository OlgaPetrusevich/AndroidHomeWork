package com.gmail.petrusevich.volha.homework4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CustomView customView = findViewById(R.id.custom_view);
        customView.setTouchActionListener(new CustomView.TouchActionListener() {
            @Override
            public void onTouchDown(int x, int y) {
                String textCoordinates = getString(R.string.coordinatesClick, x, y);
                Toast.makeText(MainActivity.this, textCoordinates, Toast.LENGTH_SHORT).show();
                customView.getNewColor(x, y);
            }
        });
    }
}
