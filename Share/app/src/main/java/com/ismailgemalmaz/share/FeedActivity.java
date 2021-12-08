package com.ismailgemalmaz.share;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class FeedActivity extends AppCompatActivity {

    //DEFİNİTİON

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    ArrayList<String> arrayListEposta;
    ArrayList<String> arrayListComment;
    ArrayList<String> arrayListİmage;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        //İNTİALİZE
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        arrayListEposta=new ArrayList<>();
        arrayListComment=new ArrayList<>();
        arrayListİmage=new ArrayList<>();
        readDataFireBase();
        //recycler view
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new RecyclerViewAdapter(arrayListEposta,arrayListComment,arrayListİmage);
        recyclerView.setAdapter(adapter);
    }

    //MENU INFLATER

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //ITEM SELECTED

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.post_add:
                Intent uploadActivityIntent = new Intent(FeedActivity.this, UploadActivity.class);
                startActivity(uploadActivityIntent);
                break;
            case R.id.sign_out:
                firebaseAuth.signOut();
                Intent loginActivityIntent = new Intent(FeedActivity.this, ActivityLogin.class);
                startActivity(loginActivityIntent);
                break;

        }
        return super.onOptionsItemSelected(item);

    }

    public  void readDataFireBase(){
        CollectionReference reference=firebaseFirestore.collection("post");
        reference.orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){
                    Toast.makeText(FeedActivity.this,error.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }

                if(value!=null){
                    for(DocumentSnapshot snapshot:value.getDocuments()){
                        Map<String,Object>  data=snapshot.getData();
                        String eposta=(String) data.get("userEmail");
                        String comment=(String)data.get("comment");
                        String image=(String)data.get("imagaeUrl");

                        arrayListEposta.add(eposta);
                        arrayListComment.add(comment);
                        arrayListİmage.add(image);

                        adapter.notifyDataSetChanged();

                    }
                }
            }
        });
    }

}

