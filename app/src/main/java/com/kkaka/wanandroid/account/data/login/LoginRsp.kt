package com.kkaka.wanandroid.account.data.login

/**
 * author：  HyZhan
 * created： 2018/10/11 18:11
 * desc：    登录返回类
 */
data class LoginRsp(
        var icon: String,
        var type: String,
        var collectIds: List<Int>,
        var username: String
)
