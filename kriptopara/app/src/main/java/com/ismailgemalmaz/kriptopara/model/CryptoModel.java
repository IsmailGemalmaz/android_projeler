package com.ismailgemalmaz.kriptopara.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.work.Worker;

import com.google.gson.annotations.SerializedName;

public class CryptoModel  {

    @SerializedName("price")
  public String price;


    @SerializedName("currency")
  public String currency;



}
