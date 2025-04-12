package com.example.madcourseproject;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class TripDetailsDriverActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "trip_details_channel";
    private static final int NOTIFICATION_ID = 1;

    EditText locationEditText, destinationEditText, dateEditText, timeEditText, passengersEditText;
    Button submitButton, routeButton;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_trip1);

        // Initialize the views
        locationEditText = findViewById(R.id.tdeditText1);
        destinationEditText = findViewById(R.id.tdeditText2);
        dateEditText = findViewById(R.id.tdeditText3);
        timeEditText = findViewById(R.id.tdeditText4);
        passengersEditText = findViewById(R.id.tdeditText5);
        submitButton = findViewById(R.id.tdSubmitBtn);
        routeButton = findViewById(R.id.routebtn);

        // Initialize the database helper
        databaseHelper = new DatabaseHelper(this);

        // Create the notification channel
        createNotificationChannel();

        // Set click listener for the submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitTripDetails();
            }
        });

        routeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TripDetailsDriverActivity.this,Home.class));
            }
        });
    }

    private void submitTripDetails() {
        // Get the input values
        String location = locationEditText.getText().toString().trim();
        String destination = destinationEditText.getText().toString().trim();
        String date = dateEditText.getText().toString().trim();
        String time = timeEditText.getText().toString().trim();
        String passengers = passengersEditText.getText().toString().trim();

        // Validate the inputs
        if (TextUtils.isEmpty(location) || TextUtils.isEmpty(destination) ||
                TextUtils.isEmpty(date) || TextUtils.isEmpty(time) || TextUtils.isEmpty(passengers)) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save the trip details to the database
        databaseHelper.addTrip(location, destination, date, time, passengers);

        // Show a success message
        Toast.makeText(this, "Trip details submitted successfully!", Toast.LENGTH_SHORT).show();

        // Display a notification
        showNotification();

        // Optionally, you can finish the activity or navigate to another screen
        // finish();
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void showNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Trip Submission")
                .setContentText("Trip details submitted successfully!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // NotificationId is a unique int for each notification that you must define
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
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}




