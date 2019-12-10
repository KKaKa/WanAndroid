package com.kkaka.wanandroid.nagivation.viewmodel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.kkaka.common.base.BaseViewModel
import com.kkaka.common.https.BaseResponse
import com.kkaka.wanandroid.nagivation.data.NagivationCategoryRsp
import com.kkaka.wanandroid.nagivation.data.NagivationRepository

/**
 * @author Laizexin on 2019/12/10
 * @description
 */
class NagivationViewModel(application: Application) : BaseViewModel<NagivationRepository>(application) {

    val mCategoryData: MutableLiveData<BaseResponse<List<NagivationCategoryRsp>>> = MutableLiveData()

    fun getCategory() {
        mRespository.getCategory(mCategoryData)
    }

}