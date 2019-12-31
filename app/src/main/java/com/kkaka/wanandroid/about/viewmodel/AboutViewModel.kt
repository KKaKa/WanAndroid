package com.kkaka.wanandroid.about.viewmodel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.kkaka.common.base.BaseViewModel
import com.kkaka.wanandroid.about.data.AboutRepository

/**
 * @author Laizexin on 2019/12/31
 * @description
 */
class AboutViewModel (application: Application) : BaseViewModel<AboutRepository>(application){

    val mCacheData : MutableLiveData<String> = MutableLiveData()

    fun getCacheSize() {
        mRespository.getCacheSize(mCacheData,getApplication())
    }

    fun clearCache() {
        mRespository.clearCache(mCacheData,getApplication())
    }

}