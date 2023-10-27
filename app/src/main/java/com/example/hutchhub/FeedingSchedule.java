package com.example.hutchhub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.example.hutchhub.Classses.AlertReceiver;

import java.text.DateFormat;
import java.util.Calendar;


public class FeedingSchedule extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{
    private EditText FeedingSchedule_NumberOfRabbits, FeedingSchedule_TypeOfFeed,FeedingSchedule_FeedingTimes,
                     FeedingSchedule_Time1,FeedingSchedule_Time2,FeedingSchedule_Time3;
    private Button FeedingSchedule_Next, FeedingSchedule_Done, btn_FeedingSchedule_Time1,
            btn_FeedingSchedule_Time2,btn_FeedingSchedule_Time3 ;

    private LinearLayout time1_Layout,time2_Layout,time3_Layout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeding_schedule);

        //Initialising Layouts
        time1_Layout = findViewById(R.id.time1_Layout);
        time2_Layout = findViewById(R.id.time2_Layout);
        time3_Layout = findViewById(R.id.time3_Layout);

        //initialising Buttons
        FeedingSchedule_Next = findViewById(R.id.FeedingSchedule_Next);
        FeedingSchedule_Done  = findViewById(R.id.FeedingSchedule_Done);
        btn_FeedingSchedule_Time1 = findViewById(R.id.btn_FeedingSchedule_Time1);
        btn_FeedingSchedule_Time2 = findViewById(R.id.btn_FeedingSchedule_Time2);
        btn_FeedingSchedule_Time3 = findViewById(R.id.btn_FeedingSchedule_Time3);

        //initialising EditText

        FeedingSchedule_NumberOfRabbits =findViewById(R.id.FeedingSchedule_NumberOfRabbits);
        FeedingSchedule_TypeOfFeed =findViewById(R.id. FeedingSchedule_TypeOfFeed);
        FeedingSchedule_FeedingTimes =findViewById(R.id.FeedingSchedule_FeedingTimes);
        FeedingSchedule_Time1  =findViewById(R.id.FeedingSchedule_Time1);
        FeedingSchedule_Time2   =findViewById(R.id.FeedingSchedule_Time2);
        FeedingSchedule_Time3  =findViewById(R.id.FeedingSchedule_Time3);


        // todo: check the data enter ond make edittext visible based on that

        // todo: also save user information to the database aswell as validate user entered information


        btn_FeedingSchedule_Time1.setOnClickListener(view -> {

            DialogFragment timePicker = new TimePickerFragment();
            timePicker.show(getSupportFragmentManager(), "time picker");
        });

        btn_FeedingSchedule_Time2.setOnClickListener(view -> {

            DialogFragment timePicker = new TimePickerFragment();
            timePicker.show(getSupportFragmentManager(), "time picker");
        });

        btn_FeedingSchedule_Time3.setOnClickListener(view -> {

                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");

        });



    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {


        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        updateTimeText(c);
        startAlarm(c);


    }


    private void updateTimeText(Calendar c) {
        String timeText = "Alarm set for: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());

       /// mTextView.setText(timeText);   Todo: do something here
    }

    private void startAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_IMMUTABLE);

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }

    private void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_IMMUTABLE);

        alarmManager.cancel(pendingIntent);
        // alarm cancelled
    }
}
