package com.ajain11337.base.rx

import android.util.Log
import com.ajain11337.base.utils.Constants
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import kotlin.random.Random

class RxUtils {
    /**
     * Observable.just support max 10 items at a time.
     * While Single.just supports only one item.
     */
    fun getDisposable(): @NonNull Disposable? {
        return Observable.just(1, 2, 3, 4, 5)
            .map {
                Log.d(Constants.RxDebug, Thread.currentThread().name)
                return@map it * it
            }
            .flatMap {
                Thread.sleep(1000)
                Log.d(Constants.RxDebug, Thread.currentThread().name)
                return@flatMap Observable.just(it * it)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                Log.d(Constants.RxDebug, "onNext: $it")
                Log.d(Constants.RxDebug, Thread.currentThread().name)
            }
            .doOnError {
                Log.d(Constants.RxDebug, "doOnError")
                Log.d(Constants.RxDebug, Thread.currentThread().name)
            }
            .doOnComplete {
                Log.d(Constants.RxDebug, "doOnComplete")
                Log.d(Constants.RxDebug, Thread.currentThread().name)
            }
            .subscribe {
                Log.d(Constants.RxDebug, "subscribe: $it")
                Log.d(Constants.RxDebug, Thread.currentThread().name)
            }
    }

    /**
     * Link : https://medium.com/mobile-app-development-publication/rxjava-2-understanding-hot-vs-cold-with-just-vs-fromcallable-3c463f9f68c9
     * Just take a given value from whatever source that was sent to it.
     * While fromCallable actually generate the value from within the Single.
     *
     * You’ll notice the value given by just stays the same regardless of how many subscribe it has.
     * The value was generated externally from the Observable, and the observable just stores it for consumption later.
     * However for fromCallable it generates internally from the Observable.
     * A new random number is generated each time a subscribe is connected to it.
     *
     * Other than the above, another difference is, just has the value even before anyone subscribe to it.
     * However fromCallable only perform the value generation when someone subscribe to it.
     * In another word, the delayed execution is also known as lazy initialization.
     * It is something very useful in programming, to save some processing resources until it is needed later.
     */
    fun checkSingleJustVsSingleCallable() {
        println("From Just")
        val justSingle = Single.just(Random.nextInt())
        justSingle.subscribe { it -> println(it) }
        justSingle.subscribe { it -> println(it) }

        println("\nFrom Callable")
        val callableSingle = Single.fromCallable { Random.nextInt() }
        callableSingle.subscribe { it -> println(it) }
        callableSingle.subscribe { it -> println(it) }
    }

    /**
     * Link : https://blog.mindorks.com/understanding-rxjava-create-and-fromcallable-operator
     * Both Create and fromCallable defer the execution of the task we specify until an observer subscribes to the ObservableSource.
     * Means, it makes the task "lazy."
     *
     * So, the following are the major differences between Create and fromCallable Operators:
     * 1 -> Create can emit multiple items whereas fromCallable can emit only one item.
     * 2 -> There is no simple way to check if isDisposed in fromCallable as present in Create.
     * So if it emits item after disposal, the throwable is delivered to the global error handler via RxJavaPlugins.onError as UndeliverableException.
     * It means it will crash the application. This is how it is different from Single.
     */
    fun checkSingleCreateVsSingleCallable() {
        // Single Creation
        Observable.create<String> { emitter ->
            if (!emitter.isDisposed) { emitter.onNext("One") }
            if (!emitter.isDisposed) { emitter.onNext("Two") }
            if (!emitter.isDisposed) { emitter.onComplete() }
        }.subscribeOn(Schedulers.io())
            .subscribe { item ->
                Log.d("CreateExample", "item : $item")
            }

        // Callable creation
        Observable.fromCallable<String> {
            return@fromCallable "MindOrks"
        }.subscribeOn(Schedulers.io())
            .subscribe { item ->
                Log.d("FromCallableExample", "item : $item")
            }
    }
}