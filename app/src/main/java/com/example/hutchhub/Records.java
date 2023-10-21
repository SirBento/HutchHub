package com.example.hutchhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class Records extends AppCompatActivity {
    FloatingActionButton fab;
    RecyclerView rabbit_Record_List;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);


        fab = findViewById(R.id.fab);

        rabbit_Record_List = findViewById(R.id.rabbit_Record_List);
        rabbit_Record_List.setItemAnimator(new SlideInUpAnimator());


        rabbit_Record_List.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if(dy>0){
                    fab.hide();

                }else{

                    fab.show();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}