package com.example.igx.problem1;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by user on 2016-12-13.
 */

public class SensorActivity extends Service implements SensorEventListener {

    SensorManager sensorManager;
    Sensor temp, accel, light, gravity;
    String result;
    double a = 0;
    double g = 0;
    double t = 0;
    double l = 0;
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy){
    }

    public  void onSensorChanged(SensorEvent event){
        switch(event.sensor.getType()){
            case Sensor.TYPE_ACCELEROMETER:
                a = event.values[0];
                break;
            case Sensor.TYPE_HEART_RATE:
                t= event.values[0];
                break;
            case Sensor.TYPE_LIGHT:
                l = event.values[0];
                break;
            case Sensor.TYPE_GRAVITY:
                g = event.values[0];
                break;
        }
        result = Double.toString(a) + "     " + Double.toString(t) + "     " + Double.toString(l) + "     " + Double.toString(g);
        Log.d("dd", result);
    }

    public void onCreate(){
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        temp  = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        gravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
    }

    public String GetResult(){
        return result;
    }
}
