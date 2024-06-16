package com.example.hutchhub.Workers;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import android.app.NotificationManager;
import com.example.hutchhub.R;

public class ReminderWorker extends Worker {

    public ReminderWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        // Create a notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "feeding")
                .setContentTitle("Reminder")
                .setContentText("Your reminder message")
                .setSmallIcon(R.drawable.feeding)
                .setAutoCancel(true);

        // Get the NotificationManager instance
        NotificationManager notificationManager = (NotificationManager) getApplicationContext()
                                                        .getSystemService(Context.NOTIFICATION_SERVICE);

        // Send the notification
        notificationManager.notify(0, builder.build());

        return Result.success();
    }
}
