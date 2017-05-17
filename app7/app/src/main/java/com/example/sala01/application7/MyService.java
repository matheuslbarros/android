package com.example.sala01.application7;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

    public MyService() {}

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(getClass().getName(), "onBind");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(getClass().getName(), "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(getClass().getName(), "onStartCommand");
        Toast.makeText(getApplicationContext(), "Executando...", Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Log.i(getClass().getName(), "onTaskRemoved");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(getClass().getName(), "onDestroy");
    }
}
