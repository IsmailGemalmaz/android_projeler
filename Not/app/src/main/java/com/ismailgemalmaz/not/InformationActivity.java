package com.ismailgemalmaz.not;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class InformationActivity extends AppCompatActivity {

    EditText textDate,textDescription,textWords;
    ImageView imageView;
    Button btnSave;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        textDate=findViewById(R.id.textDate);
        textDescription=findViewById(R.id.textDescription);
        textWords=findViewById(R.id.textWords);
        imageView=findViewById(R.id.imageView);
        btnSave=findViewById(R.id.btnSave);

        Intent intent=getIntent();
        intent.getSerializableExtra("modelArraylist");



        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String date=textDate.getText().toString();
                String descripton=textDescription.getText().toString();
                String words=textWords.getText().toString();
               try {
                   database=InformationActivity.this.openOrCreateDatabase("information",MODE_PRIVATE,null);
                   database.execSQL("CREATE TABLE IF NOT EXISTS information (id INTEGER PRIMARY KEY,date VARCHAR,description VARCHAR,words VARCHAR)");

                   String toCompile="INSERT INTO information (date,description ,words) VALUES (?,?,?)";
                   SQLiteStatement sqLiteStatement=database.compileStatement(toCompile);
                   sqLiteStatement.bindString(1,date);
                   sqLiteStatement.bindString(2,descripton);
                   sqLiteStatement.bindString(3,words);
                   sqLiteStatement.execute();

                   Toast.makeText(InformationActivity.this,"NOT KAYDEDİLDİ",Toast.LENGTH_LONG).show();

               }catch (Exception e){
                   Toast.makeText(InformationActivity.this,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
               }
               finish();
            }
        });


    }

}

