package com.kkaka.wanandroid.account.viewmodel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.kkaka.common.base.BaseViewModel
import com.kkaka.common.common.State
import com.kkaka.common.common.StateType
import com.kkaka.common.https.BaseResponse
import com.kkaka.wanandroid.R
import com.kkaka.wanandroid.account.data.AccountRepository
import com.kkaka.wanandroid.account.data.LoginRsp
import com.kkaka.wanandroid.account.data.RegisterRsp


/**
 * @author Laizexin on 2019/12/3
 * @description
 */
class AccountViewModel(application: Application) : BaseViewModel<AccountRepository>(application) {

    val mLoginData = MutableLiveData<BaseResponse<LoginRsp>>()
    val mRegistData = MutableLiveData<BaseResponse<RegisterRsp>>()

    fun login(userName :String,password :String){
        mRespository.login(userName,password,mLoginData)
    }

    fun regist(userName :String,password: String,repassword: String){
        if(password != repassword){
            loadState.postValue(State(StateType.TIP,msg = getApplication<Application>().resources.getString(R.string.password_ensure_error)))
        }else{
            mRespository.regist(userName,password,repassword,mRegistData)
        }
    }
}