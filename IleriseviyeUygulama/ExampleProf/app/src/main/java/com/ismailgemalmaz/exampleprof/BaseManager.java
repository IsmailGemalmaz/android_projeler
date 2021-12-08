package com.ismailgemalmaz.exampleprof;

import android.content.Intent;

public abstract class BaseManager {
    public BaseManagerActivity activity;

    public void onCreated() {}

    public void onStarted() {}

    public void onResumed() {}

    public void onLayoutReady() {}

    public void onPaused() {}

    public void onStopped() {}

    public void onDestroyed() {}

    public void onActivityResult(int requestCode, int resultCode, Intent data) {}

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {}

    public BaseManager(BaseManagerActivity activity) {
        this.activity = activity;

    }

    public final void destroy() {
        activity = null;

    }



}
