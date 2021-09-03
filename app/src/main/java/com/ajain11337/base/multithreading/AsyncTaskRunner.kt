package com.ajain11337.base.multithreading

import android.os.AsyncTask
import android.util.Log
import com.ajain11337.base.utils.Constants

class AsyncTaskRunner : AsyncTask<String, String, String>() {
    override fun onPreExecute() {
        super.onPreExecute()
        Log.d(Constants.AsyncTask, "onPreExecute " + Thread.currentThread().name)
    }

    override fun doInBackground(vararg p0: String?): String {
        Log.d(Constants.AsyncTask, "doInBackground " + Thread.currentThread().name)
        Thread.sleep(5000)
        return "Completed Async Task"
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        Log.d(Constants.AsyncTask, "onPostExecute " + Thread.currentThread().name)
    }

    override fun onProgressUpdate(vararg values: String?) {
        super.onProgressUpdate(*values)
        Log.d(Constants.AsyncTask, "onProgressUpdate " + Thread.currentThread().name)
    }

    override fun onCancelled() {
        super.onCancelled()
        Log.d(Constants.AsyncTask, "onCancelled " + Thread.currentThread().name)
    }
}