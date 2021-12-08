package com.ismailgemalmaz.kriptoparam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    EditText textFirstName,textSecondName,textTcNo,textEposta,textPassword;
    Button btnSignUp;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        textFirstName=findViewById(R.id.textFirstName);
        textSecondName=findViewById(R.id.textSecondName);
        textTcNo=findViewById(R.id.textTcNo);
        textEposta=findViewById(R.id.textEposta);
        textPassword=findViewById(R.id.textPassword);
        btnSignUp=findViewById(R.id.btnSignUp);
        firebaseAuth=FirebaseAuth.getInstance();

        onClick();
    }

    public void onClick(){

            // onclick signUp
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eposta=textEposta.getText().toString();
                String password=textPassword.getText().toString();
                firebaseAuth.createUserWithEmailAndPassword(eposta,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(SignUpActivity.this,"KULLANICI OLUŞTURULDU",Toast.LENGTH_LONG).show();
                        Intent intentSignIn=new Intent(SignUpActivity.this,SignInActivity.class);
                        startActivity(intentSignIn);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        AlertDialog.Builder alert=new AlertDialog.Builder(SignUpActivity.this);
                        alert.setTitle("HATA MESAJI");
                        alert.setMessage(e.getLocalizedMessage());
                        alert.setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Error button ok event
                            }
                        }); alert.show();
                    }
                });
            }
        });

    }
}