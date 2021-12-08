package com.ismailgemalmaz.kriptoparam.ui.dashboard;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoAPI {

    // Https://api.nomics.com/v1/prices?key=a8a3452e71305947867f9f04df8fd319

    @GET("prices?key=a8a3452e71305947867f9f04df8fd319")
    Call<List<CryptoModel>> CALL();
}
