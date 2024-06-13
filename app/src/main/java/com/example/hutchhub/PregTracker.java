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
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.hutchhub.Adapters.PregRabbitDetailsAdapter;
import com.example.hutchhub.Models.BreedingAndCare;
import com.example.hutchhub.Models.PregRabbitDetails;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PregTracker extends AppCompatActivity {

    FloatingActionButton  btn_fab_add_Preg_Dates;
    RecyclerView rabbit_Preg_List;
    SearchView SearchView;
    TextView noPregData;
    PregRabbitDetailsAdapter pregRabbitDetailsAdapter;
    ArrayList<PregRabbitDetails> PregRabbitDetailsArrayList;
    DatabaseReference detailsfromdb;
    FirebaseAuth auth;
    String currentuserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preg_tracker);

        rabbit_Preg_List = findViewById(R.id.rabbit_Preg_List);
        btn_fab_add_Preg_Dates = findViewById(R.id.btn_fab_add_Preg_Dates);
        SearchView = findViewById(R.id.SearchView);
        noPregData = findViewById(R.id.noPregData);


        auth = FirebaseAuth.getInstance();
        currentuserId =auth.getCurrentUser().getUid();
        detailsfromdb = FirebaseDatabase.getInstance().getReference().child("PregRabbitRecord");

        PregRabbitDetailsArrayList = new ArrayList<>();
        pregRabbitDetailsAdapter = new PregRabbitDetailsAdapter(PregRabbitDetailsArrayList);
        rabbit_Preg_List.setHasFixedSize(true);
        rabbit_Preg_List.setLayoutManager(new LinearLayoutManager(this));
        rabbit_Preg_List.addItemDecoration(new DividerItemDecoration(rabbit_Preg_List.getContext(),DividerItemDecoration.VERTICAL));
        rabbit_Preg_List.setAdapter(pregRabbitDetailsAdapter);


        rabbit_Preg_List.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if(dy>0){
                    btn_fab_add_Preg_Dates.hide();

                }else{

                    btn_fab_add_Preg_Dates.show();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        btn_fab_add_Preg_Dates.setOnClickListener(view -> {

            startActivity(new Intent(this,Add_PregRabbit_Details.class));
            finish();

        });

        SearchView.clearFocus();
        SearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
                        PregRabbitDetails pregRabbitDetails =  snapshot.getValue(PregRabbitDetails.class);
                        PregRabbitDetailsArrayList.add(pregRabbitDetails);
                        pregRabbitDetailsAdapter.notifyDataSetChanged();
                        rabbit_Preg_List.smoothScrollToPosition(rabbit_Preg_List.getAdapter().getItemCount());

                    }else{

                        noPregData.setVisibility(View.VISIBLE);
                    }

                }else{
                    noPregData.setVisibility(View.VISIBLE);
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

        ArrayList<PregRabbitDetails> filteredlist = new ArrayList<>();

        for (PregRabbitDetails pregRabbitDetails : PregRabbitDetailsArrayList) {

            if (pregRabbitDetails.getName().toLowerCase().contains(text.toLowerCase())) {

                filteredlist.add(pregRabbitDetails);
            }
            if (pregRabbitDetails.getName().toLowerCase().contains(text.toLowerCase())) {

                filteredlist.add(pregRabbitDetails);
            }
        }

        if (filteredlist.isEmpty()) {
            Toast.makeText(this, "No Rabbit By that Name is available", Toast.LENGTH_LONG).show();
        } else {

            pregRabbitDetailsAdapter.setFilteredList(filteredlist);
        }

    }
}