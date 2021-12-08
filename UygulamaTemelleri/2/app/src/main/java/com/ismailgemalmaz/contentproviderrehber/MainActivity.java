package com.ismailgemalmaz.contentproviderrehber;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.provider.ContactsContract;
import android.provider.Settings;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView=findViewById(R.id.listView);
        // ilk olarak izin istedik
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[] {Manifest.permission.READ_CONTACTS},1);
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_CONTACTS)
                        ==PackageManager.PERMISSION_GRANTED){
                    ContentResolver contentResolver=getContentResolver();
                    String[] projection={ContactsContract.Contacts.DISPLAY_NAME};
                    Cursor cursor=contentResolver.query(ContactsContract.Contacts.CONTENT_URI,projection,null,null,ContactsContract.Contacts.DISPLAY_NAME);
                        if(cursor!=null){
                            ArrayList<String> arrayList=new ArrayList<>();
                            String curdorIx= ContactsContract.Contacts.DISPLAY_NAME;

                            while(cursor.moveToNext()){
                                arrayList.add(cursor.getString(cursor.getColumnIndex(curdorIx)));
                            }
                            cursor.close();
                            ArrayAdapter adapter=new ArrayAdapter(MainActivity.this, android.R.layout.simple_expandable_list_item_1,arrayList);
                            listView.setAdapter(adapter);
                        }
                }else{
                    Snackbar.make(view,"İZİN VERMELİSİN",Snackbar.LENGTH_INDEFINITE).setAction("izin ver", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.READ_CONTACTS)){
                                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_CONTACTS},1);
                            }else{
                                Intent intent=new Intent();
                                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri=Uri.fromParts("package",MainActivity.this.getPackageName(),null);
                                intent.setData(uri);
                               MainActivity.this.startActivity(intent);
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
    }
}