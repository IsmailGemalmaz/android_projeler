package com.ismailgemalmaz.sanalparam.NavigationFragment.InvestmentFragment.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ismailgemalmaz.sanalparam.ForeginCurrencyService.ForeginCurrencyModel;
import com.ismailgemalmaz.sanalparam.R;

import java.util.ArrayList;

public class InvestmentForeginRecyclerAdapter extends RecyclerView.Adapter<InvestmentForeginRecyclerAdapter.ViewHolder> {

    ArrayList<ForeginCurrencyModel> currencyModels;
    Context context;

    public InvestmentForeginRecyclerAdapter(ArrayList<ForeginCurrencyModel> currencyModels, Context context) {
        this.currencyModels = currencyModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View rootView=inflater.inflate(R.layout.investment_foregin_recycler_adapter,parent,false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(currencyModels.get(position));
    }

    @Override
    public int getItemCount() {
        return currencyModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView buyingText,selesText,changeText,currencyText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(ForeginCurrencyModel currencyModel){
          buyingText= itemView.findViewById(R.id.foregin_buying_text);
          selesText=itemView.findViewById(R.id.foregin_seles_text);
          changeText=itemView.findViewById(R.id.foregin_change_text);
          //currencyText=itemView.findViewById(R.id.foregin_currency);

          buyingText.setText(currencyModel.Alış+" ₺");
          selesText.setText(currencyModel.Satış+" ₺");
          changeText.setText(currencyModel.Tür +" %");
         // currencyText.setText(currencyModel.currency);
        }
    }
}
