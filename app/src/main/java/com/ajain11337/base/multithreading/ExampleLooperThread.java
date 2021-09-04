package com.ajain11337.base.multithreading;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import com.ajain11337.base.utils.Constants;

/**
 * Link : https://www.youtube.com/watch?v=TN-CGfzvBhc&list=PLrnPJCHvNZuD52mtV8NvazNYIyIVPVZRa&index=2
 * "Can't create handle inside thread, that has not called Looper.prepare()"
 */
public class ExampleLooperThread extends Thread{

    public Handler handler;
    public Looper looper;

    /**
     * Looper.prepare() : Add a looper to this background thread;
     * Also adds a message queue, attached to looper.
     */
    @Override
    public void run() {
        Looper.prepare();

        looper = Looper.myLooper();
        handler = new ExampleHandler();

        Looper.loop(); // Infinite for loop, looping over message queue.

        /*for (int i = 0; i < 5; i++) {
            Log.d(Constants.ExampleLooperThread, "run:" + i  + " "+ currentThread().getName());
            SystemClock.sleep(1000);
        }
        Log.d(Constants.ExampleLooperThread, "End of run() " + currentThread().getName());*/

    }
}
