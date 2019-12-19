package com.kkaka.wanandroid.project.view

import com.kkaka.common.base.LifecycleFragment
import com.kkaka.wanandroid.R
import com.kkaka.wanandroid.project.viewmodel.ProjectViewModel

/**
 * @author Laizexin on 2019/12/19
 * @description
 */
class ProjectFragment : LifecycleFragment<ProjectViewModel>(){

    override fun initView() {
        super.initView()
        showTip("TODO")
    }

    override fun dataObserver() {
    }

    override fun getLayoutId(): Int = R.layout.fragment_project
}