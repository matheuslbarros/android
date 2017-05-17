package com.example.sala01.application7;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public final int ALARM_REPEAT_TIME = 60000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scheduleAlarmReceiver();
    }

    private void scheduleAlarmReceiver() {
        Intent alarm = new Intent(this, MyReceiver.class);
        boolean alarmRunning = (PendingIntent.getBroadcast(this, 0, alarm, PendingIntent.FLAG_NO_CREATE) != null);

        if(alarmRunning == false) {
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alarm, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), ALARM_REPEAT_TIME, pendingIntent);
        }
    }
}
