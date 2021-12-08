package com.ismailgemalmaz.sanalparam.CryptoMoneyService;

import java.io.Serializable;
import java.math.BigDecimal;

public class CryptoModel implements Serializable {

public String currency;
public double price;
public String logo_url;
public String name;
public String high;
public String market_cap;
public String circulating_supply;
public String max_supply;


    public String getCirculating_supply() {
        return circulating_supply;
    }

    public String getMarket_cap() {
        return market_cap;
    }

    public String getHigh() {
        return high;
    }

    public String getMax_supply() {
        return max_supply;
    }

    public String getLogo_url(){
        return logo_url;
    }

    public String getName() {
        return name;
    }

    public String getCurrency() {
        return currency;
    }

    public double getPrice() {
        return price;
    }
}
