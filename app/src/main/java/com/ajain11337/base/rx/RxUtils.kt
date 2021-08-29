package com.ajain11337.base.rx

import android.util.Log
import com.ajain11337.base.utils.Constants
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable

class RxUtils {
    fun getDisposable(): @NonNull Disposable? {
        return Observable.just(1, 2, 3, 4, 5)
            .map {
                Log.d(Constants.RxDebug, Thread.currentThread().name)
                return@map it*it
            }
            .flatMap {
                Thread.sleep(1000)
                Log.d(Constants.RxDebug, Thread.currentThread().name)
                return@flatMap Observable.just(it*it)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext{
                Log.d(Constants.RxDebug, "onNext: $it")
                Log.d(Constants.RxDebug, Thread.currentThread().name)
            }
            .doOnError {
                Log.d(Constants.RxDebug, "doOnError")
                Log.d(Constants.RxDebug, Thread.currentThread().name)
            }
            .doOnComplete{
                Log.d(Constants.RxDebug, "doOnComplete")
                Log.d(Constants.RxDebug, Thread.currentThread().name)
            }
            .subscribe {
                Log.d(Constants.RxDebug, "subscribe: $it")
                Log.d(Constants.RxDebug, Thread.currentThread().name)
            }
    }
}