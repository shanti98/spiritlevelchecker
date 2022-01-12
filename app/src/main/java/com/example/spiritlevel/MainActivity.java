package com.example.spiritlevel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity  implements SensorEventListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private SensorManager smanager;
    private Sensor msensor;
    private SpiritLevelView spiritLevelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spiritLevelView = findViewById(R.id.SpiritLevelView);

        smanager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        msensor = smanager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        List<Sensor> sensorList = smanager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor sensor : sensorList) {
            Log.i(TAG, sensor.getName());
        }

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;
        Log.i(TAG, String.format("x:%.2f y:%.2f z:%.2f",values[0],values[1],values[2]));
        float screenX = values[0];
        float screenY = values[1];


        spiritLevelView.invalidate();
        spiritLevelView.updatePosition(screenX,screenY);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        switch (accuracy){
            case SensorManager.SENSOR_STATUS_ACCURACY_HIGH:
                Log.i(TAG,"Accuracy is high");
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_LOW:
                Log.i(TAG,"Accuracy is low");
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM:
                Log.i(TAG,"Accuracy is medium");
                break;
            case SensorManager.SENSOR_STATUS_UNRELIABLE:
                Log.i(TAG,"Accuracy is unreliable");
                break;
            case SensorManager.SENSOR_STATUS_NO_CONTACT:
                Log.i(TAG,"Accuracy is in no contact");
                break;

        }
        Log.i(TAG, String.format(Locale.getDefault(),
                "%d", accuracy));

    }
    @Override
    protected void onPause() {
        super.onPause();
        smanager.unregisterListener(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        smanager.registerListener(this, msensor, SensorManager.SENSOR_DELAY_NORMAL);
    }


}