package com.kkaka.wanandroid.search.viewmodel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.kkaka.common.https.BaseResponse
import com.kkaka.wanandroid.common.article.viewmodel.ArticleViewModel
import com.kkaka.wanandroid.search.data.HotSearchRsp
import com.kkaka.wanandroid.search.data.SearchRepository
import com.kkaka.wanandroid.search.data.SearchResultRsp

/**
 * @author Laizexin on 2019/12/11
 * @description
 */
class SearchViewModel(application: Application) : ArticleViewModel<SearchRepository>(application){

    val mSearchData : MutableLiveData<BaseResponse<SearchResultRsp>> = MutableLiveData()
    val mHotKeyData : MutableLiveData<BaseResponse<List<HotSearchRsp>>> = MutableLiveData()

    fun search(page: Int, str: String){
        mRespository.search(page,str,mSearchData)
    }

    fun getHotSearch() {
        mRespository.getHotSearch(mHotKeyData)
    }

}