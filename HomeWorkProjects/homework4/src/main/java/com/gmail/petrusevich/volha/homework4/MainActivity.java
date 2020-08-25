package com.gmail.petrusevich.volha.homework4;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private boolean isSwitchOn;
    private CustomView customView;
    private Settings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customView = findViewById(R.id.custom_view);
        settings = Settings.getInstance(MainActivity.this);
        customView.setTouchActionListener(new CustomView.TouchActionListener() {
            @Override
            public void onTouchDown(int x, int y) {
                String textCoordinates = getString(R.string.coordinatesClick, x, y);
                if (!customView.isAreaClick(x, y)) {
                    showMessage(textCoordinates);
                }
                customView.getNewColor(x, y);
            }
        });
    }

    @Override
    protected void onResume() {
        isSwitchOn = settings.loadSettings();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menuSettings) {
            startNextActivity();
        }
        return super.onOptionsItemSelected(item);
    }

    private void startNextActivity() {
        startActivity(OptionActivity.newIntent(this));
    }


    private void showMessage(String text) {
        if (isSwitchOn) {
            Snackbar.make(findViewById(R.id.layoutParent), text, Snackbar.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
        }
    }
}
