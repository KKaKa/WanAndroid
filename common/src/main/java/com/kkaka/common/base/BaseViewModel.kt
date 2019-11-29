package com.kkaka.common.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.kkaka.common.common.State
import com.kkaka.common.utils.Util

/**
 * @author Laizexin on 2019/11/28
 * @description
 */
class BaseViewModel<T : BaseRepository>(application: Application) : AndroidViewModel(application){

    val loadState by lazy { MutableLiveData<State>() }

    val mRespository : T by lazy {
        (Util.getClass<T>(this)).getDeclaredConstructor(MutableLiveData::class.java)
            .newInstance(loadState)
    }

    override fun onCleared() {
        super.onCleared()
        mRespository.unSubscribe()
    }

}