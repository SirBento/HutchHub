package com.example.hutchhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DetailForRabbit extends AppCompatActivity {

    String PhoneNum, Address, Breed, Description, Price, Quantity, key, String;
    TextView detPhone, detLocation,detQuantity,detBreed,detPrice;
    EditText detDescription;

    Button btn_detForDone;

    DatabaseReference  userRef;

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
        btn_detForDone = findViewById(R.id.btn_detForDone);
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");

        populateValues();

        detPhone.setText(PhoneNum);
        detLocation.setText(Address);
        detQuantity.setText(Quantity);
        detBreed.setText(Breed);
        detPrice.setText(Price);
        detDescription.setText(Description);


        btn_detForDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });


    }

    private void populateValues(){

        PhoneNum = getIntent().getStringExtra("PhoneNum");
        Address = getIntent().getStringExtra("Address");
        Breed= getIntent().getStringExtra("Breed");
        Description = getIntent().getStringExtra("Description");
        Price = getIntent().getStringExtra("Price");
        Quantity = getIntent().getStringExtra("Quantity");
        key = getIntent().getStringExtra("Key");


    }

    private void getUserName() {
        userRef.child(SellerID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    sellerUsername = snapshot.child("firstname").getValue().toString();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void SendNotification(String displaymessage) {


        try{
            JSONObject jsonObject  = new JSONObject();

            JSONObject notificationObj = new JSONObject();
            notificationObj.put("title",sellerUsername);
            notificationObj.put("body",displaymessage);

            JSONObject dataObj = new JSONObject();
            dataObj.put("userId",FirebaseAuth.getInstance().getCurrentUser().getUid());

            jsonObject.put("notification",notificationObj);
            jsonObject.put("data",dataObj);
            jsonObject.put("to",key);

            callApi(jsonObject);


        }catch (Exception e){

        }


    }

   private void callApi(JSONObject jsonObject){
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        String url = "https://fcm.googleapis.com/fcm/send";
        RequestBody body = RequestBody.create(jsonObject.toString(),JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Authorization","Bearer 9018976dd8f78d5aa658d1d0cf1d5f76e561a224")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

            }
        });

    }
}