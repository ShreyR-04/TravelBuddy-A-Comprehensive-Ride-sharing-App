package com.example.madcourseproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton, signUpButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);

        databaseHelper = new DatabaseHelper(this);

        emailEditText = findViewById(R.id.logineditText1);
        passwordEditText = findViewById(R.id.logineditText2);
        loginButton = findViewById(R.id.loginbutton);
        signUpButton = findViewById(R.id.loginbutton1);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter all the details", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isValidUser = databaseHelper.checkUser(email, password);
                    if (isValidUser) {
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, QuestionActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}



