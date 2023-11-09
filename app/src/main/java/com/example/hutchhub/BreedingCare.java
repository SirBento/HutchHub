package com.example.hutchhub;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class BreedingCare extends AppCompatActivity {

    EditText rabbit_Breed_Doe, rabbit_Breed_Doe_Age,
            rabbit_Breed_Doe_Breed,rabbit_Breed_Buck,
            rabbit_Breed_Buck_Age, rabbit_Breed_Buck_Breed,
            rabbit_Breed_Falls,rabbit_Breed_Date;

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

            SaveDetails();

        });

// Todo : ADD RECOMMENDED FOOD  kgs FOR PREG RABBITS amoung may others
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
        BreedingCare.put("Reco_food",);

        String inputDate = rabbit_Breed_Date.getText().toString();

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

//Todo: save all the data, use the current user id

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

}

