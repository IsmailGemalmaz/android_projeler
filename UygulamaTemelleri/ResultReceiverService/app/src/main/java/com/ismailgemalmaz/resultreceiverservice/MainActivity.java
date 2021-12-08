package com.ismailgemalmaz.resultreceiverservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText textName;
    Button btnDownload;
    TextView textWebInfo;
    Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ResultReceiver myResultReceiver=new MyResultReceiver(null);
        textName=findViewById(R.id.textName);
        textWebInfo=findViewById(R.id.textWebInfo);
        btnDownload=findViewById(R.id.btnDownload);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userUrl=textName.getText().toString();
                Intent intent=new Intent(getApplicationContext(),DownloadClass.class);
                intent.putExtra("receiver",myResultReceiver);
                textWebInfo.setText("loading...");
                intent.putExtra("url",userUrl);
                startService(intent);
            }
        });
    }

    public class MyResultReceiver extends ResultReceiver{

        /**
         * Create a new ResultReceive to receive results.  Your
         * {@link #onReceiveResult} method will be called from the thread running
         * <var>handler</var> if given, or from an arbitrary thread if null.
         *
         * @param handler
         */
        public MyResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            if(resultCode==1&&resultData!=null){
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                       String result=resultData.getString("websiteresult");

                       textWebInfo.setText(result);
                    }
                });
            }
            super.onReceiveResult(resultCode, resultData);
        }
    }

}