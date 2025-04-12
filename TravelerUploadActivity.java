package com.example.madcourseproject;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TravelerUploadActivity extends AppCompatActivity {
    private final int AADHAR_REQUEST_CODE = 3000;
    private ImageView imgAadhar1;
    private Button nxt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_docs_traveler);

        // Initialize the views here
        imgAadhar1 = findViewById(R.id.Aadhar1);
        nxt1 = findViewById(R.id.nextbtn1);

        Button Aadhar1 = findViewById(R.id.uploadbtn1);

        Aadhar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery, AADHAR_REQUEST_CODE);
            }
        });

        nxt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TravelerUploadActivity.this, TripDetailsTravelerActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && data != null) {
            if(requestCode == AADHAR_REQUEST_CODE) {
                imgAadhar1.setImageURI(data.getData());
            }
        }
    }
}


