package com.ismailgemalmaz.exampleprof;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import butterknife.BindView;

public class WelcomeActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.ibBack) ImageButton ibBack;
    @BindView(R.id.llSignIn) LinearLayout llSignIn;
    @BindView(R.id.llSignUp) LinearLayout llSignUp;
    @BindView(R.id.llWelcome) LinearLayout llWelcome;
    @BindView(R.id.btnSignIn) Button btnSignIn;
    @BindView(R.id.btnSignUp) Button btnSignUp;
    @BindView(R.id.btnSignInGoogle) Button btnSignInGoogle;
    @BindView(R.id.btnSignInFacebook) Button btnSignInFacebook;
    @BindView(R.id.btnSignUpGoogle) Button btnSignUpGoogle;
    @BindView(R.id.btnSignUpFacebook) Button btnSignUpFacebook;
    @BindView(R.id.btnWelcomeSignUp) Button btnWelcomeSignUp;
    @BindView(R.id.btnWelcomeSignIn) Button btnWelcomeSignIn;

    private String mProviderId;
    private boolean mIsWelcomeVisible = true;

    public static void start (Activity activity){
        Intent intent=new Intent(activity,WelcomeActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }



    @Override
    public void setListeners() {
        ibBack.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        btnSignInGoogle.setOnClickListener(this);
        btnSignInFacebook.setOnClickListener(this);
        btnWelcomeSignUp.setOnClickListener(this);
        btnWelcomeSignIn.setOnClickListener(this);
        btnSignUpFacebook.setOnClickListener(this);
        btnSignUpGoogle.setOnClickListener(this);
    }

    @Override
    public void prepareUI() {

        llSignIn.setVisibility(View.GONE);
        llSignUp.setVisibility(View.GONE);
        ibBack.setVisibility(View.GONE);
    }


    @Override
    public void onBackPressed() {
        if (mIsWelcomeVisible) {
            super.onBackPressed();
        } else {
            mIsWelcomeVisible = true;
            llSignIn.setVisibility(View.GONE);
            llSignUp.setVisibility(View.GONE);
            llWelcome.setVisibility(View.VISIBLE);
            ibBack.setVisibility(View.GONE);
        }
    }


    @Override
    public void onClick(View view) {
        if (view == btnWelcomeSignUp) {
            mIsWelcomeVisible = false;
            llSignUp.setVisibility(View.VISIBLE);
            ibBack.setVisibility(View.VISIBLE);
            llWelcome.setVisibility(View.GONE);
        } else if (view == btnWelcomeSignIn) {
            mIsWelcomeVisible = false;
            llSignIn.setVisibility(View.VISIBLE);
            ibBack.setVisibility(View.VISIBLE);
            llWelcome.setVisibility(View.GONE);
        } else if (view == ibBack) {
            onBackPressed();
        }
    }

}




