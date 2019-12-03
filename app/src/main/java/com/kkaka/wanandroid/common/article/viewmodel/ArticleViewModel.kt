package com.kkaka.wanandroid.common.article.viewmodel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.kkaka.common.base.BaseApplication
import com.kkaka.common.base.BaseViewModel
import com.kkaka.common.https.BaseResponse
import com.kkaka.wanandroid.common.article.data.ArticleRepository

/**
 * @author Laizexin on 2019/12/2
 * @description 文章类ViewModel 具有收藏功能
 */
open class ArticleViewModel<T : ArticleRepository>(application: Application) : BaseViewModel<T>(application) {

    var mCollectData: MutableLiveData<BaseResponse<Any>> = MutableLiveData()

    fun collect(id : Int){
        mRespository.collect(id,mCollectData)
    }

    fun unCollect(id: Int){
        mRespository.unCollect(id,mCollectData)
    }

}