package com.ismailgemalmaz.sanalparam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class CryptoAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto_account);
        Toolbar toolbar = findViewById(R.id.accountToolBar);
        setSupportActionBar(toolbar);

    }
}