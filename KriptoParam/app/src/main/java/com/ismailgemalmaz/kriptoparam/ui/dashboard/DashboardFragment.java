package com.ismailgemalmaz.kriptoparam.ui.dashboard;

import android.os.Bundle;

import android.preference.SwitchPreference;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ismailgemalmaz.kriptoparam.BaseActivity;
import com.ismailgemalmaz.kriptoparam.R;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import static android.content.ContentValues.TAG;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class DashboardFragment extends Fragment {

  private RecyclerView recyclerView;
  private Retrofit retrofit;
       private String BASE_URL="https://api.nomics.com/v1/";
   ArrayList<CryptoModel> cryptoModels;
   RecyclerViewAdapter  recyclerViewAdapter;
   TextView textSize;
   CryptoModel cryptoModel;
   EditText textSearch;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_dashboard,container,false);
        recyclerView=view.findViewById(R.id.recyclerView);
        textSize=view.findViewById(R.id.textSize);
        textSearch=view.findViewById(R.id.textSearch);
        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Gson gson=new GsonBuilder().setLenient().create();
        retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson)).build();

        loadData();
        
    }



    public void loadData(){
        CryptoAPI cryptoAPI=retrofit.create(CryptoAPI.class);

      Call<List<CryptoModel>> listCall = cryptoAPI.CALL();

      listCall.enqueue(new Callback<List<CryptoModel>>() {
          @Override
          public void onResponse(Call<List<CryptoModel>> call, Response<List<CryptoModel>> response) {
              if(response.isSuccessful()){
                 List<CryptoModel> list= response.body();
                  cryptoModels=new ArrayList<>(list);

                  textSize.setText("LİSTEDEKİ KRİPTO PARA :"+cryptoModels.size()+" ADET");

                  LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
                  linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                  recyclerView.setLayoutManager(linearLayoutManager);
                  recyclerViewAdapter=new RecyclerViewAdapter(cryptoModels,getContext());
                  recyclerView.setAdapter(recyclerViewAdapter);


              }
          }

          @Override
          public void onFailure(Call<List<CryptoModel>> call, Throwable t) {
            t.getLocalizedMessage();
          }
      });

    }



    }



