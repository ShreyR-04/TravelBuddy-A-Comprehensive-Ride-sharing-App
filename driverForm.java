package com.example.madcourseproject;



import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class driverForm extends AppCompatActivity {

    private EditText nameEditText;
    private EditText phoneEditText;
    private EditText carNumberEditText;
    private EditText carNameEditText;
    private EditText carColorEditText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driverform);

        // Initialize views
        nameEditText = findViewById(R.id.tdeditText1);
        phoneEditText = findViewById(R.id.tdeditText2);
        carNumberEditText = findViewById(R.id.tdeditText3);
        carNameEditText = findViewById(R.id.tdeditText4);
        carColorEditText = findViewById(R.id.tdeditText5);
        submitButton = findViewById(R.id.tdSubmitBtn);

        // Set click listener for the submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });
    }

    private void submitForm() {
        // Get the input values
        String name = nameEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();
        String carNumber = carNumberEditText.getText().toString().trim();
        String carName = carNameEditText.getText().toString().trim();
        String carColor = carColorEditText.getText().toString().trim();

        // Validate the input fields
        if (TextUtils.isEmpty(name)) {
            nameEditText.setError("Please enter your name");
            nameEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(phone)) {
            phoneEditText.setError("Please enter your phone number");
            phoneEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(carNumber)) {
            carNumberEditText.setError("Please enter your car number");
            carNumberEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(carName)) {
            carNameEditText.setError("Please enter your car name");
            carNameEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(carColor)) {
            carColorEditText.setError("Please enter your car color");
            carColorEditText.requestFocus();
            return;
        }

        // All fields are validated, proceed with form submission
        Toast.makeText(this, "Form submitted successfully!", Toast.LENGTH_LONG).show();

        // Navigate to DocumentUploadActivity
        Intent intent = new Intent(driverForm.this, uploadDocs.class);
        startActivity(intent);
    }
}

