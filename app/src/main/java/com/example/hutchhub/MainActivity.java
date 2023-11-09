package com.example.hutchhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

     private ConstraintLayout
             breedAndCare, pregTrack, Chat, FeedingSchedule, buySell,
             records,growthAndWeight, knowledge, btn_Logout;

    DatabaseReference User = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        breedAndCare = findViewById(R.id.breedAndCare);
        pregTrack=findViewById(R.id.pregTrack);
        Chat= findViewById(R.id.Chat);
        FeedingSchedule = findViewById(R.id.FeedingSchedule);
        buySell =findViewById(R.id.buySell);
        records = findViewById(R.id.records);
        growthAndWeight =findViewById(R.id.growthAndWeight);
        knowledge = findViewById(R.id.knowledge);
        btn_Logout = findViewById(R.id.btn_Logout);


        breedAndCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent( MainActivity.this, BreedingCareList.class));
            }
        });

        pregTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( MainActivity.this, PregTracker.class));
            }
        });

        Chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( MainActivity.this, Chat.class));
            }
        });

        FeedingSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( MainActivity.this, FeedingSchedule.class));
            }
        });

        buySell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( MainActivity.this, BuySell.class));

            }
        });

        records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( MainActivity.this, Records.class));

            }
        });

        growthAndWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( MainActivity.this, GrowthWeightList.class));

            }
        });

        knowledge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( MainActivity.this,Knowledge.class));

            }
        });

        btn_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseMessaging.getInstance().deleteToken().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(MainActivity.this, "Log Out Successful", Toast.LENGTH_LONG).show();
                        startActivity(new Intent( MainActivity.this, Login.class) );
                        finish();
                    }
                });

            }
        });

        getFCMtoken();


    }

    private  void getFCMtoken(){

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {

                if(task.isSuccessful()){

                    String token = task.getResult();
                    Log.i("My Token",token);
                    HashMap<String, Object> fcmToken = new HashMap<>();
                    fcmToken.put("fcmToken",token);
                    User.updateChildren(fcmToken);
                }

            }
        });

    }
}