package com.example.hutchhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PregTracker extends AppCompatActivity {

    FloatingActionButton  btn_fab_add_Preg_Dates;

    RecyclerView rabbit_Preg_List ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preg_tracker);

        rabbit_Preg_List = findViewById(R.id.rabbit_Preg_List);
        btn_fab_add_Preg_Dates = findViewById(R.id.btn_fab_add_Preg_Dates);


        rabbit_Preg_List.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if(dy>0){
                    btn_fab_add_Preg_Dates.hide();

                }else{

                    btn_fab_add_Preg_Dates.show();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        btn_fab_add_Preg_Dates.setOnClickListener(view -> {

            startActivity(new Intent(this,Add_PregRabbit_Details.class));
            finish();

        });



        // Todo: add recycler to view preg dates, weaning and breeding box dates
    }
}