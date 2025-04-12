package com.example.madcourseproject;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class PaymentModeActivity extends AppCompatActivity {
    private Button offlineButton, onlineButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_mode);

        offlineButton = findViewById(R.id.modebtn1);
        onlineButton = findViewById(R.id.modebtn2);

        offlineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle offline payment
                // You can add an intent to redirect to an offline payment page/activity if required
                Toast.makeText(PaymentModeActivity.this, "Offline payment selected", Toast.LENGTH_SHORT).show();
            }
        });

        onlineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to the PaymentActivity for online payment
                startActivity(new Intent(PaymentModeActivity.this, PaymentActivity.class));
            }
        });


    }
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Offline Payment";
            String description = "Notification for offline payment selection";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("offline_payment", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    private void showOfflinePaymentNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "offline_payment")
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Offline Payment Selected")
                .setContentText("Please call the driver to confirm your payment.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // Request missing permissions, and then override onRequestPermissionsResult to handle the case where the user grants the permission.
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1);
            return;
        }
        notificationManager.notify(2, builder.build());
    }


}


