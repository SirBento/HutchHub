package com.example.hutchhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;

public class ListRabbit extends AppCompatActivity {
    private Spinner rabbitBreeds;
    private Button  upForSale;
    private EditText rabbitSellerLocation,rabbitAskingPrice,rabbitQuantity,rabbitDescription,rabbitSellerNumber;
    private String breed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_rabbit);

        rabbitBreeds = findViewById(R.id.rabbitBreeds);
        upForSale = findViewById(R.id.btn_upForSale);

        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this,R.array.rabbitBreeds,
                        androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        rabbitBreeds.setAdapter(adapter);

        rabbitSellerLocation = findViewById(R.id.rabbitSellerLocation);
        rabbitAskingPrice = findViewById(R.id.rabbitAskingPrice);
        rabbitQuantity = findViewById(R.id.rabbitQuantity);
        rabbitDescription = findViewById(R.id.rabbitDescription);
        rabbitSellerNumber = findViewById(R.id.rabbitSellerNumber);


     upForSale.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             breed = rabbitBreeds.getSelectedItem().toString();

         }
     });
}

}