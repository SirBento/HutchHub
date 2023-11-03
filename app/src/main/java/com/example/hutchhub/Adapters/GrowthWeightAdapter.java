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
import com.example.hutchhub.GrowthWeightList;
import com.example.hutchhub.Models.GrowthWeight;
import com.example.hutchhub.R;
import java.util.ArrayList;

public class GrowthWeightAdapter extends RecyclerView.Adapter<GrowthWeightAdapter.GrowthWeightAdapterViewHolder> {

    ArrayList<GrowthWeight> arrayList;

    public GrowthWeightAdapter(ArrayList<GrowthWeight> arrayList) {
        this.arrayList = arrayList;
    }

    public class GrowthWeightAdapterViewHolder extends RecyclerView.ViewHolder {
        public TextView GW_CustomRabbitName, GW_CustomRabbitDOB
                ,GW_CustomRabbitFirstDate,GW_CustomRabbitFirstWeight,GW_CustomRabbitFirstHeight,
                GW_CustomRabbitSecondDate,GW_CustomRabbitSecondWeight,GW_CustomRabbitSecondHeight,
                GW_CustomRabbitThirdDate,GW_CustomRabbitThirdWeight,GW_CustomRabbitThirdHeight;
        public Button btnGW_CustomEdit;


        public GrowthWeightAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            GW_CustomRabbitName = itemView.findViewById(R.id.GW_CustomRabbitName);
            GW_CustomRabbitDOB = itemView.findViewById(R.id.GW_CustomRabbitDOB);

            GW_CustomRabbitFirstDate = itemView.findViewById(R.id.GW_CustomRabbitFirstDate);
            GW_CustomRabbitFirstWeight= itemView.findViewById(R.id.GW_CustomRabbitFirstWeight);
            GW_CustomRabbitFirstHeight = itemView.findViewById(R.id.GW_CustomRabbitFirstHeight);

            GW_CustomRabbitSecondDate = itemView.findViewById(R.id.GW_CustomRabbitSecondDate);
            GW_CustomRabbitSecondWeight = itemView.findViewById(R.id.GW_CustomRabbitSecondWeight);
            GW_CustomRabbitSecondHeight = itemView.findViewById(R.id.GW_CustomRabbitSecondHeight);

            GW_CustomRabbitThirdDate = itemView.findViewById(R.id.GW_CustomRabbitThirdDate);
            GW_CustomRabbitThirdWeight= itemView.findViewById(R.id.GW_CustomRabbitThirdWeight);
            GW_CustomRabbitThirdHeight = itemView.findViewById(R.id.GW_CustomRabbitThirdHeight);

            btnGW_CustomEdit = itemView.findViewById(R.id.btnGW_CustomEdit);

        }
    }

    public void setFilteredList(ArrayList<GrowthWeight> filteredList){

        this.arrayList = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GrowthWeightAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_growth_weigh_list,null);

        return new GrowthWeightAdapter.GrowthWeightAdapterViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull GrowthWeightAdapterViewHolder holder, int position) {

        GrowthWeight growthWeight = arrayList.get(position);



        holder.GW_CustomRabbitName.setText("Name: "+ growthWeight.getName());
        holder.GW_CustomRabbitDOB.setText("DOB:"+ growthWeight.getDOB());
        holder.GW_CustomRabbitFirstDate.setText(growthWeight.getFirstDate());
        holder.GW_CustomRabbitFirstWeight.setText(growthWeight.getFirstWeight());
        holder.GW_CustomRabbitFirstHeight.setText(growthWeight.getFirstHeight());
        holder.GW_CustomRabbitSecondDate.setText(growthWeight.getSecondDate());
        holder.GW_CustomRabbitSecondWeight.setText(growthWeight.getSecondWeight());
        holder.GW_CustomRabbitSecondHeight.setText(growthWeight.getSecondHeight());
        holder.GW_CustomRabbitThirdDate.setText(growthWeight.getThirdDate());
        holder.GW_CustomRabbitThirdWeight.setText(growthWeight.getThirdWeight());
        holder.GW_CustomRabbitThirdHeight.setText(growthWeight.getThirdHeight());

      holder.btnGW_CustomEdit.setOnClickListener(view -> {

          Intent intent = new Intent(holder.itemView.getContext(), GrowthWeight.class);


          intent.putExtra("Name", arrayList.get(position).getName());
          intent.putExtra("DOB", arrayList.get(position).getDOB());
          intent.putExtra("Fdate", arrayList.get(position).getFirstDate());
          intent.putExtra("Fweight", arrayList.get(position).getFirstWeight());
          intent.putExtra("Fheight", arrayList.get(position).getFirstHeight());

          intent.putExtra("Sdate", arrayList.get(position).getSecondDate());
          intent.putExtra("Sweight", arrayList.get(position).getSecondWeight());
          intent.putExtra("Sheight", arrayList.get(position).getSecondHeight());

          intent.putExtra("Tdate", arrayList.get(position).getThirdDate());
          intent.putExtra("Tweight", arrayList.get(position).getThirdWeight());
          intent.putExtra("Theight", arrayList.get(position).getThirdHeight());
          holder.itemView.getContext().startActivity(intent);
          //TODO:open data cupturing activity but put extra data to diplay for the user to edit

      });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }



}
