package com.kkaka.wanandroid.account.data

import android.content.Context
import com.kkaka.common.state.collect.CollectListener
import com.kkaka.common.state.UserState
import com.kkaka.wanandroid.account.view.LoginActivity
import org.jetbrains.anko.startActivity

/**
 * @author Laizexin on 2019/12/3
 * @description 未登录状态
 */
class LogoutState : UserState{

    override fun collect(context: Context?, position: Int, listener: CollectListener) {
        goLoginActivity(context)
    }

    private fun goLoginActivity(context: Context?) {
        context?.let {
            it.startActivity<LoginActivity>()
        }
    }
}