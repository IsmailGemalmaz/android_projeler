package com.ismailgemalmaz.mycustomcontentprovider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;

public class MainActivity2 extends AppCompatActivity {

    EditText textName;
    ImageView imageView;
    Button btnSave, btnDelete, btnUpdate;
    Bitmap selectedBitmap;

    String firstName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textName = findViewById(R.id.textName);
        imageView = findViewById(R.id.imageView);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);



        onClickBtnandİmage();

        Intent intent=getIntent();
       String info= intent.getStringExtra("info");

       if(info.matches("")){
           Bitmap backGround=BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.image);
           imageView.setImageBitmap(backGround);
           textName.setText("");
           btnSave.setVisibility(View.VISIBLE);
           btnUpdate.setVisibility(View.INVISIBLE);
           btnDelete.setVisibility(View.INVISIBLE);
       }else{
           String name=intent.getStringExtra("name");
           textName.setText(name);
           firstName=name;
           int position=intent.getIntExtra("position",0);
           imageView.setImageBitmap(MainActivity.noteImageLis.get(position));


           btnSave.setVisibility(View.INVISIBLE);
           btnUpdate.setVisibility(View.VISIBLE);
           btnDelete.setVisibility(View.VISIBLE);
       }
    }

    public void onClickBtnandİmage() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //neleri kaydediceğimizi yazacağız
                String noteName = textName.getText().toString();
                // bitmap i bytearray dnüştürme== Resimleri byte şeklinde SQLite kaydederiz
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                selectedBitmap.compress(Bitmap.CompressFormat.PNG, 50, outputStream);
                byte[] bytes = outputStream.toByteArray();

                //contentvalues sayesinde değerle verildi
                ContentValues contentValues = new ContentValues();//içerik sağlayıcı sınıfında insert işleminin bizden istediği insert fnksiyonunun parametresine bak
                contentValues.put(NoteContentProvider.NAME, noteName);//nereye kaydediceği ve neyi kaydedieği verildi
                contentValues.put(NoteContentProvider.İMAGE, bytes);//nereye kaydediceği ve neyi kaydedieği verildi

                //verilen değerlerin kaydedilmne işlemi
                getContentResolver().insert(NoteContentProvider.CONTENT_URI, contentValues);//content values e verdiğimiz değerler kaydedildi

                //kayıt işleminden sonra main activity e gidileceğini belirttik
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noteName = textName.getText().toString();

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                Bitmap bitmap=((BitmapDrawable) imageView.getDrawable()).getBitmap();//seçili resimi anlaması için
                bitmap.compress(Bitmap.CompressFormat.PNG,50,outputStream);
                byte[] bytes = outputStream.toByteArray();

                ContentValues contentValues=new ContentValues();
                contentValues.put(NoteContentProvider.NAME,noteName);
                contentValues.put(NoteContentProvider.İMAGE,bytes);

                String[] selectionArgs={firstName};

                getContentResolver().update(NoteContentProvider.CONTENT_URI,contentValues,"name=?",selectionArgs);

                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String name=    textName.getText().toString();

            String[] selectionArgs={name};
            getContentResolver().delete(NoteContentProvider.CONTENT_URI,"name=?",selectionArgs);

            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity2.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity2.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                }

                if (ContextCompat.checkSelfPermission(MainActivity2.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    Snackbar.make(v, "İZİN VERMELİSİN", Snackbar.LENGTH_INDEFINITE).setAction("İZİN VER", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity2.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                                ActivityCompat.requestPermissions(MainActivity2.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);

                            } else {
                                Intent intent = new Intent();
                                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", MainActivity2.this.getPackageName(), null);
                                intent.setData(uri);
                                MainActivity2.this.startActivity(intent);
                            }

                        }
                    }).show();
                }


            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();

            try {
                selectedBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                imageView.setImageBitmap(selectedBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}