package com.ajain11337.base.multithreading

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.ajain11337.base.R
import com.ajain11337.base.utils.Constants
import java.lang.Thread.currentThread

class ThreadingActivity : AppCompatActivity() {

    /**
     * Handler works only with the message queue of the thread on which it is initialized.
     */
    private val mainHandler = Handler()

    /**
     * @Volatile :
     * All our threads will have the latest value of this variable, not the cached version.
     */
    @Volatile
    private var stopThread = false

    private val exampleLooperThread = ExampleLooperThread()
    private val exampleHandlerThread  = ExampleHandlerThread("ExampleHandlerThread")
    private val threadHandler  = Handler(exampleHandlerThread.looper)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_threading)
        exampleHandlerThread.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        exampleHandlerThread.quit()
    }

    /**
     * Link : https://www.youtube.com/watch?v=QfQE1ayCzf8&list=PLrnPJCHvNZuD52mtV8NvazNYIyIVPVZRa&index=1
     * Two ways of starting a new Thread;
     * extend Thread class; or implements Runnable;
     *
     * Also Handler, Looper, AsyncTask, HandlerThread, ThreadPoolExecutor
     * "Only the original thread that created view hierarchy can touch it views."
     *
     * Thread has a Messages Queue -> packages of work to be done.
     * A looper to keep thread alive, and loop through message queue.
     *
     * "Can't create handle inside thread, that has not called Looper.prepare()"
     *
     * Thread lifecycle : start(), run(), terminate
     * Can't use same thread again after termination.
     * Handler provides the pieces of work to MessageQueue.
     */
    fun startThread(view: View) {
        // Run using thread
        //val exampleThread = ExampleThread()
        //exampleThread.start()

        // Run using runnable
        //val exampleRunnable = ExampleRunnable()
        //exampleRunnable.run() //on Ui thread
        //Thread(exampleRunnable).start()

        // Run using exampleLooperThread
        exampleLooperThread.start()
    }

    fun stopThread(view: View) {
        //stopThread = true
        exampleLooperThread.looper.quit()
    }

    inner class ExampleThread : Thread() {
        override fun run() {
            for (i in 0..10) {
                sleep(1000)
                Log.d(Constants.ThreadingActivity, "run: $i " + currentThread().name)
            }
        }
    }

    inner class ExampleRunnable : Runnable {
        override fun run() {
            for (i in 0..10) {
                if (stopThread)
                    return;

                Thread.sleep(1000)

                Handler(Looper.getMainLooper()).post {
                    // change ui for main thread.
                }

                runOnUiThread {
                    // change ui for main thread.
                }

                mainHandler.post {
                    // change ui for main thread.
                }
                Log.d(Constants.ThreadingActivity, "run: $i " + Thread.currentThread().name)
            }
        }
    }

    /**
     * Multiple Task A clicks will add work to the queue;
     * Will executes packet of works sequentially.
     */
    fun taskA(view: View) {
        // Method 1
        /*val handler = Handler(exampleLooperThread.looper) // make's a handler attached to exampleLooperThread
        handler.post {
            for (i in 0..5){
                Log.d(Constants.ExampleLooperThread, "run:" + i  + " "+ currentThread().name)
                SystemClock.sleep(1000)
            }
        }*/

        // Method 2
        /*exampleLooperThread.handler.run {
            for (i in 0..5){
                Log.d(Constants.ExampleLooperThread, "run:" + i  + " "+ currentThread().name)
                SystemClock.sleep(1000)
            }
        }*/

        // Method 3
        /*val msg = Message.obtain()
        msg.what = 1
        exampleLooperThread.handler.sendMessage(msg)*/

        // Method 4
        threadHandler.postDelayed({
            // do some job here
        }, 2000)
        threadHandler.post{
            // post a runnable
            // handle request sequentially
        }
        threadHandler.postAtFrontOfQueue {
            // post a runnable
        }
    }

    fun taskB(view: View) {
        val msg = Message.obtain()
        msg.what = 2
        exampleLooperThread.handler.sendMessage(msg)
    }
}