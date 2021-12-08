package com.ismailgemalmaz.sanalparam.ForeginCurrencyService;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ForeginCurrencyAPI {

    @GET("/today.json")
    Call<List<ForeginCurrencyModel>> CALL();
}
