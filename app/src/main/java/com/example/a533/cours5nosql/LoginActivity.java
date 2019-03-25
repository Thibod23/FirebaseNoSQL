package com.example.a533.cours5nosql;

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

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        setListener();

    }

    private void setListener(){
        findViewById(R.id.buttonLogIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogIn();
            }
        });
    }

    private void LogIn(){
        EditText email = (EditText)findViewById(R.id.editTextEmail);
        EditText password = (EditText)findViewById(R.id.editTextPassword);
        auth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent sendToMainActivity = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(sendToMainActivity);
                        }else{
                            Toast.makeText(getApplicationContext(), "Failed to Login", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
