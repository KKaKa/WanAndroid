package com.kkaka.common.base

import android.app.Application
import com.kingja.loadsir.core.LoadSir
import com.kkaka.common.callback.EmptyCallback
import com.kkaka.common.callback.ErrorCallback
import com.kkaka.common.callback.LoadingCallback
import com.kkaka.common.utils.Preference
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy


/**
 * @author Laizexin on 2019/11/28
 * @description
 */
open class BaseApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        LoadSir.beginBuilder()
            .addCallback(ErrorCallback())
            .addCallback(LoadingCallback())
            .addCallback(EmptyCallback())
            .commit()

        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .tag("WanAndroid >>> ")
            .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))

        Preference.setContext(applicationContext)

    }

}