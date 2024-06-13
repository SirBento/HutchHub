package com.example.hutchhub.Adapters;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.hutchhub.Models.PregRabbitDetails;
import java.util.ArrayList;

public class PregRabbitDetailsAdapter extends RecyclerView.Adapter<PregRabbitDetailsAdapter.PregRabbitDetailsAdapterViewHolder> {

// TODO: FIX this here and add the custom view for the recycler view
    ArrayList<PregRabbitDetails> arrayList;

    public PregRabbitDetailsAdapter(ArrayList<PregRabbitDetails> arrayList){

        this.arrayList = arrayList;

    }

    public class PregRabbitDetailsAdapterViewHolder extends RecyclerView.ViewHolder {

       /* public TextView CustomBreedCareDoeName, CustomBreedCareDoeAge,
                CustomBreedCareDoeBreed,CustomBreedCareBuckName,
                CustomBreedCareBuckAge,CustomBreedCareBuckBreed,
                CustomBreedCareFalls,CustomBreedCrossingDate,
                CustomBreedPalpateDate,CustomBreedBirthDate,
                CustomBreedRecommendedFood,CustomBreedFoodQuantity;
                */



        public PregRabbitDetailsAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

           /* CustomBreedCareDoeName = itemView.findViewById(R.id.CustomBreedCareDoeName);
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
           */
        }
    }

    @NonNull
    @Override
    public PregRabbitDetailsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PregRabbitDetailsAdapterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setFilteredList(ArrayList<PregRabbitDetails> filteredList){

        this.arrayList = filteredList;
        notifyDataSetChanged();
    }

}
