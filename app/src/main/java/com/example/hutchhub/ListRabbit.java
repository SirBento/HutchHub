package com.example.hutchhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ListRabbit extends AppCompatActivity {
    private Spinner rabbitBreeds;
    private Button  upForSale;
    private EditText rabbitSellerLocation,rabbitAskingPrice,rabbitQuantity,rabbitDescription,rabbitSellerNumber;
    private String breed,description,quantity,price,address,sellerNum ;
   private DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("Sells");

     private LoadingDialog loadingDialog = new LoadingDialog(ListRabbit.this);
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

             description = rabbitDescription.getText().toString().trim();
             quantity = rabbitQuantity.getText().toString().trim();
             price = rabbitAskingPrice.getText().toString().trim();
             address = rabbitSellerLocation.getText().toString().trim();
             sellerNum = rabbitSellerNumber.getText().toString().trim();
             breed = rabbitBreeds.getSelectedItem().toString();

             if(!validateAddresss() | !validateDes() | !validatePhoneNumber() |!validateQuantity()|!validateAsking()){

                 return;

             }else{
                 loadingDialog.startLoadingDialog();
                 HashMap<String, String> rabbitForSale = new HashMap<>();

                 rabbitForSale.put("sellerId", FirebaseAuth.getInstance().getUid().toString());
                 rabbitForSale.put("phone", sellerNum);
                 rabbitForSale.put("price", price);
                 rabbitForSale.put("address", address);
                 rabbitForSale.put("breed", breed);
                 rabbitForSale.put("quantity", quantity);
                 rabbitForSale.put("description", description);
                 rabbitForSale.put("fcm",);
                 databaseRef.push().setValue(rabbitForSale);
                 loadingDialog.dismissDialog();

                 startActivity(new Intent(ListRabbit.this, MySellList.class));
                 finish();

             }



         }
     });
}


    private boolean validateDes(){


        if(description.isEmpty()){
            rabbitDescription.setError("A Short Description Is Required");
            rabbitDescription.requestFocus();
            return false ;

            //checking if the name entered has a valid length
        } else if (description.length() <= 5) {
            rabbitDescription.setError("Please Provide More Information");
            rabbitDescription.requestFocus();
            return false;

        } else {
            rabbitDescription.setError(null);
            return true;

        }

    }

    private boolean validateQuantity(){


        if(quantity.isEmpty()){
            rabbitQuantity.setError("Rabbit Quantity Is Required");
            rabbitQuantity.requestFocus();
            return false ;

        } else {
            rabbitQuantity.setError(null);
            return true;

        }

    }


    private boolean validateAsking(){

        if(price.isEmpty()){
            rabbitAskingPrice.setError("Asking Price Is Required");
            rabbitAskingPrice.requestFocus();
            return false ;

        } else {
            rabbitAskingPrice.setError(null);
            return true;

        }

    }


    private boolean validateAddresss(){
        // Get entered name, trim to remove extra white space

        //checking if the name text box is filled or not
        if(address.isEmpty()){
            rabbitSellerLocation.setError("Your Address is required");
            rabbitSellerLocation.requestFocus();
            return false ;

            //checking if the name entered has a valid length
        } else if (address.length() <= 5) {
            rabbitSellerLocation.setError("Your Address is invalid");
            rabbitSellerLocation.requestFocus();
            return false;

        } else {
            rabbitSellerLocation.setError(null);
            return true;

        }
    }

    private boolean validatePhoneNumber(){

        // Get entered phone number, trim to remove extra white space

        // checking if the phone number field is empty or not
        if (sellerNum .isEmpty()) {

            rabbitSellerNumber.setError("Your Phone Number Is Required");
            rabbitSellerNumber.requestFocus();
            return false;
            //checking the length and validity of the phone number
        }else if (sellerNum.length() > 10 || sellerNum.length() < 10) {

            rabbitSellerNumber.setError("Enter a valid number");
            rabbitSellerNumber.requestFocus();
            return false ;

        } else {
            rabbitSellerNumber.setError(null);
            return true;

        }

    }
}