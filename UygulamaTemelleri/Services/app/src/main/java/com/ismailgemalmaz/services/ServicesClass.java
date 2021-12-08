package com.ismailgemalmaz.services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class ServicesClass extends Service {

    private static final String TAG = "ServicesClass";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onCreate() {
        String currentThread=Thread.currentThread().getName();
        Log.d(TAG, "onCreate: "+currentThread);
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        String currentThread=Thread.currentThread().getName();
        Log.d(TAG, "onDestroy: "+currentThread);
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String currentThread=Thread.currentThread().getName();
        Log.d(TAG, "onStartCommand: "+currentThread);

        /*
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        //AsyncTask işelminde nekadar uyuyacağını burda söyleyip doinbackground methodunda bunu karşılayacağız
        AsyncTaskClass asyncTaskClass=new AsyncTaskClass();
        asyncTaskClass.execute(10000);

        return super.onStartCommand(intent, flags, startId);
    }

    class AsyncTaskClass extends AsyncTask<Integer, View,View>{

        private static final String TAG = "AsyncTaskClass";
        //kullanılmıyan
        @Override
        protected View doInBackground(Integer... integers) {
            String currentThread=Thread.currentThread().getName();
            Log.d(TAG, "doInBackground: "+currentThread);

            int sleeptime=integers[0];

            try {
                Thread.sleep(sleeptime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        //doinbackgound işlemi yapılmadan önce yapılacaklar
        @Override
        protected void onPreExecute() {
            String currentThread=Thread.currentThread().getName();
            Log.d(TAG, "onPreExecute: "+currentThread);
            super.onPreExecute();
        }

        //doinbackgound işlemi yapıldıktan sonra yapılacaklar
        //aldığımız sonuçları ana ekrana nasıl aktarırız o işlemi yaptığımız
        @Override
        protected void onPostExecute(View view) {
            String currentThread=Thread.currentThread().getName();
            Log.d(TAG, "onPostExecute: "+currentThread);
            super.onPostExecute(view);
        }

        //do in background işlemi yapıklırken yapılacaklar
        @Override
        protected void onProgressUpdate(View... values) {
            String currentThread=Thread.currentThread().getName();
            Log.d(TAG, "onProgressUpdate: "+currentThread);
            super.onProgressUpdate(values);
        }
    }
}
