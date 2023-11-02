package com.example.hutchhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.checkerframework.common.returnsreceiver.qual.This;

import java.util.Calendar;
import java.util.HashMap;

public class GrowthWeight extends AppCompatActivity {

    EditText GnW_RabbitName,  GnW_RabbitDOB,
            GnW_FirstWeight, GnW_FirstHeight, GnW_FirstWeighingDate,
            GnW_SecondWeight,GnW_SecondHeight,GnW_SecondWeighingDate,
            GnW_ThirdWeight,GnW_ThirdHeight,GnW_ThirdWeighingDate;

    Button GnW_Save;

    private int year, month, day;

    final Calendar calendar = Calendar.getInstance();

    DatabaseReference SavingInInfoDB  = FirebaseDatabase
                .getInstance().getReference("GrowthWeight")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_growth_weight);


        InitializeViews();


        GnW_Save.setOnClickListener(view -> {

            HashMap<String,String> growthNweightMap = new HashMap<>();
            growthNweightMap.put("name",GnW_RabbitName.getText().toString());
            growthNweightMap.put("DOB",GnW_RabbitDOB.getText().toString());

            growthNweightMap.put("FirstWeight",GnW_FirstWeight.getText().toString());
            growthNweightMap.put("FirstHeight",GnW_FirstHeight.getText().toString());
            growthNweightMap.put("FirstDate",GnW_FirstWeighingDate.getText().toString());

            growthNweightMap.put("SecondWeight",GnW_SecondWeight.getText().toString());
            growthNweightMap.put("SecondHeight",GnW_SecondHeight.getText().toString());
            growthNweightMap.put("SecondDate",GnW_SecondWeighingDate.getText().toString());

            growthNweightMap.put("ThirdWeight",GnW_ThirdWeight.getText().toString());
            growthNweightMap.put("ThirdHeight",GnW_ThirdHeight.getText().toString());
            growthNweightMap.put("ThirdDate",GnW_ThirdWeighingDate.getText().toString());


            GnW_FirstWeighingDate.setOnClickListener(view1 -> {

                DatePickerDialog datePickerDialog = new DatePickerDialog(GrowthWeight.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        GnW_FirstWeighingDate.setText(i2+"/"+(i1+1)+"/"+i);
                    }
                },year,month,day);

                datePickerDialog.show();
            });

            GnW_SecondWeighingDate.setOnClickListener(view1 -> {

                DatePickerDialog datePickerDialog = new DatePickerDialog(GrowthWeight.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        GnW_SecondWeighingDate.setText(i2+"/"+(i1+1)+"/"+i);
                    }
                },year,month,day);

                datePickerDialog.show();
            });

            GnW_ThirdWeighingDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    DatePickerDialog datePickerDialog = new DatePickerDialog(GrowthWeight.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                            GnW_ThirdWeighingDate.setText(i2+"/"+(i1+1)+"/"+i);
                        }
                    },year,month,day);

                    datePickerDialog.show();
                }
            });

            SavingInInfoDB.setValue(growthNweightMap).addOnCompleteListener(task -> {

                if(task.isSuccessful()) {

                    Toast.makeText(this, "Information Saved Successfully", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(this,GrowthWeightList.class));
                    finish();

                }else{
                    Toast.makeText(this, "ERROR: Please check your internet connection", Toast.LENGTH_SHORT).show();
                }
            });







        });

    }


    private void InitializeViews(){

        GnW_RabbitName = findViewById(R.id.GnW_RabbitName);
        GnW_RabbitDOB = findViewById(R.id.GnW_RabbitDOB);
        GnW_FirstWeight = findViewById(R.id.GnW_FirstWeight);
        GnW_FirstHeight = findViewById(R.id.GnW_FirstHeight);
        GnW_FirstWeighingDate = findViewById(R.id.GnW_FirstWeighingDate);
        GnW_SecondWeight = findViewById(R.id.GnW_SecondWeight);
        GnW_SecondHeight = findViewById(R.id.GnW_SecondHeight);
        GnW_SecondWeighingDate = findViewById(R.id.GnW_SecondWeighingDate);
        GnW_ThirdWeight = findViewById(R.id.GnW_ThirdWeight);
        GnW_ThirdHeight = findViewById(R.id.GnW_ThirdHeight);
        GnW_ThirdWeighingDate = findViewById(R.id.GnW_ThirdWeighingDate);
        GnW_Save = findViewById(R.id.GnW_Save);


        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }


    private boolean ValidateEditTextValues(EditText editText){

        String Value = editText.getText().toString().trim();


        if (Value.isEmpty()) {
            editText.setError("This field is required");
            editText.requestFocus();
            return false;
        } else {
            editText.setError(null);
            return true;
        }


    }
}