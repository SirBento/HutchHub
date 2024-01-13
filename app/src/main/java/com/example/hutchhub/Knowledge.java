package com.example.hutchhub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Knowledge extends AppCompatActivity {

    private CardView book1,book2,book3,book4,book5,book6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge);

        book1= findViewById(R.id.book1);
        book2= findViewById(R.id.book2);
        book3= findViewById(R.id.book3);
        book4= findViewById(R.id.book4);
        book5= findViewById(R.id.book5);
        book6= findViewById(R.id.book6);



        book1.setOnClickListener(view -> startActivity(new Intent(Knowledge.this, BreedingTechs.class)));

        book2.setOnClickListener(view -> startActivity(new Intent(Knowledge.this, RabbitHusbandry.class)));

        book3.setOnClickListener(view -> startActivity(new Intent(Knowledge.this, RabbitProduction.class)));

        book4.setOnClickListener(view -> startActivity(new Intent(Knowledge.this, RabbitTracks.class)));

        book5.setOnClickListener(view -> startActivity(new Intent(Knowledge.this, RaisingRabbits.class)));

        book6.setOnClickListener(view -> startActivity(new Intent(Knowledge.this, RaisingRabbbit2.class)));
    }
}