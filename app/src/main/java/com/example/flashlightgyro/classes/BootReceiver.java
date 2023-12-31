package com.example.flashlightgyro.classes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            // Start the ShakeDetectionService when the device boots up
            Intent serviceIntent = new Intent(context, ShakeServices.class);
            context.startService(serviceIntent);
        }
        if(intent.getAction().equals("ACTION_SHAKE_DETECTED")){
            boolean isLight = intent.getBooleanExtra("flashlight_on",false);
            LightOnOff l = new LightOnOff();
            l.changeLightState(isLight,context);
        }
    }
}
