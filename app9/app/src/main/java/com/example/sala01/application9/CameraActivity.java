package com.example.sala01.application9;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

public class CameraActivity extends AppCompatActivity {

    public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {

        public final String TAG = "CAMERA_PREVIEW";

        private Camera camera;
        private SurfaceHolder surfaceHolder;

        public CameraPreview(Context context, Camera camera) {
            super(context);
            this.camera = camera;

            // Install a SurfaceHolder.Callback so we get notified when the
            // underlying surface is created and destroyed.
            surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
            // deprecated setting, but required on Android versions prior to 3.0
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }

        public void surfaceCreated(SurfaceHolder holder) {
            try {
                camera.setPreviewDisplay(holder);
                camera.startPreview();
            } catch (IOException e) {
                Log.d(TAG, "Error setting camera preview: " + e.getMessage());
            }
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            // empty. Take care of releasing the Camera preview in your activity.
        }

        public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
            if (surfaceHolder.getSurface() == null){
                return; // preview surface does not exist
            }

            try {
                camera.stopPreview();
            } catch (Exception e) {
                // ignore: tried to stop a non-existent preview
            }

            try {
                camera.setPreviewDisplay(surfaceHolder);
                camera.startPreview();
            } catch (Exception e){
                Log.d(TAG, "Error starting camera preview: " + e.getMessage());
            }
        }
    }

    private Camera camera;
    private CameraPreview cameraPreview;
    private Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {

        public final String TAG = "PICTURE_CALLBACK";

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);

            if (pictureFile == null){
                Log.d(TAG, "Error creating media file, check storage permissions");
                return;
            }

            try {
                FileOutputStream outputStream = new FileOutputStream(pictureFile);
                outputStream.write(data);
                outputStream.close();
            } catch (FileNotFoundException e) {
                Log.d(TAG, "File not found: " + e.getMessage());
            } catch (IOException e) {
                Log.d(TAG, "Error accessing file: " + e.getMessage());
            }
        }

        private File getOutputMediaFile(int type) {
            File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Camera");

            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    return null;
                }
            }

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            if (type == MEDIA_TYPE_IMAGE){
                return new File(mediaStorageDir.getPath() + File.separator + "IMG_"+ timeStamp + ".jpg");
            } else {
                return null;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        if (checkCameraHardware(this)) {
            // Create an instance of Camera
            camera = getCameraInstance();

            // Create our Preview view and set it as the content of our activity.
            cameraPreview = new CameraPreview(this, camera);
            FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
            preview.addView(cameraPreview);

            Button captureButton = (Button) findViewById(R.id.button_capture);
            captureButton.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            camera.takePicture(null, null, pictureCallback);
                        }
                    }
            );
        } else {
            Toast.makeText(this, "Camera não encontrada.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            return true;
        } else {
            return false;
        }
    }

    private Camera getCameraInstance() {
        Camera camera = null;
        try {
            camera = Camera.open();
        } catch (Exception e) {
            Toast.makeText(this, "Camera não encontrada.", Toast.LENGTH_SHORT).show();
            finish();
        }
        return camera; // returns null if camera is unavailable
    }

}
