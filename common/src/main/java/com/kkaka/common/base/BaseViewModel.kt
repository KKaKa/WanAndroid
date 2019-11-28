package com.kkaka.common.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel

/**
 * @author Laizexin on 2019/11/28
 * @description 这里跨module 采用反射
 */
class BaseViewModel<T : BaseRepository>(application: Application) : AndroidViewModel(application){

    val load

}