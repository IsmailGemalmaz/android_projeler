package com.ismailgemalmaz.fitnnessapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ismailgemalmaz.fitnnessapp.R;

public class Details extends Fragment {

    //constarcter
    public static Details newInstance(){

        return new Details();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_deatails,container,false);
        return rootView;
    }
}
