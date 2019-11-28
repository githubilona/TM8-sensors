package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void accelerometerButtonOnClick(View view) {
        Intent accelerometerIntent = new Intent(this, AccelerometerActivity.class);
        startActivity(accelerometerIntent);
    }
    public void lightmeterButtonOnClick(View view) {
        Intent lightmeterIntent = new Intent(this, LightmeterActivity.class);
        startActivity(lightmeterIntent);
    }

    public void listOfSensorsOnClick(View view) {
        Intent sensorListIntent = new Intent(this, SensorListActivity.class);
        startActivity(sensorListIntent);
    }
}
