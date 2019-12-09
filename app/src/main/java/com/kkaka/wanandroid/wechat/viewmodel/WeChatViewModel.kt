package com.kkaka.wanandroid.wechat.viewmodel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.kkaka.common.https.BaseResponse
import com.kkaka.wanandroid.common.article.data.ArticleRepository
import com.kkaka.wanandroid.common.article.viewmodel.ArticleViewModel
import com.kkaka.wanandroid.wechat.data.WeChatListRsp
import com.kkaka.wanandroid.wechat.data.WeChatNameRsp
import com.kkaka.wanandroid.wechat.data.WeChatRepository

/**
 * @author Laizexin on 2019/12/9
 * @description
 */
class WeChatViewModel(application: Application) :ArticleViewModel<WeChatRepository>(application) {

    var mWeChatData : MutableLiveData<BaseResponse<List<WeChatNameRsp>>> = MutableLiveData()
    var mWeChatListData : MutableLiveData<BaseResponse<WeChatListRsp>> = MutableLiveData()

    fun getWeChat() {
        mRespository.getWeChatData(mWeChatData)
    }

    fun getWeChatList(uid: Int, page: Int) {
        mRespository.getWeChataListData(uid,page,mWeChatListData)
    }

}