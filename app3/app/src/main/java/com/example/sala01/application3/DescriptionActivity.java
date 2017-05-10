package com.example.sala01.application3;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
    }

    public void onClick(View view) {
        EditText editDescricao = (EditText) findViewById(R.id.editDescricao);

        Intent output = new Intent();
        output.putExtra("descricao", editDescricao.getText().toString());
        setResult(RESULT_OK, output);
        finish();
    }
}
