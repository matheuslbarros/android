package com.example.sala01.app1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        ArrayList<String> data = new ArrayList<>();
        data.add("todo 1");
        data.add("todo 2");

        // ListView listaa = (ListView) R.id.listaa;
        GridLayout lista = (GridLayout) R.id.lista;

        for(String todo : data) {
            TextView a = new TextView(getBaseContext());
            a.setText(todo);
            lista.addView(a);
        }
        */
    }
}
