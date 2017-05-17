package com.example.sala01.application7;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(getClass().getName(), "onReceive");

        Intent serviceIntent = new Intent(context, MyService.class);
        context.startService(serviceIntent);
    }
}
