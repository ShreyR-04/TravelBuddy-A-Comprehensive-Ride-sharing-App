package com.example.madcourseproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private EditText nameEditText, emailEditText, phoneEditText, passwordEditText;
    private Button signUpButton, alreadyHaveAccountButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupactivity);

        databaseHelper = new DatabaseHelper(this);

        nameEditText = findViewById(R.id.tdeditText1);
        emailEditText = findViewById(R.id.editText1);
        phoneEditText = findViewById(R.id.tdeditText2);
        passwordEditText = findViewById(R.id.editText4);
        signUpButton = findViewById(R.id.button3);
        alreadyHaveAccountButton = findViewById(R.id.button4);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String phone = phoneEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (!databaseHelper.checkUser(email)) {
                        boolean isInserted = databaseHelper.addUser(name, email, phone, password);
                        if (isInserted) {
                            Toast.makeText(SignUpActivity.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUpActivity.this, QuestionActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignUpActivity.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignUpActivity.this, "Email already exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        alreadyHaveAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}


