package com.ismailgemalmaz.sanalparam.CryptoMoneyService;

import com.ismailgemalmaz.sanalparam.CryptoMoneyService.CryptoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoAPI {
    //https://api.nomics.com/v1/currencies/ticker?key=a8a3452e71305947867f9f04df8fd319

    // Https://api.nomics.com/v1/prices?key=a8a3452e71305947867f9f04df8fd319

    //kripto para api nerden neyi çekiceğimiz retrofit ile yapıldı
    @GET("currencies/ticker?key=a8a3452e71305947867f9f04df8fd319")
    Call<List<CryptoModel>> CALL();
}
