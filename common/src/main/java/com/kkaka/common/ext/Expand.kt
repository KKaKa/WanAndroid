package com.kkaka.common.ext

import android.widget.TextView
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author Laizexin on 2019/12/2
 * @description 拓展函数
 */
fun <T> Observable<T>.execute(observer: Observer<T>) {
    this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(observer)
}

fun TextView.str(): String {
    return this.text.toString().trim()
}