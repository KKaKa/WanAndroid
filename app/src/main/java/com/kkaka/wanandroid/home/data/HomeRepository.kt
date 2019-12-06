package com.kkaka.wanandroid.home.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.kkaka.common.common.State
import com.kkaka.common.ext.execute
import com.kkaka.common.https.BaseResponse
import com.kkaka.wanandroid.api.BaseObserver
import com.kkaka.wanandroid.common.article.data.ArticleRepository

/**
 * @author Laizexin on 2019/12/2
 * @description
 */
class HomeRepository(loadState : MutableLiveData<State>) : ArticleRepository(loadState){

    fun getArticle(page : Int,liveData: MutableLiveData<BaseResponse<HomeArticleRsp>>){
        apiService.getHomeArticle(page).execute(BaseObserver(liveData,loadState,this))
    }

    fun getBanner(liveData: MutableLiveData<BaseResponse<List<BannerRsp>>>) {
        apiService.getBanner().execute(BaseObserver(liveData,loadState,this))
    }

}