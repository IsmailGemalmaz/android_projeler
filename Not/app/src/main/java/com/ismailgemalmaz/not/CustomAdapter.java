package com.ismailgemalmaz.not;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class CustomAdapter extends ArrayAdapter<InformationModel> {

    Context context ;
    ArrayList<InformationModel> informationList;


    public CustomAdapter(@NonNull Context context, @NonNull ArrayList<InformationModel> informationList) {
        super(context,R.layout.row_layout, informationList);
        this.context=context;
        this.informationList=informationList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView textBaseDescription,textBaseDate;
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
       View customView= inflater.inflate(R.layout.row_layout,parent,false);

                textBaseDescription=customView.findViewById(R.id.textBaseDescription);
                textBaseDescription.setText(informationList.get(position).Descripton);

                textBaseDate=customView.findViewById(R.id.textBaseDate);
                textBaseDate.setText(informationList.get(position).Date);


        return customView;
    }
}
