package com.example.hutchhub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BuySell extends AppCompatActivity {

    private Button buy, sell, sellList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_sell);

        buy = findViewById(R.id.btn_buy);
        sell = findViewById(R.id.btn_sell);
        sellList = findViewById(R.id.btn_sellList);

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(BuySell.this,BuyRabbit.class));
                finish();

            }
        });

        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(BuySell.this,ListRabbit.class));
                finish();

            }
        });

        sellList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuySell.this,MySellList.class));
                finish();
            }
        });

    }
}