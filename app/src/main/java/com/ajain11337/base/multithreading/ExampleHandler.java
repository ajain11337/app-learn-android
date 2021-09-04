package com.ajain11337.base.multithreading;

import static java.lang.Thread.currentThread;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import com.ajain11337.base.utils.Constants;

public class ExampleHandler extends Handler {

    public static final int TASK_A = 1;
    public static final int TASK_B = 2;

    /**
     * Sequential execution of handleMessage, one thread & one message queue;
     */
    @Override
    public void handleMessage(@NonNull Message msg) {
        switch (msg.what){
            case TASK_A:
                Log.d(Constants.ExampleHandler, "run:" + TASK_A  + " "+ currentThread().getName());
                break;
            case TASK_B:
                Log.d(Constants.ExampleHandler, "run:" + TASK_B  + " "+ currentThread().getName());
                break;
        }
    }
}
