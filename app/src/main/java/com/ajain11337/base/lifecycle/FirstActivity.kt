package com.ajain11337.base.lifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.ajain11337.base.R
import com.ajain11337.base.utils.Constants

class FirstActivity : AppCompatActivity() {
    private val TAG = "FirstActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(Constants.LifecycleDebug, "onCreate: $TAG")
        setContentView(R.layout.activity_first)
        addFragment()
        //replaceFragment()
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
    }

    /**
     * Case 1 -> Activity A to Activity B
     * Case 2 -> Activity A to Activity B, finishing A
     * Case 3 -> Activity + Fragment A to Activity + Fragment B
     * Case 4 -> Activity + Fragment A to Activity + Fragment B, finishing A
     */
    fun openSecondActivity(view: View) {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun addFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, FirstFragment.newInstance("", ""))
            .addToBackStack(null)
            .commit()
    }

    private fun replaceFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, FirstFragment.newInstance("", ""))
            .addToBackStack(null)
            .commit()
    }
}