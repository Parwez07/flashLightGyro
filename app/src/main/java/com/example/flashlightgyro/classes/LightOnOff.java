package com.example.flashlightgyro.classes;

import static android.content.Context.CAMERA_SERVICE;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.util.Log;

public class LightOnOff {

    public void changeLightState(boolean b, Context context) {

        CameraManager cameraManager = (CameraManager) context.getSystemService(CAMERA_SERVICE);
        String backCameraId = null;

        try {
            backCameraId = cameraManager.getCameraIdList()[0];
            Log.d("light","light sahi hai");
            cameraManager.setTorchMode(backCameraId, b);
        } catch (CameraAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
