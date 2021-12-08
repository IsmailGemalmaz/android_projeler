package com.ismailgemalmaz.kriptoparam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ismailgemalmaz.kriptoparam.ui.home.HomeFragment;

public class SignInActivity extends AppCompatActivity {


    EditText textEposta,textPassword;
    TextView textSignUp;
    Button btnSignIn;


    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        textEposta=findViewById(R.id.textEposta);
        textPassword=findViewById(R.id.textPassword);
        textSignUp=findViewById(R.id.textSignUp);
        btnSignIn=findViewById(R.id.btnSignIn);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();

        if(firebaseUser!=null){
            Intent intent=new Intent(SignInActivity.this,BaseActivity.class);
            startActivity(intent);
            finish();
        }

        onClick();//onClick method


    }

    //onCick
    public void onClick(){

        //onClick SiginIn
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eposta=textEposta.getText().toString();
                String password=textPassword.getText().toString();

                firebaseAuth.signInWithEmailAndPassword(eposta,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                      Intent intent=new Intent(SignInActivity.this,BaseActivity.class);
                      startActivity(intent);
                      finish();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        AlertDialog.Builder alert=new AlertDialog.Builder(SignInActivity.this);
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

        //onClick SiginUp activity
        textSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpIntent=new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(signUpIntent);
            }
        });

    }
}