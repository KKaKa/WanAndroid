package com.kkaka.wanandroid.project.view

import android.arch.lifecycle.Observer
import android.support.v4.app.Fragment
import com.kkaka.common.base.LifecycleFragment
import com.kkaka.wanandroid.R
import com.kkaka.wanandroid.common.adapter.ProjectTabAdapter
import com.kkaka.wanandroid.project.data.ProjectTabRsp
import com.kkaka.wanandroid.project.viewmodel.ProjectViewModel
import kotlinx.android.synthetic.main.fragment_project.*

/**
 * @author Laizexin on 2019/12/19
 * @description
 */
class ProjectFragment : LifecycleFragment<ProjectViewModel>(){

    override fun getLayoutId(): Int = R.layout.fragment_project

    override fun initView() {
        super.initView()
        mTabLayout.setupWithViewPager(mContent)
    }

    override fun initData() {
        super.initData()
        mViewModel.getProjectTab()
    }

    override fun dataObserver() {
        mViewModel.mProjectTapData.observe(this, Observer {
            it?.let {
                buildViewPage(it.data)
            }
        })
    }

    private fun buildViewPage(data: List<ProjectTabRsp>) {
        val tabs = arrayListOf<String>()
        val fragments = arrayListOf<Fragment>()

        data.forEach {
            tabs.add(it.name)
            fragments.add(ProjectListFragment.newInstance(it.id))
        }
        mContent.adapter = ProjectTabAdapter(childFragmentManager,tabs,fragments)
    }
}