package com.ismailgemalmaz.kriptoparam.ui.dashboard;


import android.content.Context;

import android.content.Intent;
import android.os.Handler;
import android.text.NoCopySpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ismailgemalmaz.kriptoparam.BaseActivity;
import com.ismailgemalmaz.kriptoparam.R;
import com.ismailgemalmaz.kriptoparam.SendActivity;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.logging.FileHandler;
import java.util.logging.LogRecord;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RowHolder> {

    ArrayList<CryptoModel> cryptoModels;
    Context context;
    FirebaseFirestore firestore=FirebaseFirestore.getInstance();
    TextView textCurrency, textPrice;
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    FirebaseUser user=firebaseAuth.getCurrentUser();
    public RecyclerViewAdapter(ArrayList<CryptoModel> cryptoModels, Context context) {
        this.cryptoModels = cryptoModels;
        this.context = context;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_view_crypto, parent, false);

        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {

        holder.bind(cryptoModels.get(position));

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {

                if (isLongClick) {
                    Toast.makeText(context, "Long Click" + cryptoModels.get(position), Toast.LENGTH_LONG).show();
                } else {

                    TextView textDialogPrice;

                    final BottomSheetDialog dialog = new BottomSheetDialog(context, R.style.Dialog);
                    View bottomSheetView = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.dialog, (LinearLayout) view.findViewById(R.id.dialogLinearLayout));
                    textDialogPrice = bottomSheetView.findViewById(R.id.textDialogPrice);

                    textDialogPrice.setText(cryptoModels.get(position).getPrice() + "");

                    bottomSheetView.findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String currncy = cryptoModels.get(position).getCurrency();
                            double price = cryptoModels.get(position).getPrice();
                            Object userEposta=user.getEmail();
                           HashMap<String,Object> cryptoMoney=new HashMap<>();
                           cryptoMoney.put("currency",currncy);
                           cryptoMoney.put("price",price);
                           cryptoMoney.put("ePosta",userEposta);
                           cryptoMoney.put("date",FieldValue.serverTimestamp());

                            firestore.collection("CryptoMoney").add(cryptoMoney).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(context,"SATIN ALINDI",Toast.LENGTH_LONG).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(context,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                                }
                            });

                        }
                    });
                    bottomSheetView.findViewById(R.id.detail).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    bottomSheetView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    dialog.setContentView(bottomSheetView);
                    dialog.show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {

        return cryptoModels.size();
    }


    class RowHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private ItemClickListener itemClickListener;


        public RowHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }


        public void bind(CryptoModel cryptoModel) {

            textCurrency = itemView.findViewById(R.id.textCurrncy);
            textPrice = itemView.findViewById(R.id.textPrice);
            textCurrency.setText(cryptoModel.currency);
            textPrice.setText(cryptoModel.price + " $");

        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {

            itemClickListener.onClick(v, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), true);
            return true;
        }
    }
}



