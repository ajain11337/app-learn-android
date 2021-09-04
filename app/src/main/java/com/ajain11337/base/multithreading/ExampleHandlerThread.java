package com.ajain11337.base.multithreading;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;

/**
 * HandlerThread extends Thread;
 * Has a looper & messageQueue already attached;
 * Similar to thread, can be used only once, has to be reinitialised;
 */
public class ExampleHandlerThread extends HandlerThread {

    private Handler handler;

    public ExampleHandlerThread(String name) {
        super(name);
    }

    @SuppressLint("HandlerLeak")
    @Override
    protected void onLooperPrepared() {
        handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                // handling messages here
            }
        };
    }

    public Handler getHandler(){
        return handler;
    }
}
