package com.kkaka.wanandroid.api

import com.kkaka.common.base.BaseRepository
import com.kkaka.common.https.RetrofitFactory

/**
 * @author Laizexin on 2019/12/2
 * @description
 */
open class ApiRepository : BaseRepository() {

    public val apiService : ApiService by lazy {
        RetrofitFactory.instance.create(ApiService::class.java)
    }

}