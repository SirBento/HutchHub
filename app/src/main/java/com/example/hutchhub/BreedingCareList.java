package com.example.hutchhub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.net.StandardSocketOptions;


public class BreedingCareList extends AppCompatActivity {

    FloatingActionButton btn_Breed_Care_Add;

    RecyclerView Breed_Care_R_List;
    SearchView Breed_Care_SearchView;
    TextView Breed_Care_NoRecord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeding_care_list);

        Breed_Care_R_List= findViewById(R.id.Breed_Care_R_List);
        btn_Breed_Care_Add = findViewById(R.id.btn_Breed_Care_Add);
        Breed_Care_SearchView = findViewById(R.id.Breed_Care_SearchView);
        Breed_Care_NoRecord = findViewById(R.id.Breed_Care_NoRecord);


        btn_Breed_Care_Add.setOnClickListener(view -> {

            startActivity(new Intent(this,BreedingCare.class));
            finish();

        });

    }
}