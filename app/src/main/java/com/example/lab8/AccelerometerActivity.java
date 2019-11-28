package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager mSensorManager;
    Sensor mAccelerometer;

    TextView xValueTextView;
    TextView yValueTextView;
    TextView zValueTextView;
    TextView xGravityTextView;
    TextView yGravityTextView;
    TextView zGravityTextView;
    TextView xAccelTextView;
    TextView yAccelTextView;
    TextView zAccelTextView;

    private float[] mGravity = new float[3];
    private float[] mAccel = new float[3];
    float mAlpha = 0.8f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        xValueTextView = findViewById(R.id.xValueTextView);
        yValueTextView = findViewById(R.id.yValueTextView);
        zValueTextView = findViewById(R.id.zValueTextView);
        xGravityTextView = findViewById(R.id.xGravityTextView);
        yGravityTextView = findViewById(R.id.yGravityTextView);
        zGravityTextView = findViewById(R.id.zGravityTextView);
        xAccelTextView = findViewById(R.id.xAccelTextView);
        yAccelTextView = findViewById(R.id.yAccelTextView);
        zAccelTextView = findViewById(R.id.zAccelTextView);


        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float rawX = sensorEvent.values[0], rawY = sensorEvent.values[1], rawZ = sensorEvent.values[2];

            mGravity[0] = lowPass(rawX, mGravity[0]);
            mGravity[1] = lowPass(rawY, mGravity[1]);
            mGravity[2] = lowPass(rawZ, mGravity[2]);

            mAccel[0] = highPass(rawX, mGravity[0]);
            mAccel[1] = highPass(rawY, mGravity[1]);
            mAccel[2] = highPass(rawZ, mGravity[2]);

            String xLabel = "x:\t\t";
            String yLabel = "y:\t\t";
            String zLabel = "z:\t\t";

            xValueTextView.setText(xLabel + String.valueOf(rawX));
            yValueTextView.setText(yLabel + String.valueOf(rawY));
            zValueTextView.setText(zLabel + String.valueOf(rawZ));


            xGravityTextView.setText(xLabel + String.valueOf(mGravity[0]));
            yGravityTextView.setText(yLabel + String.valueOf(mGravity[1]));
            zGravityTextView.setText(zLabel + String.valueOf(mGravity[2]));

            xAccelTextView.setText(xLabel + String.valueOf(mAccel[0]));
            yAccelTextView.setText(yLabel + String.valueOf(mAccel[1]));
            zAccelTextView.setText(zLabel + String.valueOf(mAccel[2]));


        }
    }

    private float lowPass(float current, float gravity) {
        return gravity * mAlpha + current * (1 - mAlpha);
    }

    private float highPass(float current, float gravity) {
        return current - gravity;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);

    }

    // ?????
    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }

}
