package com.ismailgemalmaz.resultreceiverservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadClass extends IntentService {
    private static final String TAG = "DownloadClass";
    public DownloadClass() {
        super("DownloadClass");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String currenThread=Thread.currentThread().getName();
        Log.d(TAG, "onHandleIntent: "+currenThread);

        String userInput=intent.getStringExtra("url");
        ResultReceiver resultReceiver=intent.getParcelableExtra("receiver");

        String result="";
        URL url;
        HttpURLConnection httpURLConnection=null;

        try {

            url=new URL(userInput);
            httpURLConnection=(HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            InputStreamReader streamReader=new InputStreamReader(inputStream);

             int data=streamReader.read();
             while (data!=-1){
                 char current=(char) data;
                 result +=current;
                 data= streamReader.read();
             }

             Bundle bundle=new Bundle();//bilgi bohçası
             bundle.putString("websiteresult",result);//sonucumuzu aktarmak için kullandık
            resultReceiver.send(1,bundle);

        }catch (Exception e){
            Bundle bundle=new Bundle();//bilgi bohçası
            bundle.putString("websiteresult","error!");//sonucumuzu aktarmak için kullandık
            resultReceiver.send(1,bundle);

        }
    }

    @Override
    public void onCreate() {
        String currenThread=Thread.currentThread().getName();
        Log.d(TAG, "onCreate: "+currenThread);
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        String currenThread=Thread.currentThread().getName();
        Log.d(TAG, "onDestroy: "+currenThread);
        super.onDestroy();
    }
}
