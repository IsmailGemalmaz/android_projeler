package com.ismailgemalmaz.share;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.badge.BadgeDrawable;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ActivityLogin extends AppCompatActivity {
    //Definition

    EditText textEposta, textPassword;
    Button btnSıgnIn, btnSıgnUp;
    TextView textMessage;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initialize

        textEposta = findViewById(R.id.textEposta);
        textPassword = findViewById(R.id.textPassword);
        textMessage = findViewById(R.id.textMessage);
        btnSıgnIn = findViewById(R.id.btnSignIn);
        btnSıgnUp = findViewById(R.id.btnSignUp);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null) {
            Intent feedActivityIntent = new Intent(ActivityLogin.this, FeedActivity.class);
            startActivity(feedActivityIntent);
            finish();
        }

        //button sıgnIn onclick
        btnSıgnIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eposta = textEposta.getText().toString();
                String password = textPassword.getText().toString();

                firebaseAuth.signInWithEmailAndPassword(eposta, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        if (authResult != null) {
                            Intent feedActivityIntent = new Intent(ActivityLogin.this, FeedActivity.class);
                            startActivity(feedActivityIntent);
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        textMessage.setText(e.getLocalizedMessage());
                    }
                });

            }
        });

        //button sıgnUp oncilck
        btnSıgnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eposta = textEposta.getText().toString();
                String password = textPassword.getText().toString();

                firebaseAuth.createUserWithEmailAndPassword(eposta, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        if (authResult != null) {
                            Intent feedActivityIntent = new Intent(ActivityLogin.this, FeedActivity.class);
                            startActivity(feedActivityIntent);
                            finish();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        textMessage.setText(e.getLocalizedMessage());
                    }
                });
            }
        });

    }
}