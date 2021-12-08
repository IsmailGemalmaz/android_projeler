package com.ismailgemalmaz.sanalparam.NavigationFragment.InvestmentFragment.TabLayoutFragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ismailgemalmaz.sanalparam.CryptoMoneyInfo;
import com.ismailgemalmaz.sanalparam.CryptoMoneyService.CryptoAPI;
import com.ismailgemalmaz.sanalparam.CryptoMoneyService.CryptoModel;
import com.ismailgemalmaz.sanalparam.NavigationFragment.InvestmentFragment.RecyclerView.InvestmentCryptoRecyclerAdapter;
import com.ismailgemalmaz.sanalparam.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class InvestmentCryptoMoneyFragment extends Fragment implements InvestmentCryptoRecyclerAdapter.CryptoMyListener{

    private View bottomView;
   private BottomSheetDialog dialog;
   private ArrayList<CryptoModel> cryptoModels;
   private RecyclerView recyclerView;
   private EditText searchText;
    private final String BASE_URL="https://api.nomics.com/v1/";
   private InvestmentCryptoRecyclerAdapter adapter;
    private Retrofit retrofit;



    public static InvestmentCryptoMoneyFragment newInstance(){
        return new InvestmentCryptoMoneyFragment();
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rotView=inflater.inflate(R.layout.investment_crypto_money_fragment,container,false);

        recyclerView=    rotView.findViewById(R.id.investment_crypto_money_recyclerView);
        searchText=rotView.findViewById(R.id.investment_crypto_money_searchText);

        //--------------------filter-----------------------------
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());//filter fonksiyonu
            }
        });
        //-------------------------------------------
        return rotView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Gson gson=new GsonBuilder().setLenient().create();
        retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                addConverterFactory(GsonConverterFactory.create(gson)).build();
        loadData();//VERİLERİ ÇEKTİĞİMİZ FONKSİYON


    }


    //---------------------filter---------------------------------
    private void filter(String text){
        ArrayList<CryptoModel> filterdList=new ArrayList<>();
        for(CryptoModel model:cryptoModels){
            if(model.getName().toLowerCase().contains(text.toLowerCase())){
                filterdList.add(model);
            }
        }
        adapter.filterList(filterdList);
    }
//-------------------------------------------


    public void loadData(){


        //api dan verileri çekdiğimiz yer
        CryptoAPI cryptoAPI=retrofit.create(CryptoAPI.class);
       Call<List<CryptoModel>> listCall= cryptoAPI.CALL();

        listCall.enqueue(new Callback<List<CryptoModel>>() {
            @Override
            public void onResponse(Call<List<CryptoModel>> call, Response<List<CryptoModel>> response) {
                  if(response.isSuccessful()){

                      List<CryptoModel> list= response.body();
                      cryptoModels=new ArrayList<>(list);

                      LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
                      linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                      recyclerView.setLayoutManager( linearLayoutManager);
                      adapter=new InvestmentCryptoRecyclerAdapter(cryptoModels,getContext(),InvestmentCryptoMoneyFragment.this::MyListiner);
                      recyclerView.setAdapter(adapter);
                  }


            }

            @Override
            public void onFailure(Call<List<CryptoModel>> call, Throwable t) {
                t.getLocalizedMessage();
            }
        });
    }


    @Override
    public void MyListiner(CryptoModel cryptoModel) {
        dialog=new BottomSheetDialog(getContext(),R.style.BottmDialog);
        bottomView=LayoutInflater.from(getContext()).inflate(R.layout.bottom_shett_dialog,(LinearLayout)getView().findViewById(R.id.dialog_linear_layout));

        bottomView.findViewById(R.id.Buying).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        bottomView.findViewById(R.id.Details).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext().getApplicationContext(),CryptoMoneyInfo.class);
                intent.putExtra("INFO",cryptoModel);
                startActivity(intent);
                dialog.cancel();
            }
        });
        bottomView.findViewById(R.id.Close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.setContentView(bottomView);
        dialog.show();

    }


    }

