package com.kkaka.wanandroid.about.data

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.kkaka.common.base.BaseRepository
import com.kkaka.common.common.State
import com.kkaka.wanandroid.common.CacheManager

/**
 * @author Laizexin on 2019/12/31
 * @description
 */
class AboutRepository(val loadState : MutableLiveData<State>) : BaseRepository() {

    fun getCacheSize(liveData: MutableLiveData<String>,context: Context) {
        liveData.postValue(CacheManager.getCacheSize(context))
    }

    fun clearCache(liveData: MutableLiveData<String>, context: Context) {
        CacheManager.clearCache(context)
        liveData.postValue(CacheManager.getCacheSize(context))
    }

}