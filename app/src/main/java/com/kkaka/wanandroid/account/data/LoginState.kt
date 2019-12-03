package com.kkaka.wanandroid.account.data

import android.content.Context
import com.kkaka.common.state.collect.CollectListener
import com.kkaka.common.state.UserState

/**
 * @author Laizexin on 2019/12/3
 * @description 登录状态
 */
class LoginState : UserState{

    override fun collect(context: Context?, position: Int, listener: CollectListener) {
        listener.collect(position)
    }
}