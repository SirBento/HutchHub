package com.example.hutchhub.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.hutchhub.Models.PregRabbitDetails;
import com.example.hutchhub.R;
import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;

public class PregRabbitDetailsAdapter extends RecyclerView.Adapter<PregRabbitDetailsAdapter.PregRabbitDetailsAdapterViewHolder> {

    ArrayList<PregRabbitDetails> arrayList;

    public PregRabbitDetailsAdapter(ArrayList<PregRabbitDetails> arrayList){

        this.arrayList = arrayList;

    }

    public class PregRabbitDetailsAdapterViewHolder extends RecyclerView.ViewHolder {

        public CircleImageView  pregRabbitPic;
        public TextView pregRabbitName, pregRabbitDOB, pregRabbitCrossDate,
        pregRabbitPalpatingDate,pregRabbitBreedingBoxDate,pregRabbitKindlingDate;

        public Button btnUpdatePregRabbitInfo, btnDeletePregRabbitInfo;




        public PregRabbitDetailsAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            pregRabbitPic= itemView.findViewById(R.id.pregRabbitPic);
            pregRabbitName = itemView.findViewById(R.id.pregRabbitName);
            pregRabbitDOB  = itemView.findViewById(R.id.pregRabbitDOB);
            pregRabbitCrossDate = itemView.findViewById(R.id. pregRabbitCrossDate);
            pregRabbitPalpatingDate  = itemView.findViewById(R.id.pregRabbitPalpatingDate);
            pregRabbitBreedingBoxDate  = itemView.findViewById(R.id.pregRabbitBreedingBoxDate);
            pregRabbitKindlingDate = itemView.findViewById(R.id.pregRabbitKindlingDate);
            btnUpdatePregRabbitInfo = itemView.findViewById(R.id.btnUpdatePregRabbitInfo);
            btnDeletePregRabbitInfo = itemView.findViewById(R.id.btnDeletePregRabbitInfo);

        }
    }

    @NonNull
    @Override
    public PregRabbitDetailsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_preg_rabbit_details_design,null);

        return new PregRabbitDetailsAdapter.PregRabbitDetailsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PregRabbitDetailsAdapterViewHolder holder, int position) {

        PregRabbitDetails pregRabbitDetails = arrayList.get(position);

        holder.pregRabbitName.setText("Name: " + pregRabbitDetails.getName());
        holder.pregRabbitDOB.setText("DOB: " + pregRabbitDetails.getDob());
        holder.pregRabbitCrossDate.setText("CrossDate: " + pregRabbitDetails.getCrossDate());
        holder.pregRabbitPalpatingDate.setText("Palpating: " + pregRabbitDetails.getPalpating());
        holder.pregRabbitBreedingBoxDate.setText("Breeding Box: " + pregRabbitDetails.getBreadingBox());
        holder.pregRabbitKindlingDate.setText("Kindling: " + pregRabbitDetails.getKindling());

        holder.btnUpdatePregRabbitInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*

                TODO: Find a way to update the details
                 -add a way to update  if the rabbit is preg or not after palpating
                 -add a way to update if the rabbit gave birth or not if yes it should
                   automatically add add that information to the records after getting
                    the information on how many kits where born etec
                 */
            }
        });


        holder.btnDeletePregRabbitInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //  TODO: Find a way to update selected item
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void setFilteredList(ArrayList<PregRabbitDetails> filteredList){

        this.arrayList = filteredList;
        notifyDataSetChanged();
    }

}
