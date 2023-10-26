package com.example.hutchhub.Adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.widget.LinearLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.hutchhub.Models.RabbitRecord;
import com.example.hutchhub.R;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;

public class RabbitRecordAdapter extends RecyclerView.Adapter<RabbitRecordAdapter.RabbitRecordAdapterViewHolder>  {

    ArrayList<RabbitRecord> arrayList;
    FirebaseAuth Auth;

    Dialog dialog;
    TextView moreInfoRabbitName,moreInfoRabbitSex ,moreInfoDOB,moreInfoFather
            ,moreInfoMother,moreInfoOrigin,moreInfoColor,moreInfoWeaning,
            moreInfoBreed, moreInfoPurpose, moreInfoNotes, txtclose;

    Button btn_moreInfoDone;

    CircleImageView moreInfoRabbitPic;

    public RabbitRecordAdapter(ArrayList<RabbitRecord> arrayList){

        this.arrayList = arrayList;


    }

    public class RabbitRecordAdapterViewHolder extends RecyclerView.ViewHolder {

        public CircleImageView rabbitPic;
        public TextView rabbitName, rabbitDOB,rabbitSex,rabbit_Breed;
        public Button btnViewMoreRabbitInfo;

        public LinearLayout layout;



        public RabbitRecordAdapterViewHolder(@NonNull View itemView) {
            super(itemView);


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

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rabbit_record_display,parent,false);
        final RabbitRecordAdapterViewHolder rabbitRecordAdapterViewHolder = new RabbitRecordAdapter.RabbitRecordAdapterViewHolder(view);
        Auth = FirebaseAuth.getInstance();

        //Dialog Initialization

        dialog = new Dialog(dialog.getContext());
        dialog.setContentView(R.layout.custom_dialog);
        //closing textview and button on the custom dialog
        txtclose= dialog.findViewById(R.id.txtclose);
        btn_moreInfoDone= dialog.findViewById(R.id.btn_moreInfoDone);

        //pic
        moreInfoRabbitPic = dialog.findViewById(R.id.moreInfoRabbitPic);

        //Initialising all components on the dialog box
       moreInfoRabbitName=dialog.findViewById(R.id.moreInfoRabbitName);
       moreInfoRabbitSex =dialog.findViewById(R.id.moreInfoRabbitSex);
       moreInfoDOB =dialog.findViewById(R.id.moreInfoDOB);
       moreInfoFather=dialog.findViewById(R.id.moreInfoFather);
       moreInfoMother=dialog.findViewById(R.id.moreInfoMother);
       moreInfoOrigin=dialog.findViewById(R.id.moreInfoOrigin);
       moreInfoColor=dialog.findViewById(R.id.moreInfoColor);
       moreInfoWeaning=dialog.findViewById(R.id.moreInfoWeaning);
       moreInfoBreed=dialog.findViewById(R.id.moreInfoBreed);
       moreInfoPurpose=dialog.findViewById(R.id.moreInfoPurpose);
       moreInfoNotes=dialog.findViewById(R.id.moreInfoNotes);

        rabbitRecordAdapterViewHolder.btnViewMoreRabbitInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                moreInfoRabbitName.setText(arrayList.get(rabbitRecordAdapterViewHolder.getAbsoluteAdapterPosition()).getName());
                moreInfoRabbitSex.setText(arrayList.get(rabbitRecordAdapterViewHolder.getAbsoluteAdapterPosition()).getSex());
                moreInfoDOB.setText(arrayList.get(rabbitRecordAdapterViewHolder.getAbsoluteAdapterPosition()).getDOB());
                moreInfoFather.setText(arrayList.get(rabbitRecordAdapterViewHolder.getAbsoluteAdapterPosition()).getFname());
                moreInfoMother.setText(arrayList.get(rabbitRecordAdapterViewHolder.getAbsoluteAdapterPosition()).getMname());
                moreInfoOrigin.setText(arrayList.get(rabbitRecordAdapterViewHolder.getAbsoluteAdapterPosition()).getOrigin());
                moreInfoColor.setText(arrayList.get(rabbitRecordAdapterViewHolder.getAbsoluteAdapterPosition()).getColor());
                moreInfoWeaning.setText(arrayList.get(rabbitRecordAdapterViewHolder.getAbsoluteAdapterPosition()).getWdate());
                moreInfoBreed.setText(arrayList.get(rabbitRecordAdapterViewHolder.getAbsoluteAdapterPosition()).getBreed());
                moreInfoPurpose.setText(arrayList.get(rabbitRecordAdapterViewHolder.getAbsoluteAdapterPosition()).getPurpose());
                moreInfoNotes.setText(arrayList.get(rabbitRecordAdapterViewHolder.getAbsoluteAdapterPosition()).getNotes());

                //retrieving Image
                Picasso.get().
                        load(arrayList.get(rabbitRecordAdapterViewHolder.getAbsoluteAdapterPosition())
                                .getImage())
                        .placeholder(R.drawable.rpic5)
                        .into(moreInfoRabbitPic);


                dialog.show();
            }
        });



        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btn_moreInfoDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        return rabbitRecordAdapterViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RabbitRecordAdapterViewHolder holder, int position) {


        RabbitRecord rabbitRecord = arrayList.get(position);
        Picasso.get().load(rabbitRecord.getImage()).placeholder(R.drawable.rpic5).into(holder.rabbitPic);
        holder.rabbitName.setText("Name: "+ rabbitRecord.getName());
        holder.rabbit_Breed.setText("Breed: "+ rabbitRecord.getBreed());
        holder.rabbitDOB.setText("DOB: "+ rabbitRecord.getDOB());
        holder.rabbitSex.setText("Sex:"+ rabbitRecord.getSex());



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}
