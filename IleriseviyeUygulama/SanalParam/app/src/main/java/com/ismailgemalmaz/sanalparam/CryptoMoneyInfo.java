package com.ismailgemalmaz.sanalparam;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.ismailgemalmaz.sanalparam.CryptoMoneyService.CryptoModel;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

public class CryptoMoneyInfo extends AppCompatActivity {

    //DEĞİŞKENLER
    TextView currency,price,highPrice,marketCap,circulatingSupply,maxSupply,cryptoVolume;
    CryptoModel cryptoModel;
    ImageView imageView;
    GraphView graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto_money_info);

        //action bar yerine toolbar kullanmak
        androidx.appcompat.widget.Toolbar toolbar =findViewById(R.id.activit_info_toolbar);
        setSupportActionBar(toolbar);


        //İNTİALİZE
        currency=findViewById(R.id.cryptoInfoCurrency);
        price=findViewById(R.id.cryptoInfoPrice);
        imageView=findViewById(R.id.cryptoInfoImage);
        highPrice=findViewById(R.id.cryptoInfoPriceHigh);
        marketCap=findViewById(R.id.cryptoMarketCap);
        circulatingSupply=findViewById(R.id.cryptoCirculatinSupply);
        maxSupply=findViewById(R.id.cryptoMaxSupply);
        cryptoVolume=findViewById(R.id.cryptoVolume);
        graph=findViewById(R.id.graph);
        //-------------------------------------------------------



        cryptoModel=(CryptoModel) getIntent().getSerializableExtra("INFO"); //CRYPTOMONEY FRAGMENTTEN GÖNDERİLEN PUTEXTRA ALMAK



                //--------------GÖRÜNÜMLERİN İÇİNE YAZILACAKLARI AYARLAMAK--------------
                currency.setText("İSİM : "+cryptoModel.getName()+" ("+cryptoModel.getCurrency()+")");
                price.setText("FİYAT : "+cryptoModel.getPrice()+" $");

                Uri uri=Uri.parse(cryptoModel.getLogo_url());//STRİNG URİ ÇEVİRMEK
                Picasso.get().load(uri).into(imageView);//İNTERNETTEKİ RESMİ ALMAK

                highPrice.setText("En Yüksek Değeri : "+cryptoModel.getHigh()+" $");
                marketCap.setText("Market Cap : "+cryptoModel.getMarket_cap());
                circulatingSupply.setText("Dolaşan Arz :"+cryptoModel.getCirculating_supply());
                maxSupply.setText("Max Arz : "+cryptoModel.getMax_supply());
                //--------------------------------------------------------------------


                //--------------------GRAFİK YAPMAK----------------------
                LineGraphSeries<DataPoint> series=new LineGraphSeries<>(
                        new DataPoint[]{
                                new DataPoint(1,cryptoModel.getPrice()),
                                new DataPoint(2,cryptoModel.getPrice()),
                                new DataPoint(3,cryptoModel.getPrice()),
                                new DataPoint(4,cryptoModel.getPrice()),
                                new DataPoint(5,cryptoModel.getPrice()),
                        });
                graph.addSeries(series);
                //---------------------------------------------------------






    }

}