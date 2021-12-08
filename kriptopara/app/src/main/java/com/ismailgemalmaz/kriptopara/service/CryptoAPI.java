package com.ismailgemalmaz.kriptopara.service;

import android.database.Observable;

import androidx.work.Worker;

import com.ismailgemalmaz.kriptopara.model.CryptoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoAPI  {

    //GET,POST,UPDATE,DELETE

    //https://api.nomics.com/v1/prices?key=a8a3452e71305947867f9f04df8fd319


    @GET("prices?key=a8a3452e71305947867f9f04df8fd319")
   // Observable<List<CryptoModel>> getData();
    Call<List<CryptoModel>> getData();


}
