package com.ismailgemalmaz.sanalparam.NavigationFragment.SettingsFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ismailgemalmaz.sanalparam.R;

public class SettingsFragment extends Fragment {

 public static SettingsFragment newInsance(){

     return new SettingsFragment();
 }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.settings_fragment,container,false);
        return rootView;
    }
}
