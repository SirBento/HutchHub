package com.example.hutchhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {


    private EditText log_Email, logPass;
    TextView forgotPassword;

    Button btn_login, btn_Sign_Up;

    private FirebaseAuth mAuth;
    LoadingDialog loadingDialog = new LoadingDialog(Login.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        forgotPassword = findViewById(R.id.lgn_forgot_pass);
        log_Email = findViewById(R.id.lgn_email);
        logPass = findViewById(R.id.lgn_pass);
        btn_login = findViewById(R.id.btn_login);
        btn_Sign_Up  = findViewById(R.id.btn_signup);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!validatePassword() |  !validateEmail() ){
                    // If the functions continue to return false repeat validation until true
                    return;
                }

                if(!haveNetworkConnection()){

                    Toast.makeText(Login.this, "Please Check Your Internet Connection", Toast.LENGTH_LONG).show();

                }else{
                    loadingDialog.startLoadingDialog();
                    userLogin();

                }

            }
        });


        //sign up activity calling

        btn_Sign_Up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent( Login.this,SignUp.class) );
            }
        });



        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent( Login.this,ForgotPassword.class) );
            }
        });


    }




    private void userLogin(){

        mAuth.signInWithEmailAndPassword(log_Email.getText().toString().trim(),logPass.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            //checking if the account is verified , if not the user has to first verify the account before logging in
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            if(user.isEmailVerified()){
                                loadingDialog.dismissDialog();

                            }else{

                                user.sendEmailVerification();
                                loadingDialog.dismissDialog();
                                Toast.makeText(Login.this,
                                        "Account not verified. Please check your email to verify your account the Login",
                                        Toast.LENGTH_LONG).show();
                            }

                        }else {
                            loadingDialog.dismissDialog();
                            Toast.makeText(Login.this, "LogIn Failed!!! Please check your credentials and your internet connection", Toast.LENGTH_LONG).show();

                        }
                    }
                });


    }
    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }

        return haveConnectedWifi || haveConnectedMobile;
    }


    private boolean validateEmail(){
        String e_mail = log_Email.getText().toString().trim();

        //checking if the email text box is filled or not
        if(e_mail.isEmpty()){
            log_Email.setError("Email is required");
            log_Email.requestFocus();
            return false;

            //checking if the email entered is valid
        }else if(!Patterns.EMAIL_ADDRESS.matcher(e_mail).matches()){
            log_Email.setError("Please enter a email ");
            log_Email.requestFocus();
            return false;

        } else {
            log_Email.setError(null);
            return true;

        }

    }

    private boolean validatePassword(){

        String pass = logPass.getText().toString().trim();

        // checking if the password text box is filled or not

        if(pass.isEmpty()){
            logPass.setError("Password is required");
            logPass.requestFocus();
            return false;

            // checking the password length due to firebase restrictions
        } else if (pass.length()< 6) {
            logPass.setError("Your password is too short");
            logPass.requestFocus();
            return false;

        } else {
            log_Email.setError(null);
            return true;

        }


    }

}

