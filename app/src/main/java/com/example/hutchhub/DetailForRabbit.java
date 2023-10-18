package com.example.hutchhub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class DetailForRabbit extends AppCompatActivity {

    String PhoneNum, Address, Breed, Description, Price, Quantity;
    TextView detPhone, detLocation,detQuantity,detBreed,detPrice;
    EditText detDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_for_rabbit);

        detPhone = findViewById(R.id.detPhone);
        detLocation = findViewById(R.id.detLocation);
        detQuantity = findViewById(R.id.detQuantity);
        detBreed = findViewById(R.id.detBreed);
        detPrice = findViewById(R.id.detPrice);
        detDescription = findViewById(R.id.detDescription);

        populateValues();

        detPhone.setText(PhoneNum);
        detLocation.setText(Address);
        detQuantity.setText(Quantity);
        detBreed.setText(Breed);
        detPrice.setText(Price);
        detDescription.setText(Description);


    }

    private void populateValues(){

        PhoneNum = getIntent().getStringExtra("PhoneNum");
        Address = getIntent().getStringExtra("Address");
        Breed= getIntent().getStringExtra("Breed");
        Description = getIntent().getStringExtra("Description");
        Price = getIntent().getStringExtra("Price");
        Quantity = getIntent().getStringExtra("Quantity");


    }
}