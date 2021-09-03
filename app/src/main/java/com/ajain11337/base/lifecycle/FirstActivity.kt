package com.ajain11337.base.lifecycle

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.ajain11337.base.R
import com.ajain11337.base.multithreading.AsyncTaskRunner
import com.ajain11337.base.rx.RxUtils
import com.ajain11337.base.utils.Constants

class FirstActivity : AppCompatActivity() {
    private val TAG = "FirstActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(Constants.LifecycleDebug, "onCreate: $TAG")
        setContentView(R.layout.activity_first)
        addFragment()
        //replaceFragment()
        startRxStream()
        startAsyncTask()
        startAsyncTask2()
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
    /**
     * Logs
     * 2021-08-29 14:18:37.548 6053-6053/com.ajain11337.base D/OpenActivityLifecycleDebug: onPause: FirstFragment
     * 2021-08-29 14:18:37.549 6053-6053/com.ajain11337.base D/OpenActivityLifecycleDebug: onPause: FirstActivity
     * 2021-08-29 14:18:37.568 6053-6053/com.ajain11337.base D/OpenActivityLifecycleDebug: onCreate: SecondActivity
     * 2021-08-29 14:18:37.592 6053-6053/com.ajain11337.base D/OpenActivityLifecycleDebug: onAttach: SecondFragment
     * 2021-08-29 14:18:37.592 6053-6053/com.ajain11337.base D/OpenActivityLifecycleDebug: onCreate: SecondFragment
     * 2021-08-29 14:18:37.593 6053-6053/com.ajain11337.base D/OpenActivityLifecycleDebug: onCreateView: SecondFragment
     * 2021-08-29 14:18:37.596 6053-6053/com.ajain11337.base D/OpenActivityLifecycleDebug: onViewCreated: SecondFragment
     * 2021-08-29 14:18:37.597 6053-6053/com.ajain11337.base D/OpenActivityLifecycleDebug: onStart: SecondFragment
     * 2021-08-29 14:18:37.597 6053-6053/com.ajain11337.base D/OpenActivityLifecycleDebug: onStart: SecondActivity
     * 2021-08-29 14:18:37.599 6053-6053/com.ajain11337.base D/OpenActivityLifecycleDebug: onResume: SecondActivity
     * 2021-08-29 14:18:37.599 6053-6053/com.ajain11337.base D/OpenActivityLifecycleDebug: onResume: SecondFragment
     * 2021-08-29 14:18:38.083 6053-6053/com.ajain11337.base D/OpenActivityLifecycleDebug: onStop: FirstFragment
     * 2021-08-29 14:18:38.083 6053-6053/com.ajain11337.base D/OpenActivityLifecycleDebug: onStop: FirstActivity
     * 2021-08-29 14:18:38.086 6053-6053/com.ajain11337.base D/OpenActivityLifecycleDebug: onDestroyView: FirstFragment
     * 2021-08-29 14:18:38.087 6053-6053/com.ajain11337.base D/OpenActivityLifecycleDebug: onDestroy: FirstFragment
     * 2021-08-29 14:18:38.087 6053-6053/com.ajain11337.base D/OpenActivityLifecycleDebug: onDetach: FirstFragment
     * 2021-08-29 14:18:38.087 6053-6053/com.ajain11337.base D/OpenActivityLifecycleDebug: onDestroy: FirstActivity
     */
    fun openSecondActivity(view: View) {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
        //finish()
    }

    /**
     * Logs
     * 2021-08-29 14:15:31.146 6053-6053/com.ajain11337.base D/OpenActivityLifecycleDebug: onCreate: FirstActivity
     * 2021-08-29 14:15:31.173 6053-6053/com.ajain11337.base D/OpenActivityLifecycleDebug: onAttach: FirstFragment
     * 2021-08-29 14:15:31.174 6053-6053/com.ajain11337.base D/OpenActivityLifecycleDebug: onCreate: FirstFragment
     * 2021-08-29 14:15:31.174 6053-6053/com.ajain11337.base D/OpenActivityLifecycleDebug: onCreateView: FirstFragment
     * 2021-08-29 14:15:31.176 6053-6053/com.ajain11337.base D/OpenActivityLifecycleDebug: onViewCreated: FirstFragment
     * 2021-08-29 14:15:31.176 6053-6053/com.ajain11337.base D/OpenActivityLifecycleDebug: onStart: FirstFragment
     * 2021-08-29 14:15:31.176 6053-6053/com.ajain11337.base D/OpenActivityLifecycleDebug: onStart: FirstActivity
     * 2021-08-29 14:15:31.177 6053-6053/com.ajain11337.base D/OpenActivityLifecycleDebug: onResume: FirstActivity
     * 2021-08-29 14:15:31.177 6053-6053/com.ajain11337.base D/OpenActivityLifecycleDebug: onResume: FirstFragment
     */
    private fun addFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, FirstFragment.newInstance("", ""))
            //.addToBackStack(null)
            .commit()
    }

    private fun replaceFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, FirstFragment.newInstance("", ""))
            .addToBackStack(null)
            .commit()
    }

    private fun startRxStream(){
        val disposable = RxUtils().getDisposable()
        disposable?.dispose()
    }

    /**
     * Link : https://stackoverflow.com/questions/4068984/running-multiple-asynctasks-at-the-same-time-not-possible
     * This allows for parallel execution on all android versions with API 4+ (Android 1.6+)
     */
    private fun startAsyncTask(){
        val task = AsyncTaskRunner()
        //task.execute("Start")
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "", "", "")
        //Thread.sleep(3000)
        //task.cancel(true)
    }

    private fun startAsyncTask2(){
        val task = AsyncTaskRunner()
        //task.execute("Start")
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "", "", "")
        //Thread.sleep(3000)
        //task.cancel(true)
    }
}