package com.ajain11337.base.multithreading

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.ajain11337.base.utils.Constants

/**
 * Link : https://www.zoftino.com/android-intentservice-example
 */
class IntentServiceRunner : IntentService("IntentService") {
    override fun onCreate() {
        super.onCreate()
        Log.d(Constants.IntentServiceRunner, "onCreate " + Thread.currentThread().name)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Log.d(Constants.IntentServiceRunner, "onStartCommand " + Thread.currentThread().name)
        return START_NOT_STICKY
    }

    override fun onHandleIntent(p0: Intent?) {
        Log.d(Constants.IntentServiceRunner, "onHandleIntent " + Thread.currentThread().name)
        Thread.sleep(5000)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(Constants.IntentServiceRunner, "onDestroy " + Thread.currentThread().name)
    }
}