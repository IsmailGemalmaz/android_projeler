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

public class SignİInInfoFragment extends Fragment {
    Button btnBack;

  public static SignİInInfoFragment newInstance(){
      return new SignİInInfoFragment();
  }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.sign_in_info,container,false);
      btnBack=  rootView.findViewById(R.id.btnBackScreen);

        buttonOnclick();
        return rootView;
    }

    private void buttonOnclick(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

                BaseLoginFragment baseLoginFragment=BaseLoginFragment.newInstance();
                fragmentTransaction.add(R.id.LoginProcses,baseLoginFragment).commit();
            }
        });
    }
}
