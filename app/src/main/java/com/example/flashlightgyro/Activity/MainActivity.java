package com.example.flashlightgyro.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flashlightgyro.R;
import com.example.flashlightgyro.classes.ShakeServices;
import com.example.flashlightgyro.classes.LightOnOff;

public class MainActivity extends AppCompatActivity {

    Button btnOn;
    Button btnOff ;
    Vibrator vibrator = null;
    private BroadcastReceiver shakeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOff = findViewById(R.id.btnOff);
        btnOn = findViewById(R.id.btnOn);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        // Start the ShakeDetectionService when the app is launched.
        startService(new Intent(this, ShakeServices.class));

        LightOnOff g = new LightOnOff();

        shakeReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                boolean isFlash = intent.getBooleanExtra("flashlight_on",false);
                g.changeLightState(isFlash,getApplicationContext());
            }
        };

        registerReceiver(shakeReceiver, new IntentFilter("ACTION_SHAKE_DETECTED"));

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



    }


}