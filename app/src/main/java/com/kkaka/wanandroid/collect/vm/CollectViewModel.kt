package com.kkaka.wanandroid.collect.vm

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.kkaka.common.https.BaseResponse
import com.kkaka.wanandroid.collect.data.CollectRepository
import com.kkaka.wanandroid.collect.data.CollectRsp
import com.kkaka.wanandroid.common.article.viewmodel.ArticleViewModel

/**
 * @author Laizexin on 2019/12/5
 * @description
 */
class CollectViewModel(application: Application) : ArticleViewModel<CollectRepository>(application) {

    val mConnectArticleData :MutableLiveData<BaseResponse<CollectRsp>> = MutableLiveData()
    val mUnConnectArticleData :MutableLiveData<BaseResponse<Any>> = MutableLiveData()

    fun getCollectArtrcles(page :Int){
        mRespository.getCollectArtrcles(page,mConnectArticleData)
    }

    fun unCollect(id : Int,originId : Int){
        mRespository.unCollect(id,originId,mUnConnectArticleData)
    }

}