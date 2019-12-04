package com.kkaka.common.base

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.text.TextUtils
import com.kingja.loadsir.callback.SuccessCallback
import com.kingja.loadsir.core.LoadService
import com.kkaka.common.callback.EmptyCallback
import com.kkaka.common.callback.ErrorCallback
import com.kkaka.common.callback.LoadingCallback
import com.kkaka.common.common.State
import com.kkaka.common.common.StateType
import com.kkaka.common.utils.Util
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast

/**
 * @author Laizexin on 2019/12/2
 * @description
 */
abstract class LifecycleFragment<T : BaseViewModel<*>> : BaseFragment() {

    lateinit var mViewModel : T

    override fun initView() {
        showLoading()
        mViewModel = ViewModelProviders.of(this).get(Util.getClass(this))
        mViewModel.loadState.observe(this,observer)
        //设置view的observer
        dataObserver()
    }

    abstract fun dataObserver()

    override fun reLoad() {
        showLoading()
        super.reLoad()
    }

    private fun showLoading() {
        loadService.showCallback(LoadingCallback::class.java)
    }

    private fun showSuccess(){
        loadService.showCallback(SuccessCallback::class.java)
    }

    private fun showEmpty(){
        loadService.showCallback(EmptyCallback::class.java)
    }

    private fun showError(msg : String){
        if(!TextUtils.isEmpty(msg)){
            toast(msg)
        }
        loadService.showCallback(ErrorCallback::class.java)
    }

    open fun showTip(msg: String){
        if(!TextUtils.isEmpty(msg)){
            toast(msg)
        }
        loadService.showCallback(SuccessCallback::class.java)
    }

    private val observer by lazy {
        Observer<State> {
            it?.let {
                when(it.code){
                    StateType.EMPTY -> showEmpty()
                    StateType.SUCCESS -> showSuccess()
                    StateType.LOADING -> showLoading()
                    StateType.ERROR -> showTip(it.msg)
                    StateType.NETWORK_ERROR -> showError("网络异常")
                    StateType.TIP -> showTip(it.msg)
                }
            }
        }
    }
}