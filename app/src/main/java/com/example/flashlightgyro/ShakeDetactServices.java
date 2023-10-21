package com.example.flashlightgyro;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class ShakeDetactServices extends AppCompatActivity implements SensorEventListener {


    SensorManager sensorManager = null;

    public static final int MIN_TIME_BETWEEN_SHAKES = 500;
    private  final  int MAX_TIME_BEWEEN_SHAKES = 1500;
    private long lastShakeTime = 0;
    private boolean isFlashlightOn = false;
    private Float shakeThreshold = 12.0f;
    int count = 0;
    double acceleration;
    long temp = 0l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake_detact_services);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (sensorManager != null) {

            Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if (sensor != null) {
                Log.d("sensor", "sensor sahi hai");
                sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

            }

        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long currTime = System.currentTimeMillis();
            Log.d("event", "event sahi hai");
            long timeDiff = currTime - lastShakeTime;


            if(timeDiff > MIN_TIME_BETWEEN_SHAKES ) {
                float x = sensorEvent.values[0];
                float y = sensorEvent.values[1];
                float z = sensorEvent.values[2];

                acceleration = Math.sqrt(Math.pow(x, 2) +
                        Math.pow(y, 2) +
                        Math.pow(z, 2)) - SensorManager.GRAVITY_EARTH;

                if (acceleration > shakeThreshold) {
                    count++;
                    lastShakeTime = currTime;
                    Log.d("allAns", "" + count + " " + isFlashlightOn + " TimeDif " + (timeDiff) + " Acce " + acceleration);
                }

                if(count >1 && timeDiff > MAX_TIME_BEWEEN_SHAKES){
                    Log.d("allAns", "yaha " + count + " " + isFlashlightOn + " TimeDif " + (timeDiff) + " Acce " + acceleration);
                    count =1;
                }

            }


            if(count >=2){

                global g = new global();

                if(isFlashlightOn){
                    g.changeLightState(false,this);
                    isFlashlightOn = false;
                }else{
                    g.changeLightState(true,this);
                    isFlashlightOn = true;
                }
                count =0;
                Log.d("allAns", "" + count + " " + isFlashlightOn + " TimeDif " + (timeDiff) + " Acce " + acceleration);
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}