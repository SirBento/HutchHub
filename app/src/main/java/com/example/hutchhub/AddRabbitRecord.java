package com.example.hutchhub;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddRabbitRecord extends AppCompatActivity {

    private Spinner rabbit_Record_Sex, rabbit_Record_Breeds, rabbit_Record_Purpose,rabbit_Record_Origin;

    private EditText rabbit_Record_name,rabbit_Record_FatherName,
                     rabbit_Record_MotherName,rabbit_Record_Color,
                     rabbit_Record_DOB,rabbit_Record_Weaned, rabbit_Record_Notes;
    private Button btn_rabbit_Record_Save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rabbit_record);

       //Spinners
        rabbit_Record_Sex = findViewById(R.id.rabbit_Record_Sex);
        rabbit_Record_Breeds = findViewById(R.id.rabbit_Record_Breeds);
        rabbit_Record_Purpose = findViewById(R.id.rabbit_Record_Purpose);
        rabbit_Record_Origin = findViewById(R.id.rabbit_Record_Origin);

        //Edit Texts
        rabbit_Record_name = findViewById(R.id.rabbit_Record_name);
        rabbit_Record_FatherName  = findViewById(R.id.rabbit_Record_FatherName );
        rabbit_Record_MotherName  = findViewById(R.id.rabbit_Record_MotherName);
        rabbit_Record_Color = findViewById(R.id.rabbit_Record_Color);
        rabbit_Record_DOB = findViewById(R.id.rabbit_Record_DOB);
        rabbit_Record_Weaned = findViewById(R.id.rabbit_Record_Weaned);
        rabbit_Record_Notes   = findViewById(R.id. rabbit_Record_Notes);

        //Button
        btn_rabbit_Record_Save = findViewById(R.id.btn_rabbit_Record_Save);



        ArrayAdapter<CharSequence> SexAdapter =
                ArrayAdapter.createFromResource(this,R.array.rabbitSex,
                        androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        rabbit_Record_Sex.setAdapter(SexAdapter);

        ArrayAdapter<CharSequence> BreedAdapter =
                ArrayAdapter.createFromResource(this,R.array.rabbitBreeds,
                        androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        rabbit_Record_Breeds.setAdapter(BreedAdapter);

        ArrayAdapter<CharSequence> PurposeAdapter =
                ArrayAdapter.createFromResource(this,R.array.rabbitPurpose,
                        androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        rabbit_Record_Purpose.setAdapter(PurposeAdapter);

        ArrayAdapter<CharSequence> OriginAdapter =
                ArrayAdapter.createFromResource(this,R.array.rabbitSource,
                        androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        rabbit_Record_Origin.setAdapter(OriginAdapter);

    }



}