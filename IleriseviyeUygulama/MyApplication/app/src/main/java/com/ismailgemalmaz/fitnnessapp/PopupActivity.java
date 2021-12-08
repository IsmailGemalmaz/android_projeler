package com.ismailgemalmaz.fitnnessapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.ismailgemalmaz.fitnnessapp.Fragment.PopupFragment;

public class PopupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_activity);

        FragmentManager fragmentManager=getSupportFragmentManager();
        Fragment fragment=fragmentManager.findFragmentById(R.id.fragment_popup_frameLayaot);

        if(fragment==null){
            fragment=PopupFragment.newInstance();
            fragmentManager.beginTransaction().add(R.id.fragment_popup_frameLayaot,fragment).commit();
        }
    }

    public static Intent newIntent(Context context,FitnessMove fitnessMove){

        Intent intent=new Intent(context, PopupActivity.class);
        intent.putExtra("INFO",fitnessMove);

        return intent;
    }
}
