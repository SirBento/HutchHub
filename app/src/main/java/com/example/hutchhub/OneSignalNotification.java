package com.example.hutchhub;
import com.onesignal.OneSignal;
import com.onesignal.debug.LogLevel;
import com.onesignal.Continue;
import android.app.Application;
import android.util.Log;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OneSignalNotification  extends Application {

    // NOTE: Replace the below with your own ONESIGNAL_APP_ID
    private static final String ONESIGNAL_APP_ID = "93d4ec61-14a4-407f-a606-55d50aa98198";

    @Override
    public void onCreate() {
        super.onCreate();

        // Verbose Logging set to help debug issues, remove before releasing your app.
        OneSignal.getDebug().setLogLevel(LogLevel.VERBOSE);

        // OneSignal Initialization
        OneSignal.initWithContext(this, ONESIGNAL_APP_ID);

        // requestPermission will show the native Android notification permission prompt.
        // NOTE: It's recommended to use a OneSignal In-App Message to prompt instead.
        OneSignal.getNotifications().requestPermission(false, Continue.none());

         //current user  id
        String id = OneSignal.getUser().getOnesignalId();
        String pushId = OneSignal.getUser().getPushSubscription().getId();



        OneSignal.getNotifications().addClickListener(iNotificationClickEvent -> {

            Log.e("Notification Clicked", "TRUE!!!");
        });

    }




}
