package com.ismailgemalmaz.sanalparam.LoginOperations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.ismailgemalmaz.sanalparam.BaseLoginFragment;
import com.ismailgemalmaz.sanalparam.MainActivity;
import com.ismailgemalmaz.sanalparam.R;
import com.ismailgemalmaz.sanalparam.SignİInInfoFragment;

public class LoginProcesses extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_processes);

        FragmentManager fragmentManager=getSupportFragmentManager();
        Fragment fragment=fragmentManager.findFragmentById(R.id.LoginProcses);

        if(fragment==null){
            fragment=new BaseLoginFragment();
            fragmentManager.beginTransaction().add(R.id.LoginProcses,fragment).commit();
        }


    }




}