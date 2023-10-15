package com.example.hutchhub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

     private ConstraintLayout
             breedAndCare, pregTrack, Chat, FeedingSchedule, buySell,
             records,growthAndWeight, knowledge, btn_Logout;
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

                startActivity(new Intent( MainActivity.this, BreedingCare.class));
                finish();
            }
        });

        pregTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( MainActivity.this, PregTracker.class));
                finish();
            }
        });

        Chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( MainActivity.this, Chat.class));
                finish();
            }
        });

        FeedingSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( MainActivity.this, FeedingSchedule.class));
                finish();
            }
        });

        buySell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( MainActivity.this, BuySell.class));
                finish();
            }
        });

        records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( MainActivity.this, Records.class));
                finish();
            }
        });

        growthAndWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( MainActivity.this, GrowthWeight.class));
                finish();
            }
        });

        knowledge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( MainActivity.this,Knowledge.class));
                finish();
            }
        });

        btn_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this, "Log Out Successful", Toast.LENGTH_LONG).show();
                startActivity(new Intent( MainActivity.this, Login.class) );
                finish();
            }
        });


    }
}