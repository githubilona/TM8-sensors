package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class LightmeterActivity extends AppCompatActivity implements SensorEventListener {

    TextView lightTextView;

    SensorManager mSensorManager;
    Sensor mLightmeter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lightmeter);

        lightTextView = findViewById(R.id.lightTextView);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mLightmeter = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
            float lightValue = sensorEvent.values[0];
            String label = "Light:\t\t";
            lightTextView.setText(label + lightValue);

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mLightmeter, SensorManager.SENSOR_DELAY_UI);

    }
    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }
}
