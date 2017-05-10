package com.example.sala01.application3;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final int EDIT_DESCRIPTION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView buttonEditar = (TextView) findViewById(R.id.buttonEditar);

        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DescriptionActivity.class);
                startActivityForResult(intent, EDIT_DESCRIPTION);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDIT_DESCRIPTION) {
            TextView textDescricao = (TextView) findViewById(R.id.textDescricao);
            textDescricao.setText(data.getStringExtra("descricao"));
        }
    }
}
