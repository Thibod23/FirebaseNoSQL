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

public class SignUpActivity extends AppCompatActivity {

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        auth = FirebaseAuth.getInstance();
        setListener();
    }

    private void setListener(){
        findViewById(R.id.button_SignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpUser();
            }
        });
    }

    private void signUpUser(){
        EditText userEmail = findViewById(R.id.editText_SignUpEmail);
        EditText password = findViewById(R.id.editText_SignUpPassword);
        EditText passwordConfirm = findViewById(R.id.editText_SignUpPasswordConfirm);
        if(!password.getText().toString().equals(passwordConfirm.getText().toString())){
            grillePain("Password don't match");
            return;
        }
        auth.createUserWithEmailAndPassword(userEmail.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    sendUserToMainActivity();
                }else{
                    Toast.makeText(getApplicationContext(), "Failed to sign up", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendUserToMainActivity(){
        Intent sendToMainActivity = new Intent(this, MainActivity.class);
        startActivity((sendToMainActivity));
    }

    private void grillePain(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
