package com.example.hutchhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GrowthWeightList extends AppCompatActivity {
    FloatingActionButton btn_addRabbitGrowthWeight;
    DatabaseReference dbGrowthWeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_growth_weight_list);

        btn_addRabbitGrowthWeight = findViewById(R.id.btn_addRabbitGrowthWeight);

        dbGrowthWeight = FirebaseDatabase
                .getInstance()
                .getReference("GrowthWeight")
                .child(FirebaseAuth.getInstance()
                        .getCurrentUser()
                        .getUid());

        btn_addRabbitGrowthWeight.setOnClickListener(view -> {

            startActivity(new Intent( GrowthWeightList.this, GrowthWeight.class));

            //Todo: open the activity with already available data. make edit texts visible or unvisible

        });

    }
}