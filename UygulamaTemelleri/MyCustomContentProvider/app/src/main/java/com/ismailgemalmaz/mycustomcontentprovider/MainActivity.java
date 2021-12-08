package com.ismailgemalmaz.mycustomcontentprovider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
   static ArrayList<Bitmap> noteImageLis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //view intialize
        listView=findViewById(R.id.listView);
        ArrayList<String> noteNameList=new ArrayList<>();
         noteImageLis=new ArrayList<>();

        ArrayAdapter adapter=new ArrayAdapter(MainActivity.this, android.R.layout.simple_expandable_list_item_1,noteNameList);
        listView.setAdapter(adapter);

        //hangi uri dan çektiğimizi yazdık
        ContentResolver contentResolver=getContentResolver();
        String url= "content://"+NoteContentProvider.PROVİDER_NAME;
        Uri noteUri=Uri.parse(url);

        Cursor cursor=contentResolver.query(noteUri,null,null,null,"name");
        if(cursor!=null){
            while (cursor.moveToNext()){
                noteNameList.add(cursor.getString(cursor.getColumnIndex(NoteContentProvider.NAME)));
                //byte türünde olduğu için bitmap e çevirdik
                byte[] bytes=cursor.getBlob(cursor.getColumnIndex(NoteContentProvider.İMAGE));
                Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                noteImageLis.add(bitmap);

                adapter.notifyDataSetChanged();
            }
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),MainActivity2.class);
                intent.putExtra("info","old");//main activit ye nerden gidildiğini bilmek için
                intent.putExtra("name",noteNameList.get(position));
                intent.putExtra("position",position);//diğe taraftaki imaja paslamak ve oradakii almakm için
                startActivity(intent);
            }
        });
    }

    //kaynaklardaki menüyü MainActivit ye ekledik
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.add_note,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //menu içindeki item e seçme işlemini uyguladık
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
     int id=item.getItemId();
     if(id==R.id.add_note_item){
         Intent intent=new Intent(MainActivity.this,MainActivity2.class);
         intent.putExtra("info","new");
         startActivity(intent);
     }
        return super.onOptionsItemSelected(item);
    }
}