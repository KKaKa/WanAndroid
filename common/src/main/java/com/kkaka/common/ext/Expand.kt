package com.kkaka.common.ext

import android.app.Activity
import android.content.Context
import android.text.Html
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.kkaka.common.R
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

fun ImageView.loadImage(context: Context,
                        path: String,
                        placeholder: Int = R.drawable.ic_placeholder,
                        error: Int = R.drawable.ic_error,
                        width: Int = 150,
                        height : Int = 200) {

    val options = RequestOptions()
        .placeholder(placeholder)
        .error(error)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

    if(width != 0 && height != 0) options.override(width,height)

    Glide.with(context).load(path).apply(options).into(this)
}

// 关闭软键盘
fun Activity.hideKeyboard() {
    // 当前焦点的 View
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
}

fun String.toHtml(): String {
    return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT).toString()
    } else {
        Html.fromHtml(this).toString()
    }
}
