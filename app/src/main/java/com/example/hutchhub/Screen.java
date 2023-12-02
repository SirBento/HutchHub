package com.example.hutchhub;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Screen extends AppCompatActivity {

    private TextView getStarted;
    FirebaseUser LoggediNuser;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);

        getStarted = findViewById(R.id.btn_get_started);
        mAuth = FirebaseAuth.getInstance();

        LoggediNuser = mAuth.getCurrentUser();




        getStarted.setOnClickListener(v -> {

            startActivity(new Intent(Screen.this, Login.class ));

            if(LoggediNuser!= null){
                LoggediNuser.getUid();
                startActivity(new Intent(Screen.this,MainActivity.class));
                finish();
            }else {
                startActivity(new Intent(Screen.this,Login.class));
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        if(LoggediNuser!= null){
            LoggediNuser.getUid();
            startActivity(new Intent(Screen.this, MainActivity.class));
            finish();

        }

    }
}