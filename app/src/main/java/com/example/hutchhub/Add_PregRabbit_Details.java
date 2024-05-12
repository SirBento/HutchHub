package com.example.hutchhub;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hutchhub.R;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Calendar;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class Add_PregRabbit_Details extends AppCompatActivity {

    CircleImageView rabbit_Preg_Record_image;
    EditText rabbit_Preg_Record_name, rabbit_Preg_Record_DOB,rabbit_Preg_Record_CrossDate;
    Button btn_rabbit_Preg_Record_Save;
    private int year, month, day;
    final Calendar calendar = Calendar.getInstance();
    private FirebaseAuth mAuth;
    private String currentUserID;
    private StorageReference RabbitPicRef;
    private DatabaseReference RootRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("PregRabbitRecords");


    String downloadUrl;

    ActivityResultLauncher<Intent> imagePickLauncher;

    LoadingDialog loadingDialog = new LoadingDialog(Add_PregRabbit_Details.this);
    Uri resultUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_preg_rabbit_details);

        InitializeValues();

        rabbit_Preg_Record_CrossDate.setOnClickListener(view -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    rabbit_Preg_Record_CrossDate.setText(i2+"/"+(i1+1)+"/"+i);
                }
            },year,month,day);

            datePickerDialog.show();
        });

        rabbit_Preg_Record_DOB.setOnClickListener(view -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    rabbit_Preg_Record_DOB.setText(i2+"/"+(i1+1)+"/"+i);
                }
            },year,month,day);

            datePickerDialog.show();
        });

        rabbit_Preg_Record_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ImagePicker.with(Add_PregRabbit_Details.this).cropSquare().compress(512).maxResultSize(512,512)
                        .createIntent(new Function1<Intent, Unit>() {
                            @Override
                            public Unit invoke(Intent intent) {
                                imagePickLauncher.launch(intent);
                                return null;
                            }
                        });
            }
        });

        btn_rabbit_Preg_Record_Save.setOnClickListener(view -> {

            if(!ValidateEditTextValues(rabbit_Preg_Record_name)|
                    !ValidateEditTextValues(rabbit_Preg_Record_DOB)
                    |!ValidateEditTextValues(rabbit_Preg_Record_CrossDate)){
                loadingDialog.dismissDialog();
                return;

            }

            SaveImage();
        });
    }



    private void SaveImage(){
        loadingDialog.startLoadingDialog();
        StorageReference filePath = RabbitPicRef.child("AddPregRabbitPics").child(currentUserID).child(rabbit_Preg_Record_name.getText().toString());

        if(resultUri!=null){
            filePath.putFile(resultUri).addOnSuccessListener(taskSnapshot -> {
                final Task<Uri> firebaseUri = taskSnapshot.getStorage().getDownloadUrl();

                firebaseUri.addOnSuccessListener(uri -> {

                    downloadUrl = uri.toString();
                    SaveRabbitRecords();

                });
            });
        }else{
            Toast.makeText(Add_PregRabbit_Details.this, "Error: Please add the rabbit picture", Toast.LENGTH_LONG).show();
            loadingDialog.dismissDialog();
        }

    }

    private void InitializeValues(){

        rabbit_Preg_Record_image = findViewById(R.id.rabbit_Preg_Record_image);
        rabbit_Preg_Record_name = findViewById(R.id.rabbit_Preg_Record_name);
        rabbit_Preg_Record_DOB = findViewById(R.id.rabbit_Preg_Record_DOB);
        rabbit_Preg_Record_CrossDate = findViewById(R.id.rabbit_Preg_Record_CrossDate);
        btn_rabbit_Preg_Record_Save = findViewById(R.id.btn_rabbit_Preg_Record_Save);

        mAuth = FirebaseAuth.getInstance();
        RabbitPicRef = FirebaseStorage.getInstance().getReference().child("PreRabbitRecord");
        currentUserID = mAuth.getCurrentUser().getUid();


        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

    }

    private void SaveRabbitRecords(){

        HashMap<String, String> rabbitRecord = new HashMap<>();
        rabbitRecord.put("Name",  rabbit_Preg_Record_name.getText().toString().trim());
        rabbitRecord.put("CrossDate", rabbit_Preg_Record_CrossDate.getText().toString().trim());
        rabbitRecord.put("DOB", rabbit_Preg_Record_DOB.getText().toString().trim());
        rabbitRecord.put("Image",downloadUrl);

        databaseRef.child(currentUserID).push().setValue(rabbitRecord).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Add_PregRabbit_Details.this, "Rabbit Record Saved Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

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


