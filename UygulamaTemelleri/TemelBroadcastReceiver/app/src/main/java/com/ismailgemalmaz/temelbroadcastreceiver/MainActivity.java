package com.ismailgemalmaz.temelbroadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),InnerBroadcastClass.class);
                Bundle bundle=new Bundle();
                bundle.putString("name","ismail");
                bundle.putInt("age",20);
                intent.putExtras(bundle);
                sendBroadcast(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),NotInnerBroadcastClass.class);

                sendBroadcast(intent);
            }
        });
    }

    public static class InnerBroadcastClass extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

           Bundle bundle= intent.getExtras();
          String name= bundle.getString("name");
          int age=bundle.getInt("age");
            Toast.makeText(context,"iç sınıf "+" isim :"+name+" yaş : "+ age,Toast.LENGTH_LONG).show();
        }
    }
}