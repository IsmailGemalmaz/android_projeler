package com.ismailgemalmaz.not;

import java.io.Serializable;

public class InformationModel implements Serializable {

   public String Date;

  public  String Descripton;

  public  String Words;

    public InformationModel(String date, String descripton, String words) {
        Date = date;
        Descripton = descripton;
        Words = words;
    }
}
