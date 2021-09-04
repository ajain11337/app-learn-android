package com.ajain11337.base.lifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ajain11337.base.R
import com.ajain11337.base.multithreading.IntentServiceRunner
import com.ajain11337.base.multithreading.ServiceRunner
import com.ajain11337.base.utils.Constants

class SecondActivity : AppCompatActivity() {

    private val TAG = "SecondActivity"
    private val serviceIntent = Intent(this@SecondActivity, ServiceRunner::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(Constants.LifecycleDebug, "onCreate: $TAG")
        setContentView(R.layout.activity_second)
        addFragment()
        //replaceFragment()
        startServiceRunner()
        startIntentServiceRunner()
    }

    override fun onStart() {
        super.onStart()
        Log.d(Constants.LifecycleDebug, "onStart: $TAG")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(Constants.LifecycleDebug, "onRestart: $TAG")
    }

    override fun onResume() {
        super.onResume()
        Log.d(Constants.LifecycleDebug, "onResume: $TAG")
    }

    override fun onPause() {
        super.onPause()
        Log.d(Constants.LifecycleDebug, "onPause: $TAG")
    }

    override fun onStop() {
        super.onStop()
        Log.d(Constants.LifecycleDebug, "onStop: $TAG")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(Constants.LifecycleDebug, "onDestroy: $TAG")
        stopServiceRunner()
        stopIntentServiceRunner()
    }

    /**
     * Source : https://stackoverflow.com/questions/5159982/how-do-i-add-a-fragment-to-an-activity-with-a-programmatically-created-content-v#comment5796720_5161143
     */
    private fun addFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, SecondFragment.newInstance("", ""))
            //.addToBackStack(null)
            .commit()
    }

    private fun replaceFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, SecondFragment.newInstance("", ""))
            .addToBackStack(null)
            .commit()
    }

    private fun startServiceRunner(){
        baseContext.startService(Intent(this@SecondActivity, ServiceRunner::class.java))
    }

    private fun stopServiceRunner(){
        baseContext.stopService(Intent(this@SecondActivity, ServiceRunner::class.java))
    }

    private fun startIntentServiceRunner(){
        baseContext.startService(Intent(this@SecondActivity, IntentServiceRunner::class.java))
    }

    private fun stopIntentServiceRunner(){
        baseContext.stopService(Intent(this@SecondActivity, IntentServiceRunner::class.java))
    }
}