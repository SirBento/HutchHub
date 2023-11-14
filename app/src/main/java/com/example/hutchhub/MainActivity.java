package com.example.hutchhub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

     private ConstraintLayout
             breedAndCare, pregTrack, Chat, FeedingSchedule, buySell,
             records,growthAndWeight, knowledge, btn_Logout;

        String UserID= FirebaseAuth.getInstance().getCurrentUser().getUid();
    DatabaseReference User = FirebaseDatabase.getInstance().getReference("Users").child(UserID);
    DatabaseReference SellsRef = FirebaseDatabase.getInstance().getReference().child("Sells");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        breedAndCare = findViewById(R.id.breedAndCare);
        pregTrack=findViewById(R.id.pregTrack);
        Chat= findViewById(R.id.Chat);
        FeedingSchedule = findViewById(R.id.FeedingSchedule);
        buySell =findViewById(R.id.buySell);
        records = findViewById(R.id.records);
        growthAndWeight =findViewById(R.id.growthAndWeight);
        knowledge = findViewById(R.id.knowledge);
        btn_Logout = findViewById(R.id.btn_Logout);


        breedAndCare.setOnClickListener(v -> startActivity(new Intent( MainActivity.this, BreedingCareList.class)));

        pregTrack.setOnClickListener(v -> startActivity(new Intent( MainActivity.this, PregTracker.class)));

        Chat.setOnClickListener(v -> startActivity(new Intent( MainActivity.this, Chat.class)));

        FeedingSchedule.setOnClickListener(v -> startActivity(new Intent( MainActivity.this, FeedingSchedule.class)));

        buySell.setOnClickListener(v -> startActivity(new Intent( MainActivity.this, BuySell.class)));

        records.setOnClickListener(v -> startActivity(new Intent( MainActivity.this, Records.class)));

        growthAndWeight.setOnClickListener(v -> startActivity(new Intent( MainActivity.this, GrowthWeightList.class)));

        knowledge.setOnClickListener(v -> startActivity(new Intent( MainActivity.this,Knowledge.class)));

        btn_Logout.setOnClickListener(v -> FirebaseMessaging.getInstance().deleteToken().addOnCompleteListener(task -> {

            FirebaseAuth.getInstance().signOut();
            Toast.makeText(MainActivity.this, "Log Out Successful", Toast.LENGTH_LONG).show();
            startActivity(new Intent( MainActivity.this, Login.class) );
            finish();
        }));

        getFCMtoken();


    }

    private  void getFCMtoken(){

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {

            if(task.isSuccessful()){

                String token = task.getResult();
                Log.i("My Token",token);
                HashMap<String, Object> fcmToken = new HashMap<>();
                fcmToken.put("fcmToken",token);// change to key
                User.updateChildren(fcmToken);


                HashMap<String, Object> fcmKey = new HashMap<>();
                fcmKey.put("key",token);// change to key

                /**Find a way to update the Breeding Care node **/
                SellsRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){

                            if(UserID.equals(snapshot.hasChild("sellerId"))) {

                                SellsRef.child(snapshot.getKey()).updateChildren(fcmKey);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }

        });

    }
}