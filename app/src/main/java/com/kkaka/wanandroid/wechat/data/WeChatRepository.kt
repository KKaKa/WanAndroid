package com.kkaka.wanandroid.wechat.data

import android.arch.lifecycle.MutableLiveData
import com.kkaka.common.common.State
import com.kkaka.common.ext.execute
import com.kkaka.common.https.BaseResponse
import com.kkaka.wanandroid.api.BaseObserver
import com.kkaka.wanandroid.common.article.data.ArticleRepository

/**
 * @author Laizexin on 2019/12/9
 * @description
 */
class WeChatRepository(loadState : MutableLiveData<State>) : ArticleRepository(loadState) {

    fun getWeChatData(liveData: MutableLiveData<BaseResponse<List<WeChatNameRsp>>>) {
        apiService.getWeChat().execute(BaseObserver(liveData,loadState,this))
    }

    fun getWeChataListData(uid: Int, page: Int, liveData: MutableLiveData<BaseResponse<WeChatListRsp>>) {
        apiService.getWeChatList(uid,page).execute(BaseObserver(liveData,loadState,this))
    }

}