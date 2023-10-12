package com.example.hutchhub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    private EditText firstName, email, passWord1, passWord2;

    private Button signUp;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        signUp = findViewById(R.id.btn_CreateAcc);
        firstName = findViewById(R.id.signUp_Name);
        email = findViewById(R.id.signUp_Email);
        passWord1 = findViewById(R.id.signUp_pass);
        passWord2 = findViewById(R.id.signUp_pass2);



        // creating a variable for accessing the database authentication
        mAuth = FirebaseAuth.getInstance();


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!validateSecondPassword() | !validateFirstPassword() | !validateFirstName()  |  !validateEmail() ){

                    return;
                }

                registerUser();

            }
        });
    }
}