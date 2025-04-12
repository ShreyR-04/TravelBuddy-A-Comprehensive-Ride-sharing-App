package com.example.madcourseproject;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {

    private Button driverButton, travelerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_custom_dialog_box);

        driverButton = findViewById(R.id.quesbtn1);
        travelerButton = findViewById(R.id.quesbtn2);

        driverButton.setOnClickListener(v -> {
            Intent intent = new Intent(QuestionActivity.this, driverForm.class);
            startActivity(intent);
        });

        travelerButton.setOnClickListener(v -> {
            Intent intent = new Intent(QuestionActivity.this, TravelerUploadActivity.class);
            startActivity(intent);
        });
    }
}

