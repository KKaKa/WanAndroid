package com.kkaka.wanandroid.search.data

import android.arch.lifecycle.MutableLiveData
import com.kkaka.common.common.State
import com.kkaka.common.ext.execute
import com.kkaka.common.https.BaseResponse
import com.kkaka.wanandroid.api.BaseObserver
import com.kkaka.wanandroid.common.article.data.ArticleRepository

/**
 * @author Laizexin on 2019/12/11
 * @description
 */
class SearchRepository(loadState : MutableLiveData<State>) : ArticleRepository(loadState) {

    fun search(page: Int, str: String, liveData: MutableLiveData<BaseResponse<SearchResultRsp>>) {
        apiService.search(page,str).execute(BaseObserver(liveData,loadState,this))
    }

    fun getHotSearch(liveData: MutableLiveData<BaseResponse<List<HotSearchRsp>>>) {
        apiService.getHotKey().execute(BaseObserver(liveData,loadState,this))
    }

}