package com.ismailgemalmaz.myapplication;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ornek {


    /*  public class MainActivity  {
      ListView listView;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            listView=findViewById(R.id.listView);
            if(ContextCompat.checkSelfPermission(com.ismailgemalmaz.myapplication.MainActivity.this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(com.ismailgemalmaz.myapplication.MainActivity.this,new String[] {Manifest.permission.READ_CONTACTS},1);
            }

            FloatingActionButton fab = findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(ContextCompat.checkSelfPermission(com.ismailgemalmaz.myapplication.MainActivity.this,Manifest.permission.READ_CONTACTS)==PackageManager.PERMISSION_GRANTED){
                        //Content Provider
                        ContentResolver contentResolver=getContentResolver();//içerik çözümleyicid
                        String[] projection={ContactsContract.Contacts.DISPLAY_NAME};
                        Cursor cursor=contentResolver.query(ContactsContract.Contacts.CONTENT_URI,projection,null,null,
                                ContactsContract.Contacts.DISPLAY_NAME);
                        if(cursor!=null){
                            ArrayList<String> contaxtLst= new ArrayList<String>() ;
                            String columIx= ContactsContract.Contacts.DISPLAY_NAME;
                            while (cursor.moveToNext()){
                                contaxtLst.add(cursor.getString(cursor.getColumnIndex(columIx)));
                            }
                            cursor.close();
                            ArrayAdapter adapter=new ArrayAdapter(com.ismailgemalmaz.myapplication.MainActivity.this, android.R.layout.simple_expandable_list_item_1,contaxtLst);
                            listView.setAdapter(adapter);
                        }
                    }else{
                        Snackbar.make(view, "İZİN VERMELİSİN", Snackbar.LENGTH_LONG)
                                .setAction("İZİN VER", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if(ActivityCompat.shouldShowRequestPermissionRationale(com.ismailgemalmaz.myapplication.MainActivity.this,Manifest.permission.READ_CONTACTS)){
                                            ActivityCompat.requestPermissions(com.ismailgemalmaz.myapplication.MainActivity.this,new String[] {Manifest.permission.READ_CONTACTS},1);
                                        }else{
                                            Intent intent=new Intent();
                                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                            Uri uri=Uri.fromParts("package", com.ismailgemalmaz.myapplication.MainActivity.this.getPackageName(),null);
                                            intent.setData(uri);
                                            com.ismailgemalmaz.myapplication.MainActivity.this.startActivity(intent);
                                        }

                                    }

                                }).show();
                    }
                }
            });
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }*/

}
