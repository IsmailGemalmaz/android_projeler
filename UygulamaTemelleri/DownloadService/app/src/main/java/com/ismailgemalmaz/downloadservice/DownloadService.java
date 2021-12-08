package com.ismailgemalmaz.downloadservice;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.concurrent.ExecutionException;

public class DownloadService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AsyncTaskDownLoad asyncTaskDownLoad=new AsyncTaskDownLoad();
        try {
            asyncTaskDownLoad.execute("https://www.tcmb.gov.tr/kurlar/today.xml").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    ///////////////////////asyynctask sınıfı///////////////////////////////////////
    class AsyncTaskDownLoad extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            String result="";//sonuçları buraya kaydedeceğiz

            URL url;
            HttpURLConnection httpURLConnection=null;//Url ile http mizi bağlayacak olan kod

            try {
                url=new URL(strings[0]);//oen baştan başlasın diye doınbackground metodunun parametresini verdik
                httpURLConnection=(HttpURLConnection) url.openConnection();//basğlama işlemi yapıldı

                InputStream inputStream=httpURLConnection.getInputStream();//nerdeki akışı alıcapğını verdik
                InputStreamReader streamReader=new InputStreamReader(inputStream);//sonucu okuması içn verdik

                int data=streamReader.read();//veri int olarak geliyor

                //veriyi döngüe sokup int olan veriyi karaktere dönüştreceğiz
                while(data!=-1){

                    char current=(char) data;//int olan datayı char a cast ettik
                    result+=current;//yukarda tanımladığımız strng e datayı(current) verdik
                    data=streamReader.read();//bir sonrakine gitmek için yaptık
                    //Data bitene kadar yapacak
                }

            }catch (Exception e){
               return  "faild";
            }

            return result;
        }

        //doinbackground verdiğimiz son string aslında sonucu temsil ediyor ve o sunuc parametresi bu methodu referans alır
        @Override
        protected void onPostExecute(String s) {
            System.out.println("sonuç :"+s);
            super.onPostExecute(s);
        }
    }
}
