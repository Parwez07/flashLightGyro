package com.example.flashlightgyro;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnOn;
    Button btnOff , btnSec;
    Vibrator vibrator = null;
    private BroadcastReceiver shakeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOff = findViewById(R.id.btnOff);
        btnOn = findViewById(R.id.btnOn);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        btnSec = findViewById(R.id.btnSec);

        // Start the ShakeDetectionService when the app is launched.
        startService(new Intent(this, ShakeServices.class));


        global g = new global();

        btnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                g.changeLightState(true,getApplicationContext());
            }
        });
        btnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                g.changeLightState(false,getApplicationContext());
            }
        });

        btnSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ShakeDetactServices.class));
            }
        });

    }


}