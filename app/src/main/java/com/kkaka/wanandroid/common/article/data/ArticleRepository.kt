package com.kkaka.wanandroid.common.article.data

import android.arch.lifecycle.MutableLiveData
import com.kkaka.common.base.BaseRepository
import com.kkaka.common.common.State
import com.kkaka.common.ext.execute
import com.kkaka.common.https.BaseResponse
import com.kkaka.wanandroid.api.ApiRepository
import com.kkaka.wanandroid.api.BaseObserver

/**
 * @author Laizexin on 2019/12/2
 * @description
 */
open class ArticleRepository(val loadState : MutableLiveData<State>) : ApiRepository(){

    fun collect(position: Int,liveData : MutableLiveData<BaseResponse<Any>>){
        apiService.collect(position).execute(BaseObserver(liveData,loadState,this))
    }

    fun unCollect(position: Int,liveData: MutableLiveData<BaseResponse<Any>>){
        apiService.unCollect(position).execute(BaseObserver(liveData,loadState,this))
    }

}