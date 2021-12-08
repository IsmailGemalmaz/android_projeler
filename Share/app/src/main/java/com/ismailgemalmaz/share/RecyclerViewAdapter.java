package com.ismailgemalmaz.share;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.PostHolder> {
    ArrayList<String> epostaFireBase;
    ArrayList<String> commnetFireBase;
    ArrayList<String> imageFireBase;

    public RecyclerViewAdapter(ArrayList<String> epostaFireBase, ArrayList<String> commnetFireBase, ArrayList<String> imageFireBase) {
        this.epostaFireBase = epostaFireBase;
        this.commnetFireBase = commnetFireBase;
        this.imageFireBase = imageFireBase;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
       View view= inflater.inflate(R.layout.recycler_layout,parent,false);
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        holder.recyclerViewEposta.setText(epostaFireBase.get(position));
        holder.recyclerViewComment.setText(commnetFireBase.get(position));
        Picasso.get().load(imageFireBase.get(position)).into(holder.recyclerViewImage);

    }

    @Override
    public int getItemCount() {
        return epostaFireBase.size();
    }

    class PostHolder extends RecyclerView.ViewHolder{
        TextView recyclerViewEposta,recyclerViewComment;
        ImageView recyclerViewImage;
        public PostHolder(@NonNull View itemView){
            super(itemView);
            recyclerViewEposta=itemView.findViewById(R.id.rcyclerViewEposta);
            recyclerViewComment=itemView.findViewById(R.id.rcyclerViewCommnet);
            recyclerViewImage=itemView.findViewById(R.id.rcyclerViewImage);
        }
    }
}
