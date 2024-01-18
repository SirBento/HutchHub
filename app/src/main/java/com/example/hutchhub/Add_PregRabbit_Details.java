package com.example.hutchhub;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.hutchhub.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class Add_PregRabbit_Details extends AppCompatActivity {

    CircleImageView rabbit_Preg_Record_image;
    EditText rabbit_Preg_Record_name, rabbit_Preg_Record_DOB,rabbit_Preg_Record_CrossDate;
    Button btn_rabbit_Preg_Record_Save;
    private int year, month, day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_preg_rabbit_details);

        rabbit_Preg_Record_image = findViewById(R.id.rabbit_Preg_Record_image);
        rabbit_Preg_Record_name = findViewById(R.id.rabbit_Preg_Record_name);
        rabbit_Preg_Record_DOB = findViewById(R.id.rabbit_Preg_Record_DOB);
        rabbit_Preg_Record_CrossDate = findViewById(R.id.rabbit_Preg_Record_CrossDate);
        btn_rabbit_Preg_Record_Save = findViewById(R.id.btn_rabbit_Preg_Record_Save);




        rabbit_Preg_Record_CrossDate.setOnClickListener(view -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    rabbit_Preg_Record_CrossDate.setText(i2+"/"+(i1+1)+"/"+i);
                }
            },year,month,day);

            datePickerDialog.show();
        });
    }
}