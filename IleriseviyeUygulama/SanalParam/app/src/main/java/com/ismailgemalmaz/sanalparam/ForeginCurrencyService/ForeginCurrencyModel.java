package com.ismailgemalmaz.sanalparam.ForeginCurrencyService;

import java.io.Serializable;

public class ForeginCurrencyModel implements Serializable {

    public String currency;
   public double Alış;
   public double Satış;
   public double Tür;

    public String getCurrency() {
        return currency;
    }

    public double getAlış() {
        return Alış;
    }

    public double getSatış() {
        return Satış;
    }

    public double getTür() {
        return Tür;
    }
}
