package com.example.hutchhub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hutchhub.Adapters.BreadingAndCareAdapater;
import com.example.hutchhub.Adapters.BuyRabbitAdapter;
import com.example.hutchhub.Models.BreedingAndCare;
import com.example.hutchhub.Models.RabbitForSale;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.StandardSocketOptions;
import java.util.ArrayList;

public class BreedingCareList extends AppCompatActivity {

    FloatingActionButton btn_Breed_Care_Add;
    RecyclerView Breed_Care_R_List;
    SearchView Breed_Care_SearchView;
    TextView Breed_Care_NoRecord;
    BreadingAndCareAdapater breadingAndCareAdapater;
    ArrayList<BreedingAndCare> BreedingAndCareArrayList;
    DatabaseReference detailsfromdb;
    FirebaseAuth auth;
    String currentuserId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeding_care_list);

        Breed_Care_R_List= findViewById(R.id.Breed_Care_R_List);
        btn_Breed_Care_Add = findViewById(R.id.btn_Breed_Care_Add);
        Breed_Care_SearchView = findViewById(R.id.Breed_Care_SearchView);
        Breed_Care_NoRecord = findViewById(R.id.Breed_Care_NoRecord);

        auth = FirebaseAuth.getInstance();
        currentuserId =auth.getCurrentUser().getUid();
        detailsfromdb = FirebaseDatabase.getInstance().getReference().child("BreedingCare");

        BreedingAndCareArrayList = new ArrayList<>();
        breadingAndCareAdapater = new BreadingAndCareAdapater(BreedingAndCareArrayList);
        Breed_Care_R_List.setHasFixedSize(true);
        Breed_Care_R_List.setLayoutManager(new LinearLayoutManager(this));
        Breed_Care_R_List.addItemDecoration(new DividerItemDecoration(Breed_Care_R_List.getContext(),DividerItemDecoration.VERTICAL));
        Breed_Care_R_List.setAdapter(breadingAndCareAdapater);


        btn_Breed_Care_Add.setOnClickListener(view -> {

            startActivity(new Intent(this,BreedingCare.class));
            finish();

        });


        Breed_Care_SearchView.clearFocus();
        Breed_Care_SearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        detailsfromdb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                if(snapshot.exists()){
                    String id = snapshot.child("Id").getValue().toString();
                    if(id.equals(currentuserId)){
                        BreedingAndCare breedingAndCare =  snapshot.getValue(BreedingAndCare.class);
                        BreedingAndCareArrayList.add(breedingAndCare);
                        breadingAndCareAdapater.notifyDataSetChanged();
                        Breed_Care_R_List.smoothScrollToPosition(Breed_Care_R_List.getAdapter().getItemCount());

                    }else{

                        Breed_Care_NoRecord.setVisibility(View.VISIBLE);
                    }

                }else{
                    Breed_Care_NoRecord.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    private void filterList(String text) {

        ArrayList<BreedingAndCare> filteredlist = new ArrayList<>();

        for (BreedingAndCare breedingAndCare : BreedingAndCareArrayList) {

            if (breedingAndCare.getBuck_Breed().toLowerCase().contains(text.toLowerCase())) {

                filteredlist.add(breedingAndCare);
            }
            if (breedingAndCare.getDoe_Breed().toLowerCase().contains(text.toLowerCase())) {

                filteredlist.add(breedingAndCare);
            }
        }

        if (filteredlist.isEmpty()) {
            Toast.makeText(this, "No Rabbit By that Name is available", Toast.LENGTH_LONG).show();
        } else {

            breadingAndCareAdapater.setFilteredList(filteredlist);
        }

    }

}