package com.ismailgemalmaz.kriptopara.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.app.VoiceInteractor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.textclassifier.ConversationActions;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ismailgemalmaz.kriptopara.R;
import com.ismailgemalmaz.kriptopara.UserActivity;
import com.ismailgemalmaz.kriptopara.adapter.RecyclerViewAdapter;
import com.ismailgemalmaz.kriptopara.model.CryptoModel;
import com.ismailgemalmaz.kriptopara.service.CryptoAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<CryptoModel> cryptoModels;
    private String BASE_URL="https://api.nomics.com/v1/";
    Retrofit retrofit;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    CryptoModel cryptoModel;
    Runnable runnable;
    Handler handler;

    // CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //https://api.nomics.com/v1/prices?key=a8a3452e71305947867f9f04df8fd319
        //recycler View

        recyclerView=findViewById(R.id.recyclerView);

        //RETROFİT JSON

        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                addConverterFactory(GsonConverterFactory.create(gson)).build();



       loadData();
       handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {

                loadData();
                handler.postDelayed(runnable,10000);


            }
        };
        handler.post(runnable);

    }
    private void loadData(){

        CryptoAPI cryptoAPI=retrofit.create(CryptoAPI.class);

        //compositeDisposable =new CompositeDisposable();



        Call<List<CryptoModel>>  call=cryptoAPI.getData();

        call.enqueue(new Callback<List<CryptoModel>>() {
            @Override
            public void onResponse(Call<List<CryptoModel>> call, Response<List<CryptoModel>> response) {
                if(response.isSuccessful()){
                    List<CryptoModel> responseList=response.body();
                    cryptoModels=new ArrayList<>(responseList);


                  /*  for(CryptoModel cryptoModel:cryptoModels){
                        System.out.println(cryptoModel.price);
                        System.out.println(cryptoModel.currency);
                    }*/

                    //RECYCLER VİEW

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    adapter=new RecyclerViewAdapter(cryptoModels, MainActivity.this,UserActivity.class);

                    recyclerView.setAdapter(adapter);




                }


            }

            @Override
            public void onFailure(Call<List<CryptoModel>> call, Throwable t) {
                    t.getLocalizedMessage();
            }
        });

    }


}