package com.ismailgemalmaz.sanalparam.NavigationFragment.InvestmentFragment.RecyclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.ismailgemalmaz.sanalparam.CryptoMoneyService.CryptoModel;
import com.ismailgemalmaz.sanalparam.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class InvestmentCryptoRecyclerAdapter extends RecyclerView.Adapter<InvestmentCryptoRecyclerAdapter.RecyclerViewHolder> implements View.OnClickListener{

    //DEĞİŞKENLERİ ATAYIP CONSTRACTER DA BUNLARI ALDIK
    ArrayList<CryptoModel> cryptoModels;
    Context context;
    CryptoMyListener  cryptoMyListener;//DOKUNDUĞUMUZDA NEYE BASTIĞIMIZI ALGILAMASI İÇİN YAZDIĞIMIZ İNTERFACE TANIMLADIK

    public InvestmentCryptoRecyclerAdapter(ArrayList<CryptoModel> cryptoModels, Context context,CryptoMyListener cryptoMyListener) {
        this.cryptoModels = cryptoModels;
        this.context = context;
        this.cryptoMyListener=cryptoMyListener;
    }

    public ArrayList<CryptoModel> getCryptoModels(){
        return cryptoModels;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View rootView=inflater.inflate(R.layout.investment_crypto_recycler_adapter,parent,false);
        rootView.setOnClickListener(this);//TIKLANMA OLAYINI GÖRÜNÜME VERDK
        return new RecyclerViewHolder(rootView);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.bind(cryptoModels.get(position));//BAĞLANDIĞINDA NE OLUCAĞINI BURADA YAZDIK
    }


    @Override
    public int getItemCount() {

        return cryptoModels.size();
    }

    @Override
    public void onClick(View v) {
       CryptoModel cryptoModel=(CryptoModel) v.getTag();//
       cryptoMyListener.MyListiner(cryptoModel);
    }

    //------------------filter------------------------------
    public void filterList(ArrayList<CryptoModel> filterdList){
        cryptoModels=filterdList;
        notifyDataSetChanged();
    }
    //----------------------------------------------



    //view holder
    class RecyclerViewHolder extends RecyclerView.ViewHolder{

        TextView currencyText,priceText;
        ImageView cryptoImageView;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @RequiresApi(api = Build.VERSION_CODES.P)
        public void bind(CryptoModel cryptoModel){

            itemView.setTag(cryptoModel);//TIKLAMA OLAYI İÇİN BURADA BUNU YAZIP ONCLİCK İÇİNDE BUNU ALDIK

            currencyText=itemView.findViewById(R.id.crypto_currency_text);
            priceText=itemView.findViewById(R.id.crypto_price_text);
            cryptoImageView=itemView.findViewById(R.id.crypto_image_text);
            currencyText.setText(cryptoModel.name+"("+cryptoModel.currency+")");
            priceText.setText(cryptoModel.price +" $");
            Uri uri=Uri.parse(cryptoModel.logo_url);
            Picasso.get().load(uri).into(cryptoImageView);
        }
    }

    public interface CryptoMyListener{
        void MyListiner(CryptoModel cryptoModel);
    }

}
