package com.ismailgemalmaz.kriptopara.adapter;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textclassifier.TextLanguage;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.ComponentActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ismailgemalmaz.kriptopara.R;
import com.ismailgemalmaz.kriptopara.UserActivity;
import com.ismailgemalmaz.kriptopara.model.CryptoModel;
import com.ismailgemalmaz.kriptopara.view.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RowHolder > {


    private ArrayList<CryptoModel> cryptoLİst;
    private Context context;
    private Class aClass;
    private String[] colors={"#002fa7","#394393","#4a577f","#516b6a","#507f54","#489338","#35a700"};
    ArrayList<CryptoModel> cryptoModelFull;


    public RecyclerViewAdapter(ArrayList<CryptoModel> cryptoLİst,Context context, Class aClass)
    {
        this.context=context;
        this.cryptoLİst = cryptoLİst;
        cryptoModelFull=new ArrayList<>(cryptoLİst);
        this.aClass=aClass;

    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
       View view= inflater.inflate(R.layout.row_layout,parent,false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        holder.bind(cryptoLİst.get(position),colors,position);


    }


    @Override
    public int getItemCount() {

        return cryptoLİst.size();
    }



        class RowHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textName;
        TextView textPrice;
        LinearLayout linearLayout;
        public RowHolder(@NonNull View itemView) {
            super(itemView);

        }

        public void bind(CryptoModel cryptoModel,String [] colors,Integer position){


           itemView.setBackgroundColor(Color.parseColor(colors[position % 7]));
            textName=itemView.findViewById(R.id.textName);
            textPrice=itemView.findViewById(R.id.textPrice);
            linearLayout=itemView.findViewById(R.id.linearLayout);
            textName.setText("İSİM : "+cryptoModel.currency);
            textPrice.setText("FİYAT : "+cryptoModel.price+"$");


            linearLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {


        }
    }


}
