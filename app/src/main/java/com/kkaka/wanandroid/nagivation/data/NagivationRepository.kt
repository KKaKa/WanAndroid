package com.kkaka.wanandroid.nagivation.data

import android.arch.lifecycle.MutableLiveData
import com.kkaka.common.common.State
import com.kkaka.common.ext.execute
import com.kkaka.common.https.BaseResponse
import com.kkaka.wanandroid.api.ApiRepository
import com.kkaka.wanandroid.api.BaseObserver

/**
 * @author Laizexin on 2019/12/10
 * @description
 */
class NagivationRepository(val loadState : MutableLiveData<State>) : ApiRepository(){

    fun getCategory(livadata :  MutableLiveData<BaseResponse<List<NagivationCategoryRsp>>>) {
        apiService.getCategory().execute(BaseObserver(livadata,loadState,this))
    }
}