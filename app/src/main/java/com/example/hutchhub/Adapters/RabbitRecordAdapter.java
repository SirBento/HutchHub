package com.example.hutchhub.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.hutchhub.Models.RabbitRecord;
import com.example.hutchhub.R;
import com.example.hutchhub.Records;
import com.example.hutchhub.SignUp;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;

public class RabbitRecordAdapter extends RecyclerView.Adapter<RabbitRecordAdapter.RabbitRecordAdapterViewHolder>  {

    ArrayList<RabbitRecord> arrayList;
    FirebaseAuth Auth;

    public RabbitRecordAdapter(ArrayList<RabbitRecord> arrayList){

        this.arrayList = arrayList;

    }

    public class RabbitRecordAdapterViewHolder extends RecyclerView.ViewHolder {

        public CircleImageView rabbitPic;
        public TextView rabbitName, rabbitDOB,rabbitSex,rabbit_Breed;
        public Button btnViewMoreRabbitInfo;
        public Dialog dialog;


        public RabbitRecordAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            dialog = new Dialog();// needs a context
            rabbitPic = itemView.findViewById(R.id.rabbitPic);
            rabbitName = itemView.findViewById(R.id.rabbitName);
            rabbitDOB = itemView.findViewById(R.id.rabbitDOB);
            rabbitSex = itemView.findViewById(R.id.rabbitSex);
            rabbit_Breed = itemView.findViewById(R.id.rabbit_Breed);
            btnViewMoreRabbitInfo = itemView.findViewById(R.id.btnViewMoreRabbitInfo);

        }
    }

    @NonNull
    @Override
    public RabbitRecordAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rabbit_record_display,null);

        Auth = FirebaseAuth.getInstance();
        return new RabbitRecordAdapter.RabbitRecordAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RabbitRecordAdapterViewHolder holder, int position) {
        RabbitRecord rabbitRecord = arrayList.get(position);

        Picasso.get().load(rabbitRecord.getImage()).placeholder(R.drawable.rpic5).into(holder.rabbitPic);
        holder.rabbitName.setText("Name: "+ rabbitRecord.getName());
        holder.rabbit_Breed.setText("Breed: "+ rabbitRecord.getBreed());
        holder.rabbitDOB.setText("DOB: "+ rabbitRecord.getDOB());
        holder.rabbitSex.setText("Sex:"+ rabbitRecord.getSex());

        holder.btnViewMoreRabbitInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.setContentView(R.layout.custom_dialog);
                TextView txtclose = dialog.findViewById(R.id.txtclose);
                Button btn_moreInfoDone = dialog.findViewById(R.id.btn_moreInfoDone);

                //Dialog should close when the text view is closed
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                btn_moreInfoDone .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}
