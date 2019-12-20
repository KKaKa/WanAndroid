package com.kkaka.wanandroid.project.viewmodel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.kkaka.common.base.BaseViewModel
import com.kkaka.common.https.BaseResponse
import com.kkaka.wanandroid.project.data.ProjectRepository
import com.kkaka.wanandroid.project.data.ProjectRsp
import com.kkaka.wanandroid.project.data.ProjectTabRsp

/**
 * @author Laizexin on 2019/12/19
 * @description
 */
class ProjectViewModel (application: Application) : BaseViewModel<ProjectRepository>(application){
    val mProjectTapData :MutableLiveData<BaseResponse<List<ProjectTabRsp>>> = MutableLiveData()
    val mProjectListData: MutableLiveData<BaseResponse<ProjectRsp>> = MutableLiveData()

    fun getProjectTab() {
        mRespository.getProjectTab(mProjectTapData)
    }

    fun getProjectList(uid: Int, page: Int) {
        mRespository.getProjectList(uid,page,mProjectListData)
    }

}