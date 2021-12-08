package com.ismailgemalmaz.sanalparam;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SignUpfoFragment extends Fragment {

    public static SignUpfoFragment newInsatance(){
        return new SignUpfoFragment();
    }
    Button btnBack,btnSignUp;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View rootView=inflater.inflate(R.layout.sign_up_info,container,false);
       btnBack=rootView.findViewById(R.id.btnBackScreen);
       btnSignUp=rootView.findViewById(R.id.btnSignUp);
       buttonOncilck();
        return rootView;
    }

    public void buttonOncilck(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

                BaseLoginFragment baseLoginFragment=BaseLoginFragment.newInstance();
                fragmentTransaction.add(R.id.LoginProcses,baseLoginFragment).commit();

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SignUpActivity.class);
                startActivity(intent);
            }
        });

    }
}
