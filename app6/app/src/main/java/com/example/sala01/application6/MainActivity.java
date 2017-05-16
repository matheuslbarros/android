package com.example.sala01.application6;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends FragmentActivity {

    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            Fragmento fragmento = new Fragmento();
            fragmento.setArguments(getIntent().getExtras());

            transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container, fragmento);
            transaction.commit();
        }
    }

    public void trocar(View view) {
        FragmentoDois fragmentoDois = new FragmentoDois();
        fragmentoDois.setArguments(getIntent().getExtras());

        transaction.replace(R.id.fragment_container, fragmentoDois);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
