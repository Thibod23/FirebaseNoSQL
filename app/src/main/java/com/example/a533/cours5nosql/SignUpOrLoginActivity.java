package com.example.a533.cours5nosql;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.common.oob.SignUp;

public class SignUpOrLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_or_login);
        setListener();
    }

    private void setListener(){
        findViewById(R.id.button_GoToSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToSignUpActivity();
            }
        });

        findViewById(R.id.button_GoToLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToLoginActivity();
            }
        });
    }

    private void sendToSignUpActivity(){
        Intent sendToSignUp = new Intent(this,SignUpActivity.class);
        startActivity(sendToSignUp);
    }

    private void sendToLoginActivity(){
        Intent sendToLogin = new Intent(this,LoginActivity.class);
        startActivity(sendToLogin);
    }
}
