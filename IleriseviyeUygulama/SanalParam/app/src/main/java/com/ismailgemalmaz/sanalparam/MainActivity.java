package com.ismailgemalmaz.sanalparam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ismailgemalmaz.sanalparam.CryptoMoneyService.CryptoModel;
import com.ismailgemalmaz.sanalparam.NavigationFragment.InvestmentFragment.InvestmentFragment;
import com.ismailgemalmaz.sanalparam.NavigationFragment.PersonFragment.PersonFragment;
import com.ismailgemalmaz.sanalparam.NavigationFragment.SettingsFragment.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //ToolBar'ı action barın yerine kullandık
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Bottom Navigation tanımladık intialize ettik
        BottomNavigationView navigationView=findViewById(R.id.bottom_navigation);

        //bottom navigationa oluşturduğumuz navlistener fonksiyonunu verdik
        navigationView.setOnNavigationItemSelectedListener(navListener);

        //varsayılan olarak InvestmantFragment'ı verdik
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new PersonFragment()).commit();

    }


    //navigationbottom selected
   private BottomNavigationView.OnNavigationItemSelectedListener navListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment=null;

                    switch (item.getItemId()){


                        case R.id.person :
                            selectedFragment=PersonFragment.newInstance();
                            break;
                        case R.id.investment:
                            selectedFragment= InvestmentFragment.newInstance();
                            break;
                        case R.id.settings:
                            selectedFragment= SettingsFragment.newInsance();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();


                    return true;
                }
            };






}