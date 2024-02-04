package com.example.hutchhub.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.hutchhub.Models.BreedingAndCare;
import com.example.hutchhub.Models.RabbitForSale;
import com.example.hutchhub.R;
import java.util.ArrayList;

public class BreadingAndCareAdapater extends RecyclerView.Adapter<BreadingAndCareAdapater.BreadingAndCareAdapaterViewHolder>{

    ArrayList<BreedingAndCare> arrayList;

    public BreadingAndCareAdapater(ArrayList<BreedingAndCare> arrayList){

        this.arrayList = arrayList;

    }


    public class BreadingAndCareAdapaterViewHolder extends RecyclerView.ViewHolder {
        public TextView CustomBreedCareDoeName, CustomBreedCareDoeAge,
                CustomBreedCareDoeBreed,CustomBreedCareBuckName,
                CustomBreedCareBuckAge,CustomBreedCareBuckBreed,
                CustomBreedCareFalls,CustomBreedCrossingDate,
                CustomBreedPalpateDate,CustomBreedBirthDate,
                CustomBreedRecommendedFood,CustomBreedFoodQuantity;



        public BreadingAndCareAdapaterViewHolder(@NonNull View itemView) {
            super(itemView);

            CustomBreedCareDoeName = itemView.findViewById(R.id.CustomBreedCareDoeName);
            CustomBreedCareDoeAge = itemView.findViewById(R.id.CustomBreedCareDoeAge);
            CustomBreedCareDoeBreed  = itemView.findViewById(R.id.CustomBreedCareDoeBreed);
            CustomBreedCareBuckName = itemView.findViewById(R.id.CustomBreedCareBuckName);
            CustomBreedCareBuckAge  = itemView.findViewById(R.id.CustomBreedCareBuckAge);
            CustomBreedCareBuckBreed  = itemView.findViewById(R.id.CustomBreedCareBuckBreed);
            CustomBreedCareFalls = itemView.findViewById(R.id.CustomBreedCareFalls);
            CustomBreedCrossingDate = itemView.findViewById(R.id.CustomBreedCrossingDate);
            CustomBreedPalpateDate = itemView.findViewById(R.id.CustomBreedPalpateDate);
            CustomBreedBirthDate = itemView.findViewById(R.id.CustomBreedBirthDate);
            CustomBreedRecommendedFood = itemView.findViewById(R.id.CustomBreedRecommendedFood);
            CustomBreedFoodQuantity = itemView.findViewById(R.id.CustomBreedFoodQuantity);

        }
    }

    @NonNull
    @Override
    public BreadingAndCareAdapaterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_breeding_care,null);

        return new BreadingAndCareAdapater.BreadingAndCareAdapaterViewHolder(view);
    }

    public void setFilteredList(ArrayList<BreedingAndCare> filteredList){

        this.arrayList = filteredList;
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull BreadingAndCareAdapaterViewHolder holder, int position) {

       BreedingAndCare breedingAndCare = arrayList.get(position);

        holder.CustomBreedCareDoeName.setText("Name: " + breedingAndCare.getDoe_Name());
        holder.CustomBreedCareDoeBreed.setText("Breed: " + breedingAndCare.getDoe_Breed());
        holder.CustomBreedCareDoeAge.setText("Age: " + breedingAndCare.getDoe_Age());
        holder.CustomBreedCareBuckName.setText("Name: " + breedingAndCare.getBuck_Name());
        holder.CustomBreedCareBuckAge.setText("Age: " + breedingAndCare.getBuck_Age());
        holder.CustomBreedCareBuckBreed.setText("Breed: " + breedingAndCare.getBuck_Breed());
        holder.CustomBreedCareFalls.setText("Successful Falls: " + breedingAndCare.getFalls());
        holder.CustomBreedCrossingDate.setText("Crossing Date: " +breedingAndCare.getCross_Date());
        holder.CustomBreedPalpateDate.setText("Predicted Palpating Date: " +breedingAndCare.getPal_Date());
        holder.CustomBreedBirthDate.setText("Predicted Birth Date: " +breedingAndCare.getPregDue_Date());
        holder.CustomBreedRecommendedFood.setText("Recommended Food: " +breedingAndCare.getReco_food());
        holder.CustomBreedFoodQuantity.setText("Food Quantity: " +breedingAndCare.getQuantity());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }



}
