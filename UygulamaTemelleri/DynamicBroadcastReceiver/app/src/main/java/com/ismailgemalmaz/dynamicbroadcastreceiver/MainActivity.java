package com.ismailgemalmaz.dynamicbroadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    BroadcastingClass broadcastingClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        broadcastingClass=new BroadcastingClass();
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_LOW);//batarya krtik sseviyede ise

        registerReceiver(broadcastingClass,intentFilter);//dinamik olarak broadacast sınıfını kullanıp kullanmamak arasında kalınca kulanırız manifeste eklememk için
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(broadcastingClass);
    }
}