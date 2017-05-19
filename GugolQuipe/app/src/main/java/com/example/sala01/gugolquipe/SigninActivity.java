package com.example.sala01.gugolquipe;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class SigninActivity extends BaseActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private EditText mEmailField, mPasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mEmailField = (EditText) findViewById(R.id.editEmail);
        mPasswordField = (EditText) findViewById(R.id.editPassword);

        mAuth = FirebaseAuth.getInstance();
    }

    public void cadastrar(View view) {
        showProgressDialog();

        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        hideProgressDialog();

                        if (task.isSuccessful()) {
                            onAuthSuccess(task.getResult().getUser());
                        } else {
                            toast("Falha ao criar usuário");
                        }
                    }
                });
    }

    private void onAuthSuccess(FirebaseUser user) {
        writeNewUser(user.getUid(), user.getEmail());

        startActivity(new Intent(SigninActivity.this, MainActivity.class));
        finish();
    }

    private void writeNewUser(String id, String email) {
        User user = new User();
        user.id = id;
        user.email = email;
        mDatabase.child("users").child(id).setValue(user);
    }

}
