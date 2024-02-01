package com.example.hutchhub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hutchhub.Adapters.RabbitForSaleAdapter;
import com.example.hutchhub.Adapters.RabbitRecordAdapter;
import com.example.hutchhub.Models.RabbitForSale;
import com.example.hutchhub.Models.RabbitRecord;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class Records extends AppCompatActivity {
    FloatingActionButton fab;
    RecyclerView rabbit_Record_RC_List;
    FirebaseAuth auth;
    DatabaseReference rabbitRecord;
    ArrayList<RabbitRecord> rabbitRecordArrayList;
    RabbitRecordAdapter RabbitRecordAdapter;
    String currentUserUID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);


        fab = findViewById(R.id.fab);
        rabbit_Record_RC_List = findViewById(R.id.rabbit_Record_List);
        rabbit_Record_RC_List.setItemAnimator(new SlideInUpAnimator());
        auth = FirebaseAuth.getInstance();
        currentUserUID =auth.getCurrentUser().getUid();
        rabbitRecord = FirebaseDatabase.getInstance().getReference().child("RabbitRecords").child(currentUserUID);
        rabbitRecordArrayList = new ArrayList<>();
        RabbitRecordAdapter = new RabbitRecordAdapter(rabbitRecordArrayList,Records.this);
        rabbit_Record_RC_List.setHasFixedSize(true);
        rabbit_Record_RC_List.setLayoutManager(new LinearLayoutManager(this));
        rabbit_Record_RC_List.setAdapter(RabbitRecordAdapter);



        rabbit_Record_RC_List.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if(dy>0){
                    fab.hide();

                }else{

                    fab.show();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        fab.setOnClickListener(view -> startActivity(new Intent(Records.this, AddRabbitRecord.class)));

        rabbitRecord.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                if(snapshot.exists()) {

                    RabbitRecord rabbitRecord1 =snapshot.getValue(RabbitRecord.class);
                    rabbitRecord1.setKey(snapshot.getKey());
                    rabbitRecordArrayList.add(rabbitRecord1);
                    RabbitRecordAdapter.notifyDataSetChanged();
                    rabbit_Record_RC_List.smoothScrollToPosition(rabbit_Record_RC_List.getAdapter().getItemCount());

                }
                else{
                    Toast.makeText(Records.this, "No Record Yet", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                if(snapshot.exists()) {
                    RabbitRecord rabbitRecord1 =snapshot.getValue(RabbitRecord.class);
                    rabbitRecordArrayList.add(rabbitRecord1);
                    RabbitRecordAdapter.notifyDataSetChanged();
                    rabbit_Record_RC_List .smoothScrollToPosition(rabbit_Record_RC_List.getAdapter().getItemCount());
                }
                else{
                    Toast.makeText(Records.this, "No Record Yet", Toast.LENGTH_LONG).show();

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
}