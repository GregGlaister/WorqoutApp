package com.greg19007919.worqoutapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText passwordField2,emailField2;
    Button loginButton;
    TextView toRegButton;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        passwordField2 = findViewById(R.id.passwordField2);
        emailField2 = findViewById(R.id.emailField2);
        loginButton = findViewById(R.id.loginButton);
        toRegButton = findViewById(R.id.toRegButton);
        fAuth = FirebaseAuth.getInstance();

        toRegButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(Login.this,Register.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailField2.getText().toString().trim();
                String password = passwordField2.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    emailField2.setError("please enter an email address");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    passwordField2.setError("please enter a password");
                    return;
                }

                if(password.length() < 6){
                    passwordField2.setError("Please make sure your password is 6 characters or longer");
                    return;
                }

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{
                            Toast.makeText(Login.this, "Error:" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }

                });
            }
        });
    }


}