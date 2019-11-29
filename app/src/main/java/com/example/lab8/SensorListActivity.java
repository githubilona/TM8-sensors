package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SensorListActivity extends AppCompatActivity implements SensorEventListener {

    TextView temperatureTextView;
    ListView sensorListView;
    List<Sensor> sensors;
    List<String> sensorsNames;
    ArrayAdapter<String> adapter;

    SensorManager mSensorManager;
    Sensor mTemperature;
    float temperatureValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_list);

        sensorListView = findViewById(R.id.sensorListView);
        temperatureTextView = findViewById(R.id.temperatureTextView);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        sensorsNames = new ArrayList<>();
        fillSensorNameList();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sensorsNames);
        sensorListView.setAdapter(adapter);

        mTemperature = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

    }

    public void fillSensorNameList() {
        for (Sensor s : sensors) {
            sensorsNames.add(s.getName());
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            float temperatureValue = sensorEvent.values[0];
            temperatureTextView.setText("Temperature: \t\t " + temperatureValue);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mTemperature, SensorManager.SENSOR_DELAY_UI);

    }

    // ?
    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }
}
