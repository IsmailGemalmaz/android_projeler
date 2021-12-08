package com.ismailgemalmaz.kriptoparam.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.protobuf.Internal;
import com.ismailgemalmaz.kriptoparam.R;
import com.ismailgemalmaz.kriptoparam.ui.dashboard.CryptoModel;
import com.ismailgemalmaz.kriptoparam.ui.dashboard.ItemClickListener;
import com.ismailgemalmaz.kriptoparam.ui.dashboard.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeFragmentAdapter extends RecyclerView.Adapter<HomeFragmentAdapter.CryptoHolder> {
    ArrayList<String> arrayListCurrency;
    ArrayList<Double> arrayListPrice;
    ArrayList<Object> arrayEposta;
    Context context;
    FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();


    public HomeFragmentAdapter ( ArrayList<String> arrayListCurrency, ArrayList<Double> arrayListPrice, ArrayList<Object> arrayEposta,Context context){
        this.arrayListCurrency=arrayListCurrency;
        this.arrayListPrice=arrayListPrice;
        this.arrayEposta=arrayEposta;

        this.context=context;
    }

    @NonNull
    @Override
    public CryptoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
       View view= inflater.inflate(R.layout.home_listview_row_view,parent,false);
        return new CryptoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CryptoHolder holder, int position) {
        holder.textHomeCurrency.setText(arrayListCurrency.get(position));
        holder.textHomePrice.setText(arrayListPrice.get(position)+"");
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {


                  firebaseFirestore.collection("Crypto Money").document("").delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                      @Override
                      public void onSuccess(Void aVoid) {
                          Toast.makeText(context,"silindi",Toast.LENGTH_LONG).show();
                      }
                  });

            }
        });
    }

    @Override
    public int getItemCount()
    {

        return arrayListCurrency.size();
    }


    class  CryptoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textHomeCurrency,textHomePrice;

        private ItemClickListener itemClickListener;
        public CryptoHolder(@NonNull View itemView) {
            super(itemView);
            textHomeCurrency= itemView.findViewById(R.id.textHomeCurrency);
           textHomePrice= itemView.findViewById(R.id.textHomePrice);
           itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener=itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),false);
        }
    }

    }

