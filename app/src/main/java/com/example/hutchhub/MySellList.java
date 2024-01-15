package com.example.hutchhub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.hutchhub.Adapters.RabbitForSaleAdapter;
import com.example.hutchhub.Models.RabbitForSale;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;

public class MySellList extends AppCompatActivity {
   TextView noSellText;
    FirebaseAuth auth;
    DatabaseReference sellsDB;
    ArrayList<RabbitForSale> rabbitForSaleList;
    RabbitForSaleAdapter rabbitForSaleAdapter;
    RecyclerView mySellRecyclerList;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sell_list);

        noSellText = findViewById(R.id.noSellText);
        searchView = findViewById(R.id.SearchView);
        mySellRecyclerList = findViewById(R.id.mySellRecyclerList);

        auth = FirebaseAuth.getInstance();
        sellsDB = FirebaseDatabase.getInstance().getReference().child("Sells");
        rabbitForSaleList = new ArrayList<>();
        rabbitForSaleAdapter = new RabbitForSaleAdapter(rabbitForSaleList);
        mySellRecyclerList.setHasFixedSize(true);
        mySellRecyclerList.setLayoutManager(new LinearLayoutManager(this));
        mySellRecyclerList.setAdapter(rabbitForSaleAdapter);


        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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


        sellsDB.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                /*** probably causing bugs  ***/
  /***
                if(snapshot.exists()) {
                    RabbitForSale rabbitForSale =snapshot.getValue(RabbitForSale.class);
                    rabbitForSaleList.add(rabbitForSale);

                    if(!auth.getCurrentUser().getUid().equals(rabbitForSale.getSellerId())){
                        noSellText.setVisibility(View.VISIBLE);

                    }else{

                        rabbitForSaleAdapter.notifyDataSetChanged();
                        mySellRecyclerList.smoothScrollToPosition(mySellRecyclerList.getAdapter().getItemCount());

                    }
                }
                else{
                    noSellText.setVisibility(View.VISIBLE);

                }

   ***/

                if(snapshot.exists()) {
                    RabbitForSale rabbitForSale =snapshot.getValue(RabbitForSale.class);
                    rabbitForSale.setKey(snapshot.getKey());
                    rabbitForSaleList.add(rabbitForSale);
                    rabbitForSaleAdapter.notifyDataSetChanged();
                    mySellRecyclerList.smoothScrollToPosition(mySellRecyclerList.getAdapter().getItemCount());
                    if(!auth.getCurrentUser().getUid().equals(rabbitForSale.getSellerId())){
                        noSellText.setVisibility(View.VISIBLE);
                    }
                }
                else{
                    noSellText.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                if(snapshot.exists()) {
                    RabbitForSale rabbitForSale =snapshot.getValue(RabbitForSale.class);
                    rabbitForSale.setKey(snapshot.getKey());
                    rabbitForSaleList.add(rabbitForSale);
                    rabbitForSaleAdapter.notifyDataSetChanged();
                    mySellRecyclerList.smoothScrollToPosition(mySellRecyclerList.getAdapter().getItemCount());
                    if(!auth.getCurrentUser().getUid().equals(rabbitForSale.getSellerId())){
                        noSellText.setVisibility(View.VISIBLE);
                    }
                }
                else{
                    noSellText.setVisibility(View.VISIBLE);
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


        LoadingDialog loadingDialog = new LoadingDialog(MySellList.this);
        loadingDialog.startLoadingDialog();
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                loadingDialog.dismissDialog();
            }
        }, 7000); // dismiss the dialog after 7 seconds

    }


    private void filterList(String text) {

        ArrayList<RabbitForSale> filteredlist = new ArrayList<>();

        for(RabbitForSale rabbitForSale: rabbitForSaleList){

            if(rabbitForSale.getBreed().toLowerCase().contains(text.toLowerCase())){

                filteredlist.add(rabbitForSale);
            }
        }

        if(filteredlist.isEmpty()){
            Toast.makeText(this, "No Rabbit Breed By that Name is available", Toast.LENGTH_SHORT).show();
        }else{

            rabbitForSaleAdapter.setFilteredList(filteredlist);
        }

    }

}