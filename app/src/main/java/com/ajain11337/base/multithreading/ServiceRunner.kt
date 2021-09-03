package com.ajain11337.base.multithreading

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.ajain11337.base.utils.Constants
import android.media.MediaPlayer
import android.provider.Settings

class ServiceRunner : Service() {

    private var player: MediaPlayer? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(Constants.ServiceRunner, "onStartCommand " + Thread.currentThread().name)
        player = MediaPlayer.create( this, Settings.System.DEFAULT_RINGTONE_URI );
        player?.isLooping = true;
        player?.start();
        return START_NOT_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        Log.d(Constants.ServiceRunner, "onBind " + Thread.currentThread().name)
        return null
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(Constants.ServiceRunner, "onUnbind " + Thread.currentThread().name)
        return super.onUnbind(intent)
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Log.d(Constants.ServiceRunner, "onRebind " + Thread.currentThread().name)
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(Constants.ServiceRunner, "onCreate " + Thread.currentThread().name)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(Constants.ServiceRunner, "onDestroy " + Thread.currentThread().name)
        player?.stop()
    }
}