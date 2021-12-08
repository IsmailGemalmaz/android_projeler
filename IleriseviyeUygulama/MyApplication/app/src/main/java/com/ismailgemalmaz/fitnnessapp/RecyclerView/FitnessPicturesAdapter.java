package com.ismailgemalmaz.fitnnessapp.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ismailgemalmaz.fitnnessapp.FitnessMove;
import com.ismailgemalmaz.fitnnessapp.R;

import java.util.ArrayList;

public class FitnessPicturesAdapter extends RecyclerView.Adapter<FitnessPİctureViewHolder> implements View.OnClickListener {

    private ArrayList<FitnessMove> fitnessMoves;
    private LayoutInflater layoutInflater;
    private AppCompatActivity appCompatActivity;
    private Mylistener mylistener;

    public FitnessPicturesAdapter(AppCompatActivity appCompatActivity, Mylistener mylistener) {
        this.appCompatActivity = appCompatActivity;
        this.mylistener = mylistener;
        layoutInflater=appCompatActivity.getLayoutInflater();
        fitnessMoves=new ArrayList<>();
    }

//fragmentta birdaha yeni nesne oluşturmamak için yazıdık
    public ArrayList<FitnessMove> getFitnessMoves(){

        return fitnessMoves;
    }

    @NonNull
    @Override
    public FitnessPİctureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView=layoutInflater.inflate(R.layout.recycler_view_fitness_pictures_list,parent,false);
        rootView.setOnClickListener(this);
        return new FitnessPİctureViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull FitnessPİctureViewHolder holder, int position) {
        holder.getMove(appCompatActivity,fitnessMoves.get(position));
    }

    @Override
    public int getItemCount() {
        return fitnessMoves.size();
    }

    @Override
    public void onClick(View v) {

        //kullanılan görünüm fitnismove a aitmi değilimi kontrolü
        if(v.getTag() instanceof FitnessMove){
            FitnessMove fitnessMove=(FitnessMove) v.getTag();
            mylistener.MyListener(fitnessMove);
        }

    }

    //kendi listenermızı oluşturuyoruz
    public interface Mylistener{
        void MyListener(FitnessMove fitnessMove);
    }
}
