package com.example.madcourseproject;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class TripDetailsTravelerActivity extends AppCompatActivity {
    private EditText locationEditText, destinationEditText, dateEditText, timeEditText, passengersEditText;
    private Button payButton;

    // Notification channel ID and name
    private static final String CHANNEL_ID = "trip_submission";
    private static final String CHANNEL_NAME = "Trip Submission";

    // Database helper instance
    private TripDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_trip2);

        // Initialize the views
        locationEditText = findViewById(R.id.tdeditText1);
        destinationEditText = findViewById(R.id.tdeditText2);
        dateEditText = findViewById(R.id.tdeditText3);
        timeEditText = findViewById(R.id.tdeditText4);
        passengersEditText = findViewById(R.id.tdeditText5);
        payButton = findViewById(R.id.tdSubmitBtn);

        // Initialize the database helper
        dbHelper = new TripDatabaseHelper(this);

        // Set an onClickListener for the pay button
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitTripDetailsAndProceedToPayment();
            }
        });

        // Create notification channel
        createNotificationChannel();
    }

    private void submitTripDetailsAndProceedToPayment() {
        String location = locationEditText.getText().toString().trim();
        String destination = destinationEditText.getText().toString().trim();
        String date = dateEditText.getText().toString().trim();
        String time = timeEditText.getText().toString().trim();
        String passengers = passengersEditText.getText().toString().trim();

        if (location.isEmpty() || destination.isEmpty() || date.isEmpty() || time.isEmpty() || passengers.isEmpty()) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
        } else {
            // Insert trip details into the database
            long result = dbHelper.insertTripDetails(location, destination, date, time, passengers);

            if (result != -1) {
                // Display notification on successful submission
                showNotification();

                // Redirect the user to the payment mode selection interface
                startActivity(new Intent(TripDetailsTravelerActivity.this, PaymentModeActivity.class));
            } else {
                Toast.makeText(this, "Failed to store trip details", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = CHANNEL_NAME;
            String description = "Notification for trip submission";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void showNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Trip Submission")
                .setContentText("Trip details submitted successfully")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(1, builder.build());
    }
}






