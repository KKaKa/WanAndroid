package com.kkaka.wanandroid.collect.data

import android.arch.lifecycle.MutableLiveData
import com.kkaka.common.common.State
import com.kkaka.common.ext.execute
import com.kkaka.common.https.BaseResponse
import com.kkaka.wanandroid.api.BaseObserver
import com.kkaka.wanandroid.common.article.data.ArticleRepository

/**
 * @author Laizexin on 2019/12/5
 * @description
 */
class CollectRepository(loadState: MutableLiveData<State>) : ArticleRepository(loadState) {

    fun getCollectArticles(page : Int, liveData: MutableLiveData<BaseResponse<CollectRsp>>){
        apiService.getCollectArticle(page).execute(BaseObserver(liveData,loadState,this))
    }

    fun unCollect(id: Int, originId: Int, liveData: MutableLiveData<BaseResponse<Any>>){
        apiService.unMyCollect(id,originId).execute(BaseObserver(liveData,loadState,this))
    }

}