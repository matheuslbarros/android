package com.example.sala01.application8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void abrirMapa(View view) {
        Intent intent = new Intent(getBaseContext(), MapsActivity.class);
        startActivity(intent);
    }
}
