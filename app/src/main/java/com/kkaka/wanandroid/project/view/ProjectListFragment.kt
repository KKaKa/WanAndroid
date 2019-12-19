package com.kkaka.wanandroid.project.view

import android.os.Bundle
import android.support.v4.app.Fragment
import com.kkaka.common.base.LifecycleFragment
import com.kkaka.wanandroid.R
import com.kkaka.wanandroid.wechat.viewmodel.WeChatViewModel

/**
 * @author Laizexin on 2019/12/9
 * @description
 */
class ProjectListFragment : LifecycleFragment<WeChatViewModel>() {

    override fun getLayoutId(): Int = R.layout.fragment_project_list

    private var page = 0
    private val uid by lazy { arguments?.getInt("uid") ?: -1 }

    companion object {
        fun newInstance(uid: Int): Fragment {
            val bundle = Bundle()
            bundle.putInt("uid", uid)
            val fragment = ProjectListFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun initData() {
        super.initData()
        page = 0
    }

    override fun dataObserver() {

    }

}
