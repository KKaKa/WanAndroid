package com.kkaka.common.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir

/**
 * @author Laizexin on 2019/11/29
 * @description
 */
abstract class BaseFragment : Fragment() {

    lateinit var loadService : LoadService<*>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(getLayoutId(), null)
        loadService = LoadSir.getDefault().register(rootView) {reLoad()}
        return loadService.loadLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    abstract fun initView()

    open fun reLoad(){
        initData()
    }

    open fun initData() {
    }

    abstract fun getLayoutId() :Int

}