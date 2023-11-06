package com.example.hutchhub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hutchhub.Models.Schedules;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SavedFeedingSchedule extends AppCompatActivity {

    DatabaseReference schedueDB = FirebaseDatabase
            .getInstance().getReference("FeedingSchedule")
            .child(FirebaseAuth.getInstance().getCurrentUser().getUid());

    TextView feedingMorningTime,feedingAfterNoonTime,feedingEveningTime;
    Boolean set;

    Schedules schedules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_feeding_schedule);

        feedingMorningTime = findViewById(R.id.feedingMorningTime);
        feedingAfterNoonTime = findViewById(R.id.feedingAfterNoonTime);
        feedingEveningTime = findViewById(R.id.feedingEveningTime);

        schedueDB.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                if(snapshot.exists()){

                    schedules = snapshot.getValue(Schedules.class);
                    set = true;
                }else{
                    set = false;
                    Toast.makeText(SavedFeedingSchedule.this, "Please Set Your Feeding Schedule", Toast.LENGTH_LONG).show();

                }


            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                if(snapshot.exists()){

                    schedules = snapshot.getValue(Schedules.class);
                    set = true;
                }else{
                    set = false;
                    Toast.makeText(SavedFeedingSchedule.this, "Please Set Your Feeding Schedule", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        if (!set){

            startActivity(new Intent(this,FeedingSchedule.class));
            finish();
        }else {

            feedingMorningTime.setText(schedules.getMorning());
            feedingAfterNoonTime.setText(schedules.getAfternoon());
            feedingEveningTime.setText(schedules.getEvening());


        }

    }
}