package com.ajain11337.base.coroutines

import android.util.Log
import com.ajain11337.base.utils.Constants
import kotlinx.coroutines.*

class CoroutineUtils {
    // Dispatchers.Main
    suspend fun callForAsync() {
        // Dispatchers.Main
        Log.d(Constants.CoroutineUtils, "callWithContextStarts " + Thread.currentThread().name)

        val x = MainScope().async {
            // Dispatchers.Main
            Log.d(Constants.CoroutineUtils, "async x " + Thread.currentThread().name)
            delay(5000)
            return@async 4
        }

        val y = MainScope().async {
            // Dispatchers.IO
            Log.d(Constants.CoroutineUtils, "async y " + Thread.currentThread().name)
            delay(5000)
            return@async 5
        }

        val z = MainScope().async {
            // Dispatchers.Default
            Log.d(Constants.CoroutineUtils, "async z " + Thread.currentThread().name)
            delay(5000)
            return@async 6
        }

        // Result
        val sum: Int = x.await() + y.await() + z.await()
        Log.d(Constants.CoroutineUtils, "sum = $sum " + Thread.currentThread().name)

        // Dispatchers.Main
        Log.d(Constants.CoroutineUtils, "callWithContextEnds " + Thread.currentThread().name)
    }

    suspend fun callForDispatchers() {
        // Dispatchers.Main
        Log.d(Constants.CoroutineUtils, "callWithContextStarts " + Thread.currentThread().name)

        withContext(Dispatchers.Main) {
            launch {
                // Dispatchers.Main
                Log.d(Constants.CoroutineUtils, "Dispatchers.Main " + Thread.currentThread().name)
                delay(5000)
            }
        }

        withContext(Dispatchers.IO) {
            launch {
                // Dispatchers.IO
                Log.d(Constants.CoroutineUtils, "Dispatchers.IO " + Thread.currentThread().name)
                delay(5000)
            }
        }

        withContext(Dispatchers.Default) {
            launch {
                // Dispatchers.Default
                Log.d(Constants.CoroutineUtils, "Dispatchers.Default " + Thread.currentThread().name)
                delay(5000)
            }
        }

        // Dispatchers.Main
        Log.d(Constants.CoroutineUtils, "callWithContextEnds " + Thread.currentThread().name)
    }

    @DelicateCoroutinesApi
    fun callWithGlobeScope() {
        // Dispatchers.Main
        Log.d(Constants.CoroutineUtils, "callWithContextStarts " + Thread.currentThread().name)

        GlobalScope.launch {
            // GlobalScope
            Log.d(Constants.CoroutineUtils, "GlobalScope " + Thread.currentThread().name)
            delay(5000)
        }
    }
}