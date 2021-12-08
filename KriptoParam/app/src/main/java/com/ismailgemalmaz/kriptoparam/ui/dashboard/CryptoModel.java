package com.ismailgemalmaz.kriptoparam.ui.dashboard;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class CryptoModel implements Serializable {

  public String currency;
  public double price;

  public String getCurrency() {
    return currency;
  }

  public double getPrice() {
    return price;
  }





}
