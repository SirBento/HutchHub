package com.example.hutchhub;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.dhaval2404.imagepicker.ImagePicker;
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



public class AddRabbitRecord extends AppCompatActivity {

    private Spinner rabbit_Record_Sex, rabbit_Record_Breeds, rabbit_Record_Purpose,rabbit_Record_Origin;

    private EditText rabbit_Record_name,rabbit_Record_FatherName,
                     rabbit_Record_MotherName,rabbit_Record_Color,
                     rabbit_Record_DOB,rabbit_Record_Weaned, rabbit_Record_Notes;
    private Button btn_rabbit_Record_Save;

    private CircleImageView rabbit_Record_image;
    final Calendar calendar = Calendar.getInstance();
    private FirebaseAuth mAuth;
    private String currentUserID;
    private StorageReference RabbitPicRef;
    private DatabaseReference RootRef;


    private int year, month, day;

    String downloadUrl;

    ActivityResultLauncher<Intent> imagePickLauncher;

    LoadingDialog loadingDialog = new LoadingDialog(AddRabbitRecord.this);
    Uri resultUri;

    private DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rabbit_record);



        //Assigning all values
        InitializeValues();


        imagePickLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        if(data!=null && data.getData()!=null){
                            resultUri= data.getData();
                            Glide.with(AddRabbitRecord.this).load(resultUri).apply(RequestOptions.circleCropTransform()).into(rabbit_Record_image);
                        }
                    }
                }
        );



        rabbit_Record_DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddRabbitRecord.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        rabbit_Record_DOB.setText(i2+"/"+(i1+1)+"/"+i);
                        //rabbit_Record_DOB.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));
                    }
                },year,month,day);

                datePickerDialog.show();

            }
        });



        rabbit_Record_Weaned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddRabbitRecord.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        rabbit_Record_Weaned.setText(i2+"/"+(i1+1)+"/"+i);
                        //rabbit_Record_Weaned.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));
                    }
                },year,month,day);

                datePickerDialog.show();

            }
        });



        rabbit_Record_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ImagePicker.with(AddRabbitRecord.this).cropSquare().compress(512).maxResultSize(512,512)
                        .createIntent(new Function1<Intent, Unit>() {
                            @Override
                            public Unit invoke(Intent intent) {
                                imagePickLauncher.launch(intent);
                                return null;
                            }
                        });
            }
        });



        btn_rabbit_Record_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!ValidateEditTextValues(rabbit_Record_name)| !ValidateEditTextValues(rabbit_Record_FatherName)
                   |!ValidateEditTextValues(rabbit_Record_MotherName) |!ValidateEditTextValues(rabbit_Record_Color)|
                   !ValidateEditTextValues(rabbit_Record_DOB)|!ValidateEditTextValues(rabbit_Record_Weaned)
                   |!ValidateEditTextValues(rabbit_Record_Notes)){

                    return;
                }

                SaveImage();
                SaveRabbitRecords();
            }
        });

    }



    private void SaveImage(){

        loadingDialog.startLoadingDialog();

        StorageReference filePath = RabbitPicRef.child("Add_Rabbit_Pics").child(currentUserID).child(rabbit_Record_name.getText().toString());

        if(resultUri!=null){
            filePath.putFile(resultUri).addOnSuccessListener(taskSnapshot -> {
                final Task<Uri> firebaseUri = taskSnapshot.getStorage().getDownloadUrl();

                firebaseUri.addOnSuccessListener(uri -> {

                    Toast.makeText(AddRabbitRecord.this, "Rabbit Picture Uploaded Successfully", Toast.LENGTH_SHORT).show();
                    downloadUrl = uri.toString();
                    // complete the rest of your code

                    RootRef.child("RabbitRecords").child(currentUserID).child("image").setValue(downloadUrl)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()){

                                    loadingDialog.dismissDialog();

                                }else{

                                    Toast.makeText(AddRabbitRecord.this, "Error: Please check your internet connection", Toast.LENGTH_SHORT).show();
                                    loadingDialog.dismissDialog();
                                }

                            });
                });
            });
        }else{
            Toast.makeText(AddRabbitRecord.this, "Error: Please add the rabbit picture", Toast.LENGTH_LONG).show();
        }

    }

 private void InitializeValues(){


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

     //Circle ImageView
     rabbit_Record_image = findViewById(R.id.rabbit_Record_image);

     mAuth = FirebaseAuth.getInstance();
     RabbitPicRef = FirebaseStorage.getInstance().getReference().child("RabbitRecord");
     currentUserID = mAuth.getCurrentUser().getUid();

     RootRef = FirebaseDatabase.getInstance().getReference();


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

     year = calendar.get(Calendar.YEAR);
     month = calendar.get(Calendar.MONTH);
     day = calendar.get(Calendar.DAY_OF_MONTH);

 }

 private void SaveRabbitRecords(){

     HashMap<String, String> rabbitRecord = new HashMap<>();
     rabbitRecord.put("Name", rabbit_Record_name.getText().toString().trim());
     rabbitRecord.put("Sex", rabbit_Record_Sex.getSelectedItem().toString());
     rabbitRecord.put("DOB", rabbit_Record_DOB.getText().toString().trim());
     rabbitRecord.put("Fname", rabbit_Record_FatherName .getText().toString().trim());
     rabbitRecord.put("Mname", rabbit_Record_MotherName.getText().toString().trim());
     rabbitRecord.put("Origin", rabbit_Record_Origin.getSelectedItem().toString());
     rabbitRecord.put("Color", rabbit_Record_Color.getText().toString().trim());
     rabbitRecord.put("Wdate", rabbit_Record_Weaned.getText().toString().trim());
     rabbitRecord.put("Breed", rabbit_Record_Breeds.getSelectedItem().toString());
     rabbitRecord.put("Purpose", rabbit_Record_Purpose.getSelectedItem().toString());
     rabbitRecord.put("Notes", rabbit_Record_Notes.getText().toString().trim());
     rabbitRecord.put("Image",downloadUrl);

     databaseRef.child(currentUserID).setValue(rabbitRecord);

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