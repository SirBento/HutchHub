package com.example.hutchhub.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hutchhub.FeedingSchedule;
import com.example.hutchhub.Models.RabbitForSale;
import com.example.hutchhub.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RabbitForSaleAdapter extends RecyclerView.Adapter<RabbitForSaleAdapter.RabbitForSaleAdapterViewHolder> {

    ArrayList<RabbitForSale> arrayList;
    FirebaseAuth Auth;
    DatabaseReference sellsDB = FirebaseDatabase.getInstance()
                                .getReference()
                                .child("Sells")
                                .child(Auth.getCurrentUser().getUid());

    public RabbitForSaleAdapter(ArrayList<RabbitForSale> arrayList){

        this.arrayList = arrayList;

    }

    public class RabbitForSaleAdapterViewHolder extends RecyclerView.ViewHolder {
        public TextView rabbitBreedName, rabbitPrice,rabbitQuantity;
        public Button btnOrder,btnDelete;


        public RabbitForSaleAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            rabbitBreedName = itemView.findViewById(R.id.rabbitBreedName);
            rabbitPrice = itemView.findViewById(R.id.rabbitPrice);
            rabbitQuantity = itemView.findViewById(R.id.rabbitQuantity);
            btnOrder = itemView.findViewById(R.id.btnOrder);
            btnDelete = itemView.findViewById(R.id.btnDelete);

        }
    }

    @NonNull
    @Override
    public RabbitForSaleAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rabbit_sell_layout,null);

        Auth = FirebaseAuth.getInstance();
        return new RabbitForSaleAdapterViewHolder(view);
    }

    public void setFilteredList(ArrayList<RabbitForSale> filteredList){

        this.arrayList = filteredList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull RabbitForSaleAdapterViewHolder holder, int position) {

        String sellerID = Auth.getCurrentUser().getUid();
        RabbitForSale rabbitForSale = arrayList.get(position);
        String fromUserID = rabbitForSale.getSellerId();

        if(sellerID.equals(fromUserID)){

            holder.btnDelete.setVisibility(View.VISIBLE);
            holder.rabbitBreedName.setText("Breed: "+ rabbitForSale.getBreed());
            holder.rabbitPrice.setText("Price(Each): $"+rabbitForSale.getPrice());
            holder.rabbitQuantity.setText("Quantity: "+rabbitForSale.getQuantity());
            holder.btnOrder.setVisibility(View.INVISIBLE);

            holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Todo: Test this new code
                    sellsDB.child(rabbitForSale.getKey()).removeValue().addOnCompleteListener(task -> {

                        if(task.isSuccessful()){

                            Toast.makeText(view.getContext(), "Record Deleted Successfully", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(view.getContext(), "Error: Please Check Your Internet Connection", Toast.LENGTH_LONG).show();
                        }

                    });
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }





}
