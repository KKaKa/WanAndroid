package com.kkaka.wanandroid.system.data

import android.arch.lifecycle.MutableLiveData
import com.kkaka.common.common.State
import com.kkaka.common.ext.execute
import com.kkaka.common.https.BaseResponse
import com.kkaka.wanandroid.api.BaseObserver
import com.kkaka.wanandroid.common.article.data.ArticleRepository

/**
 * @author Laizexin on 2019/12/17
 * @description
 */
class SystemRepository(loadState : MutableLiveData<State>) : ArticleRepository(loadState) {

    fun getTopMenu(liveData: MutableLiveData<BaseResponse<List<TopMenuRsp>>>) {
        apiService.getTopMenu().execute(BaseObserver(liveData,loadState,this))
    }

    fun getSystemArticles(page: Int, id: Int, liveData: MutableLiveData<BaseResponse<SystemAtricleRsp>>) {
        apiService.getSystemArticles(page,id).execute(BaseObserver(liveData,loadState,this))
    }

}