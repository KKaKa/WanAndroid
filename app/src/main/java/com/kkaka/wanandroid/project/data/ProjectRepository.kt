package com.kkaka.wanandroid.project.data

import android.arch.lifecycle.MutableLiveData
import com.kkaka.common.common.State
import com.kkaka.common.ext.execute
import com.kkaka.common.https.BaseResponse
import com.kkaka.wanandroid.api.ApiRepository
import com.kkaka.wanandroid.api.BaseObserver

/**
 * @author Laizexin on 2019/12/19
 * @description
 */
class ProjectRepository(val loadState : MutableLiveData<State>) : ApiRepository() {

    fun getProjectTab(liveData: MutableLiveData<BaseResponse<List<ProjectTabRsp>>>) {
        apiService.getProjectTab().execute(BaseObserver(liveData,loadState,this))
    }

    fun getProjectList(uid: Int, page: Int, liveData: MutableLiveData<BaseResponse<ProjectRsp>>) {
        apiService.getProjectList(page,uid).execute(BaseObserver(liveData,loadState,this))
    }

}