package com.ismailgemalmaz.not;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    CustomAdapter adapter;
    ArrayList<InformationModel> modelArrayList;
    ListView listView;
    SQLiteDatabase sqLiteDatabase;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.add_note_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.add_note_item){
            Intent intent=new Intent(MainActivity.this,InformationActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listView);
        modelArrayList=new ArrayList<>();
        getData();

    }

    public void getData() {
        adapter = new CustomAdapter(MainActivity.this, modelArrayList);

        try {
            sqLiteDatabase = MainActivity.this.openOrCreateDatabase("information", MODE_PRIVATE, null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM information", null);

            int dateIx = cursor.getColumnIndex("date");
            int descriptionIx = cursor.getColumnIndex("description");
            int wordsIx = cursor.getColumnIndex("words");

            while (cursor.moveToNext()) {
                String dateFormDateBase = cursor.getString(dateIx);
                String descriptionFormDateBase = cursor.getString(descriptionIx);
                String wordsFromDateBase = cursor.getString(wordsIx);

                InformationModel model = new InformationModel(dateFormDateBase, descriptionFormDateBase, wordsFromDateBase);

                modelArrayList.add(model);

            }
            adapter.notifyDataSetChanged();
            cursor.close();



        } catch (Exception e) {
            e.getLocalizedMessage();
        }

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(MainActivity.this,InformationActivity.class);
                intent.putExtra("modelArraylist",modelArrayList.get(position));
                startActivity(intent);
            }
        });
    }

}