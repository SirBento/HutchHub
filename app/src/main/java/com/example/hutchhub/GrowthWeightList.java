package com.example.hutchhub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.hutchhub.Adapters.GrowthWeightAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.hutchhub.Models.GrowthWeight;
import java.util.ArrayList;

public class GrowthWeightList extends AppCompatActivity {
    FloatingActionButton btn_addRabbitGrowthWeight;

    RecyclerView RabbitGrowthWeight_R_List;
    SearchView SearchView;
    TextView  Gnw_NoRecord;
    DatabaseReference dbGrowthWeight;
    ArrayList<GrowthWeight> GrowthWeight_A_List;
    GrowthWeightAdapter growthWeightAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_growth_weight_list);

        btn_addRabbitGrowthWeight = findViewById(R.id.btn_addRabbitGrowthWeight);
        SearchView = findViewById(R.id.SearchView);
        RabbitGrowthWeight_R_List = findViewById(R.id.RabbitGrowthWeight_R_List);
        Gnw_NoRecord= findViewById(R.id. Gnw_NoRecord);

        GrowthWeight_A_List = new ArrayList<>();
        growthWeightAdapter = new GrowthWeightAdapter(GrowthWeight_A_List);
        RabbitGrowthWeight_R_List.setHasFixedSize(true);
        RabbitGrowthWeight_R_List.setLayoutManager(new LinearLayoutManager(this));
        RabbitGrowthWeight_R_List.setAdapter(growthWeightAdapter);


        dbGrowthWeight = FirebaseDatabase
                .getInstance()
                .getReference("GrowthWeight")
                .child(FirebaseAuth.getInstance()
                        .getCurrentUser()
                        .getUid());



        SearchView.clearFocus();
        SearchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
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


        btn_addRabbitGrowthWeight.setOnClickListener(view -> {

            startActivity(new Intent(this, com.example.hutchhub.GrowthWeight.class));


        });


        dbGrowthWeight.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                if(!snapshot.exists()){
                    Gnw_NoRecord.setVisibility(View.VISIBLE);
                }else{

                    if(snapshot.getKey().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){

                        com.example.hutchhub.Models.GrowthWeight growthWeight
                                = snapshot.getValue(com.example.hutchhub.Models.GrowthWeight.class);
                        GrowthWeight_A_List.add(growthWeight);
                        growthWeightAdapter.notifyDataSetChanged();
                        RabbitGrowthWeight_R_List.smoothScrollToPosition( RabbitGrowthWeight_R_List.getAdapter().getItemCount());

                    }else{
                        Gnw_NoRecord.setVisibility(View.VISIBLE);

                    }

                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(!snapshot.exists()){
                    Gnw_NoRecord.setVisibility(View.VISIBLE);
                }else{

                    if(snapshot.getKey().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){

                        com.example.hutchhub.Models.GrowthWeight growthWeight
                                = snapshot.getValue(com.example.hutchhub.Models.GrowthWeight.class);
                        GrowthWeight_A_List.add(growthWeight);
                        growthWeightAdapter.notifyDataSetChanged();
                        RabbitGrowthWeight_R_List.smoothScrollToPosition( RabbitGrowthWeight_R_List.getAdapter().getItemCount());

                    }else{
                        Gnw_NoRecord.setVisibility(View.VISIBLE);

                    }

                }

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

        ArrayList<GrowthWeight> filteredlist= new ArrayList<>();

        for (GrowthWeight growthWeight : GrowthWeight_A_List) {

            if (growthWeight.getName().toLowerCase().contains(text.toLowerCase())) {

                filteredlist.add(growthWeight);
            }
        }

        if (filteredlist.isEmpty()) {
            Toast.makeText(this, "No Rabbit By that Name is available", Toast.LENGTH_SHORT).show();
        } else {

            growthWeightAdapter.setFilteredList(filteredlist);
        }

    }
}