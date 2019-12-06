package com.kkaka.wanandroid.account.data

import android.content.Context
import com.kkaka.common.constant.Constant
import com.kkaka.common.state.collect.CollectListener
import com.kkaka.common.state.UserState
import com.kkaka.common.state.collect.CollectState
import com.kkaka.common.state.login.LoginSucState
import com.kkaka.common.utils.Preference
import com.kkaka.wanandroid.account.data.login.LoginState
import com.kkaka.wanandroid.account.data.logout.LogoutState

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

    fun login(context: Context?){
        mState.login(context)
    }

    fun collect(context: Context?, position: Int, listener: CollectListener){
        mState.collect(context,position,listener)
    }

    fun goCollectActivity(context: Context?){
        mState.goCollectActivity(context)
    }

    fun collectRefresh(id : Int,originId : Int){
        CollectState.notifyCollectState(id,originId)
    }

    fun loginSuccess(userName :String,collectIds: List<Int>?){
        isLogin = true
        mState = LoginState()
        LoginSucState.notifyLoginState(userName,collectIds)
    }

    fun logoutSuccess(){
        isLogin = false
        Preference.clear()
        mState = LogoutState()
        LoginSucState.notifyLoginState("未登录",null)
    }

}