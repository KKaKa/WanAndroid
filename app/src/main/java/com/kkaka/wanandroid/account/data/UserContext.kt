package com.kkaka.wanandroid.account.data

import android.content.Context
import com.kkaka.common.constant.Constant
import com.kkaka.common.state.collect.CollectListener
import com.kkaka.common.state.UserState
import com.kkaka.common.state.login.LoginSucState
import com.kkaka.common.utils.Preference

/**
 * @author Laizexin on 2019/12/3
 * @description
 */
class UserContext private constructor(){

    private var isLogin : Boolean by Preference(Constant.LOGIN_KEY,false)

    var mState : UserState = if(isLogin) LoginState() else LogoutState()

    companion object {
        val instance by lazy { UserContext() }
    }

    fun getInstance() : UserContext {
        return instance
    }

    fun collect(context: Context?, position: Int, listener: CollectListener){
        mState.collect(context,position,listener)
    }

    fun loginSuccess(userName :String,collectIds: List<Int>?){
        isLogin = true
        mState = LoginState()
        LoginSucState.notifyLoginState(userName,collectIds)
    }

}