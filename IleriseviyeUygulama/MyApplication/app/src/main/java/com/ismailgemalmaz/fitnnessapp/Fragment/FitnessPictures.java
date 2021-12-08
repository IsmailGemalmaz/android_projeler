package com.ismailgemalmaz.fitnnessapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ismailgemalmaz.fitnnessapp.FitnessMove;
import com.ismailgemalmaz.fitnnessapp.PopupActivity;
import com.ismailgemalmaz.fitnnessapp.R;
import com.ismailgemalmaz.fitnnessapp.RecyclerView.FitnessPicturesAdapter;

import java.util.ArrayList;

public class FitnessPictures extends Fragment implements FitnessPicturesAdapter.Mylistener {

    private FitnessPicturesAdapter fitnessPicturesAdapter;
    private RecyclerView recyclerView;
    private ArrayList<FitnessMove> fitnessMoves;

    //constracter
    public static FitnessPictures newInstance(){

        return new FitnessPictures();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_fitness_pictures,container,false);
        fitnessPicturesAdapter=new FitnessPicturesAdapter((AppCompatActivity) getActivity(),this);

        //yeni nesne oluşturmadan adapterda yazdığımız fonksiyonla iimizi hallettik
        fitnessMoves=fitnessPicturesAdapter.getFitnessMoves();
        recyclerView=rootView.findViewById(R.id.fragment_fitness_pictures_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
          //faragment adapte a eklendimi kontrolü
        if(isAdded()){
            recyclerView.setAdapter(fitnessPicturesAdapter);
        }

        getFitnessMoves(fitnessMoves);
        return rootView;
    }

    private ArrayList<FitnessMove> getFitnessMoves(ArrayList<FitnessMove> fitnessMoves){
        for(int i=0;i<16;i++){
            fitnessMoves.add(new FitnessMove("Fitnes move name "+i,"https://www.kendinigelistirmevakti.com/wp-content/uploads/2018/09/beslenme-program%C4%B1-haz%C4%B1rlama-1" +".jpg",
                    "Fitnesmove description",100));

        }
        return fitnessMoves;
    }

    @Override
    public void MyListener(FitnessMove fitnessMove) {
        Toast.makeText(getContext(),fitnessMove.getFitnessName(),Toast.LENGTH_LONG).show();


        Intent intent= PopupActivity.newIntent(getActivity(),fitnessMove);
        startActivity(intent);
    }
}
