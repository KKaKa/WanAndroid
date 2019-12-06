package com.kkaka.wanandroid.home.viewmodel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.kkaka.common.base.BaseApplication
import com.kkaka.common.https.BaseResponse
import com.kkaka.wanandroid.common.article.data.ArticleRepository
import com.kkaka.wanandroid.common.article.viewmodel.ArticleViewModel
import com.kkaka.wanandroid.home.data.BannerRsp
import com.kkaka.wanandroid.home.data.HomeArticleRsp
import com.kkaka.wanandroid.home.data.HomeRepository

/**
 * @author Laizexin on 2019/12/2
 * @description
 */
class HomeViewModel(application: Application) : ArticleViewModel<HomeRepository>(application){

    val mHomeArticleData : MutableLiveData<BaseResponse<HomeArticleRsp>> = MutableLiveData()
    val mBannerData : MutableLiveData<BaseResponse<List<BannerRsp>>> = MutableLiveData()

    fun getArticle(page : Int){
        mRespository.getArticle(page,mHomeArticleData)
    }

    fun getBanner() {
        mRespository.getBanner(mBannerData)
    }

}