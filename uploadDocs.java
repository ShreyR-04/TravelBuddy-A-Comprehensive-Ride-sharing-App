package com.example.madcourseproject;



import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class uploadDocs extends AppCompatActivity {
    private final int AADHAR_REQUEST_CODE = 1000;
    private final int DL_REQUEST_CODE = 2000;

    ImageView imgAadhar;
    ImageView imgDL;
    Button nxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_docs);

        imgAadhar = findViewById(R.id.Aadhar1);
        imgDL = findViewById(R.id.DrivingLic);
        Button Aadhar = findViewById(R.id.uploadbtn1);
        Button DrL = findViewById(R.id.uploadbtn2);
        nxt = findViewById(R.id.nextbtn1);

        Aadhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery, AADHAR_REQUEST_CODE);
            }
        });

        DrL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery, DL_REQUEST_CODE);
            }
        });

        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(uploadDocs.this, TripDetailsDriverActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && data != null) {
            if(requestCode == AADHAR_REQUEST_CODE) {
                imgAadhar.setImageURI(data.getData());
            } else if(requestCode == DL_REQUEST_CODE) {
                imgDL.setImageURI(data.getData());
            }
        }
    }
}


