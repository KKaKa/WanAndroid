package com.kkaka.wanandroid.api

import android.arch.lifecycle.MutableLiveData
import com.kkaka.common.base.BaseRepository
import com.kkaka.common.common.State
import com.kkaka.common.common.StateType
import com.kkaka.common.constant.Constant
import com.kkaka.common.https.BaseResponse
import com.kkaka.wanandroid.BuildConfig
import com.kkaka.wanandroid.account.data.UserContext
import com.kkaka.wanandroid.search.data.SearchResultRsp
import com.orhanobut.logger.Logger
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * @author Laizexin on 2019/12/2
 * @description
 */
class BaseObserver<T : BaseResponse<*>>(
    private val liveData: MutableLiveData<T>,
    private val loadState: MutableLiveData<State>,
    private val repository: BaseRepository
) : Observer<T> {

    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
        repository.Subscribe(d)
    }

    override fun onNext(response: T) {
        when(response.errorCode){
            Constant.RESPONSE_SUCCESS -> {
                if(response.data is List<*>){
                    if((response.data as List<*>).isEmpty()){
                        loadState.postValue(State(StateType.EMPTY))
                        return
                    }
                }
                loadState.postValue(State(StateType.SUCCESS))
                liveData.postValue(response)
            }
            Constant.RESPONSE_NOT_LOGIN ->{
                UserContext.instance.logoutSuccess()
                loadState.postValue(State(StateType.ERROR,msg = "登录过期"))
            }
            else -> {
                loadState.postValue(State(StateType.ERROR,msg = response.errorMsg))
            }
        }
    }

    override fun onError(e: Throwable) {
        if(BuildConfig.DEBUG){
            e.message?.let { Logger.e(it) }
        }
        loadState.postValue(State(StateType.NETWORK_ERROR))
    }


}