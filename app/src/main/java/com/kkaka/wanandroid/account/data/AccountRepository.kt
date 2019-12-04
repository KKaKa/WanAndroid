package com.kkaka.wanandroid.account.data

import android.arch.lifecycle.MutableLiveData
import com.kkaka.common.base.BaseRepository
import com.kkaka.common.common.State
import com.kkaka.common.ext.execute
import com.kkaka.common.https.BaseResponse
import com.kkaka.wanandroid.api.ApiRepository
import com.kkaka.wanandroid.api.BaseObserver

/**
 * @author Laizexin on 2019/12/3
 * @description
 */
class AccountRepository(val loadState: MutableLiveData<State>) : ApiRepository() {

    fun login(userName :String,password :String,liveData: MutableLiveData<BaseResponse<LoginRsp>>){
        apiService.getLogin(userName,password).execute(BaseObserver(liveData,loadState,this))
    }

    fun regist(userName: String,password: String,repassword :String ,liveData: MutableLiveData<BaseResponse<RegisterRsp>>){
        apiService.getRegister(userName,password,repassword).execute(BaseObserver(liveData,loadState,this))
    }
}