package com.ismailgemalmaz.sanalparam.NavigationFragment.InvestmentFragment.TabLayoutFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ismailgemalmaz.sanalparam.ForeginCurrencyService.ForeginCurrencyAPI;
import com.ismailgemalmaz.sanalparam.ForeginCurrencyService.ForeginCurrencyModel;
import com.ismailgemalmaz.sanalparam.NavigationFragment.InvestmentFragment.RecyclerView.InvestmentForeginRecyclerAdapter;
import com.ismailgemalmaz.sanalparam.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class InvestmentForeginCurrencyFragment extends Fragment {

    RecyclerView recyclerView;
    EditText serachText;
    private Retrofit retrofit;
    private String BASE_URL="https://finans.truncgil.com";
    ArrayList<ForeginCurrencyModel> currencyModels;
    InvestmentForeginRecyclerAdapter adapter;

    public static InvestmentForeginCurrencyFragment newInstance(){
        return new InvestmentForeginCurrencyFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.investment_foregin_currency_fragment,container,false);
       recyclerView= rootView.findViewById(R.id.investment_crypto_money_recyclerView);
       serachText=rootView.findViewById(R.id.investment_foregin_currency_searchText);
        return rootView;
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
        ForeginCurrencyAPI currencyAPI=retrofit.create(ForeginCurrencyAPI.class);
        Call<List<ForeginCurrencyModel>> listCall= currencyAPI.CALL();

       listCall.enqueue(new Callback<List<ForeginCurrencyModel>>() {
           @Override
           public void onResponse(Call<List<ForeginCurrencyModel>> call, Response<List<ForeginCurrencyModel>> response) {
               if(response.isSuccessful()){
                  List<ForeginCurrencyModel> list= response.body();
                  currencyModels=new ArrayList<>(list);


                  LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
                   linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                   recyclerView.setLayoutManager(linearLayoutManager);
                   adapter=new InvestmentForeginRecyclerAdapter(currencyModels,getContext());
                   recyclerView.setAdapter(adapter);


               }
           }

           @Override
           public void onFailure(Call<List<ForeginCurrencyModel>> call, Throwable t) {
                t.getLocalizedMessage();
           }
       });
    }
}
