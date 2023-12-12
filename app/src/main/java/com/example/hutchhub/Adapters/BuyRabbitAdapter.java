package com.example.hutchhub.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.hutchhub.DetailForRabbit;
import com.example.hutchhub.Models.RabbitForSale;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class BuyRabbitAdapter extends RecyclerView.Adapter<BuyRabbitAdapter.BuyRabbitAdapterViewHolder> {

    ArrayList<RabbitForSale> arrayList;
    FirebaseAuth Auth;


    public BuyRabbitAdapter(ArrayList<RabbitForSale> arrayList){

        this.arrayList = arrayList;

    }

    public class BuyRabbitAdapterViewHolder extends RecyclerView.ViewHolder {
        public TextView rabbitBreedName, rabbitPrice,rabbitQuantity;
        public Button btnOrder,btnDelete;


        public BuyRabbitAdapterViewHolder(@NonNull View itemView) {
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
    public BuyRabbitAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rabbit_sell_layout,null);

        Auth = FirebaseAuth.getInstance();
        return new BuyRabbitAdapter.BuyRabbitAdapterViewHolder(view);
    }

    public void setFilteredList(ArrayList<RabbitForSale> filteredList){

        this.arrayList = filteredList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull BuyRabbitAdapterViewHolder holder, int position) {


        String sellerID = Auth.getCurrentUser().getUid();
        RabbitForSale rabbitForSale = arrayList.get(position);
        String fromUserID = rabbitForSale.getSellerId();

        if(!sellerID.equals(fromUserID)){

            holder.btnDelete.setVisibility(View.INVISIBLE);
            holder.rabbitBreedName.setText("Breed: "+ rabbitForSale.getBreed());
            holder.rabbitPrice.setText("Price(Each): $"+rabbitForSale.getPrice());
            holder.rabbitQuantity.setText("Quantity: "+rabbitForSale.getQuantity());
            holder.btnOrder.setVisibility(View.VISIBLE);

            holder.btnOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // open details for rabbit add extra details in intent
                    Intent intent = new Intent(holder.itemView.getContext(), DetailForRabbit.class);
                    intent.putExtra("Key",arrayList.get(position).getKey());
                    intent.putExtra("SellerID",arrayList.get(position).getSellerId());
                    intent.putExtra("PhoneNum", arrayList.get(position).getPhone());
                    intent.putExtra("Address", arrayList.get(position).getAddress());
                    intent.putExtra("Breed", arrayList.get(position).getBreed());
                    intent.putExtra("Description", arrayList.get(position).getDescription());
                    intent.putExtra("Price", arrayList.get(position).getPrice());
                    intent.putExtra("Quantity", arrayList.get(position).getQuantity());
                    holder.itemView.getContext().startActivity(intent);

                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}
