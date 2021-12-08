package com.ismailgemalmaz.sanalparam;

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

public class BaseLoginFragment extends Fragment {
    Button btnSignIn,btnSignUp;

    public static BaseLoginFragment newInstance(){
        return new BaseLoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.base_login_fragment,container,false);
        btnSignIn=rootView.findViewById(R.id.btnSignIn);
        btnSignUp=rootView.findViewById(R.id.btnSignUp);
       buttonOnclick();


        return rootView;
    }

    private void buttonOnclick(){
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

                SignİInInfoFragment signİInInfoFragment=SignİInInfoFragment.newInstance();
                fragmentTransaction.add(R.id.LoginProcses,signİInInfoFragment).commit();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

                SignUpfoFragment signUpfoFragment=SignUpfoFragment.newInsatance();
                fragmentTransaction.add(R.id.LoginProcses,signUpfoFragment).commit();
            }
        });
    }
}
