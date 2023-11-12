package com.example.hutchhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hutchhub.Adapters.BreadingAndCareAdapater;
import com.example.hutchhub.Models.BreedingAndCare;
import com.example.hutchhub.Models.RabbitForSale;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class BreedingCare extends AppCompatActivity {

    EditText rabbit_Breed_Doe, rabbit_Breed_Doe_Age,
            rabbit_Breed_Doe_Breed,rabbit_Breed_Buck,
            rabbit_Breed_Buck_Age, rabbit_Breed_Buck_Breed,
            rabbit_Breed_Falls,rabbit_Breed_Date;

    DatabaseReference detailsDB = FirebaseDatabase.getInstance()
            .getReference("BreedingCare")
            .child(FirebaseAuth.getInstance().getCurrentUser().getUid());

    Button btn_Breed_Save;

    private int year, month, day;
    final Calendar calendar = Calendar.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeding_care);

        initializeValues();


        rabbit_Breed_Date.setOnClickListener(view -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(BreedingCare.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    rabbit_Breed_Date.setText(i2+"/"+(i1+1)+"/"+i);
                }
            },year,month,day);

            datePickerDialog.show();
        });

        btn_Breed_Save.setOnClickListener(view -> {

            if(!ValidateEditTextValues(rabbit_Breed_Doe)| !ValidateEditTextValues(rabbit_Breed_Doe_Age)
                    |!ValidateEditTextValues(rabbit_Breed_Doe_Breed) |!ValidateEditTextValues(rabbit_Breed_Buck)|!ValidateEditTextValues(rabbit_Breed_Buck_Age)
                    |!ValidateEditTextValues(rabbit_Breed_Buck_Breed)|!ValidateEditTextValues(rabbit_Breed_Falls)|!ValidateEditTextValues(rabbit_Breed_Date)){

                return;
            }

            SaveDetails();

        });

    }


    private void SaveDetails(){


        HashMap<String,String> BreedingCare = new HashMap<>();

        BreedingCare.put("Doe_Name", rabbit_Breed_Doe.getText().toString());
        BreedingCare.put("Doe_Age",rabbit_Breed_Doe_Age.getText().toString());
        BreedingCare.put("Doe_Breed",rabbit_Breed_Doe_Breed.getText().toString());
        BreedingCare.put("Buck_Name",rabbit_Breed_Buck.getText().toString());
        BreedingCare.put("Buck_Age",rabbit_Breed_Buck_Age.getText().toString());
        BreedingCare.put("Buck_Breed",rabbit_Breed_Buck_Breed.getText().toString());
        BreedingCare.put("Falls",rabbit_Breed_Falls.getText().toString());
        BreedingCare.put("Cross_Date",rabbit_Breed_Date.getText().toString());
        BreedingCare.put("Reco_food","Dark Leafy Greens\nAlfAlfa Hay\nRabbit Pellets");
        BreedingCare.put("Quantity","Grass hay: 85%(ideally unlimited)\nFresh leafy greens: 10%(2 handfuls a day)\nQuality Pellets: 5%(2-5 tablespoons a day)\nClean Water: unlimited");

        String inputDate = rabbit_Breed_Date.getText().toString();
//TODO: should add new alarms based on the calculated date to notify the user
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateFormat.parse(inputDate));
            calendar.add(Calendar.WEEK_OF_YEAR, 2);
            String resultDate = dateFormat.format(calendar.getTime());

            BreedingCare.put("Pal_Date",resultDate);

        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateFormat.parse(inputDate));
            calendar.add(Calendar.MONTH, 1);
            String resultDate = dateFormat.format(calendar.getTime());

            BreedingCare.put("PregDue_Date",resultDate);

        } catch (Exception e) {
            e.printStackTrace();
        }

        detailsDB.setValue(BreedingCare).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(BreedingCare.this, "Breeding Information Saved", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(com.example.hutchhub.BreedingCare.this, BreedingCareList.class));
                    finish();
                }

            }
        });


}

private void initializeValues(){

    rabbit_Breed_Doe = findViewById(R.id.rabbit_Breed_Doe);
    rabbit_Breed_Doe_Age= findViewById(R.id.rabbit_Breed_Doe_Age);
    rabbit_Breed_Doe_Breed = findViewById(R.id.rabbit_Breed_Doe_Breed);
    rabbit_Breed_Buck = findViewById(R.id.rabbit_Breed_Buck);
    rabbit_Breed_Buck_Age = findViewById(R.id.rabbit_Breed_Buck_Age);
    rabbit_Breed_Buck_Breed = findViewById(R.id.rabbit_Breed_Buck_Breed);
    rabbit_Breed_Falls = findViewById(R.id.rabbit_Breed_Falls);
    rabbit_Breed_Date = findViewById(R.id.rabbit_Breed_Date);
    btn_Breed_Save = findViewById(R.id.btn_Breed_Save);

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

