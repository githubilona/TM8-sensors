package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SensorListActivity extends AppCompatActivity implements SensorEventListener {

    ListView sensorListView;
    List<Sensor> sensors;
    List<String> sensorsNames;
    ArrayAdapter<String> adapter;

    SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_list);

        sensorListView = findViewById(R.id.sensorListView);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        sensorsNames = new ArrayList<>();
        fillSensorNameList();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sensorsNames);
        sensorListView.setAdapter(adapter);

        //mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    public void fillSensorNameList() {
        for (Sensor s : sensors) {
            sensorsNames.add(s.getName());
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
