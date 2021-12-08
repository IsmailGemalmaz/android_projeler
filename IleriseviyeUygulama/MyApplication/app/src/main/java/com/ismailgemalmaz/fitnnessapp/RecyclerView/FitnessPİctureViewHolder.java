package com.ismailgemalmaz.fitnnessapp.RecyclerView;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ismailgemalmaz.fitnnessapp.FitnessMove;
import com.ismailgemalmaz.fitnnessapp.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class FitnessPİctureViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    ProgressBar progressBar;
    public FitnessPİctureViewHolder(@NonNull View itemView) {
        super(itemView);
       imageView= itemView.findViewById(R.id.recycelrview_fitness_pictures_list_imageview);
      progressBar=  itemView.findViewById(R.id.recyeclerview_fitniss_pictures_list_progreebar);
    }

    //imageview içine atama yaptık
    public void getMove(Context context, FitnessMove fitnessMove){
        //oncilck için verilen tag
        itemView.setTag(fitnessMove);

        Picasso.get().load(fitnessMove.getFitnessPicture()).fit().centerCrop().into(imageView, new Callback() {
            @Override
            public void onSuccess() {

                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
}
