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

import com.example.hutchhub.Adapters.BuyRabbitAdapter;
import com.example.hutchhub.Adapters.RabbitForSaleAdapter;
import com.example.hutchhub.Models.RabbitForSale;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class BuyRabbit extends AppCompatActivity {

    TextView noBuySellText;
    FirebaseAuth auth;
    DatabaseReference sellsDB;
    ArrayList<RabbitForSale> rabbitForSaleList;
    BuyRabbitAdapter buyRabbitAdapter;
    RecyclerView myBuyRecyclerList;
    SearchView buySearchView;
    LoadingDialog loadingDialog = new LoadingDialog(BuyRabbit.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_rabbit);


        noBuySellText = findViewById(R.id.noBuySellText);
        buySearchView = findViewById(R.id.buySearchView);
        myBuyRecyclerList = findViewById(R.id.myBuyRecyclerList);

        auth = FirebaseAuth.getInstance();
        sellsDB = FirebaseDatabase.getInstance().getReference().child("Sells");
        rabbitForSaleList = new ArrayList<>();
        buyRabbitAdapter = new BuyRabbitAdapter(rabbitForSaleList);
        myBuyRecyclerList.setHasFixedSize(true);
        myBuyRecyclerList.setLayoutManager(new LinearLayoutManager(this));
        myBuyRecyclerList.setAdapter(buyRabbitAdapter);


        buySearchView.clearFocus();
        buySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
                if(snapshot.exists() && snapshot.hasChildren()) {
                    RabbitForSale rabbitForSale = snapshot.getValue(RabbitForSale.class);
                    String sellerID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    String fromUserID = rabbitForSale.getSellerId();

                    if(!sellerID.equals(fromUserID)){
                        rabbitForSaleList.add(rabbitForSale);
                        buyRabbitAdapter.notifyDataSetChanged();
                        myBuyRecyclerList.smoothScrollToPosition(myBuyRecyclerList.getAdapter().getItemCount());

                    }else{
                            noBuySellText.setVisibility(View.VISIBLE);
                        }

                }
                else{
                    noBuySellText.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                if(snapshot.exists()&& snapshot.hasChildren()) {
                    RabbitForSale rabbitForSale =snapshot.getValue(RabbitForSale.class);
                    rabbitForSaleList.add(rabbitForSale);
                    buyRabbitAdapter.notifyDataSetChanged();
                    myBuyRecyclerList.smoothScrollToPosition(myBuyRecyclerList.getAdapter().getItemCount());
                }
                else{
                    noBuySellText.setVisibility(View.VISIBLE);
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


        loadingDialog.startLoadingDialog();
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run () {
                loadingDialog.dismissDialog();
            }
            },7000);
    }



    private void filterList(String text) {

        ArrayList<RabbitForSale> filteredlist = new ArrayList<>();

        for (RabbitForSale rabbitForSale : rabbitForSaleList) {

            if (rabbitForSale.getBreed().toLowerCase().contains(text.toLowerCase())) {

                filteredlist.add(rabbitForSale);
            }
        }

        if (filteredlist.isEmpty()) {
            Toast.makeText(this, "No Rabbit Breed By that Name is available", Toast.LENGTH_SHORT).show();
        } else {

            buyRabbitAdapter.setFilteredList(filteredlist);
        }
    }
}


