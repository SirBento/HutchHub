package com.example.hutchhub.Broadcasters;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Vibrator;
import androidx.core.app.NotificationCompat;

import com.example.hutchhub.R;

public class AlarmReceiver extends BroadcastReceiver {

    private MediaPlayer mediaPlayer;
    private Vibrator vibrator;

    @Override
    public void onReceive(Context context, Intent intent) {

        // Create a notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "Feeding")
                .setContentTitle("Reminder")
                .setContentText("Time to feed your rabbits")
                .setSmallIcon(R.drawable.feeding)
                .setAutoCancel(true);

        // Get the NotificationManager instance
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Create a notification channel (required for Android 8.0 and above)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("Feeding", "Time to feed your rabbits", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        // Send the notification
        notificationManager.notify(0, builder.build());

        // Play sound or vibration for 8 seconds
        mediaPlayer = MediaPlayer.create(context, R.raw.alarm_sound);
        mediaPlayer.start();

        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(8000); // Vibrate for 8 seconds

        // Stop the alarm after 8 seconds
        new android.os.Handler().postDelayed(() -> {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
            if (vibrator != null) {
                vibrator.cancel();
            }
        }, 8000);
    }
}