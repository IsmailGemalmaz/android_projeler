package com.ismailgemalmaz.sanalparam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {


    EditText txtName,txtEmail,txtPassword,txtTryPassword;
    ImageView imageView;
    Button btnSignUp;
    FirebaseAuth  firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        txtName=findViewById(R.id.txtName);
        txtEmail=findViewById(R.id.txtEmail);
        txtPassword=findViewById(R.id.txtPassword);
        txtTryPassword=findViewById(R.id.txtTryPassword);
        imageView=findViewById(R.id.imageView);
        btnSignUp=findViewById(R.id.btnSignUpAc);

        firebaseAuth=FirebaseAuth.getInstance();

        buttonOncilck();




    }

    public void buttonOncilck(){
        String email= txtEmail.getText().toString();
        String password=txtPassword.getText().toString();



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                }
        });
    }


}