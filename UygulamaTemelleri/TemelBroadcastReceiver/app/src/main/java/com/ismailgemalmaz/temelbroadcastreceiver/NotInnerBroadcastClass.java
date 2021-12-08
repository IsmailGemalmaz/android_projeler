package com.ismailgemalmaz.temelbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotInnerBroadcastClass extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"dış sınıf",Toast.LENGTH_LONG).show();

    }
}
