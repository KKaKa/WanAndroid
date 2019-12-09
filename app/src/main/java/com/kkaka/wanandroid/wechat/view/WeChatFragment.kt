package com.kkaka.wanandroid.wechat.view

import android.arch.lifecycle.Observer
import android.support.v4.app.Fragment
import com.kkaka.common.base.LifecycleFragment
import com.kkaka.wanandroid.R
import com.kkaka.wanandroid.common.adapter.WeChatAdapter
import com.kkaka.wanandroid.wechat.data.WeChatNameRsp
import com.kkaka.wanandroid.wechat.viewmodel.WeChatViewModel
import kotlinx.android.synthetic.main.fragment_wechat.*

/**
 * @author Laizexin on 2019/12/9
 * @description
 */
class WeChatFragment :  LifecycleFragment<WeChatViewModel>(){

    override fun getLayoutId(): Int = R.layout.fragment_wechat

    override fun initView() {
        super.initView()
        mTabLayout.setupWithViewPager(mContent)
    }

    override fun initData() {
        super.initData()
        mViewModel.getWeChat()
    }

    override fun dataObserver() {
        mViewModel.mWeChatData.observe(this, Observer {
            it?.let {
                buildViewPage(it.data)
            }
        })
    }

    private fun buildViewPage(datas: List<WeChatNameRsp>) {

        val titles = mutableListOf<String>()
        val fragments = mutableListOf<Fragment>()

        for (data in datas) {
            titles.add(data.name)
            fragments.add(WeChatListFragment.newInstance(data.id))
        }
        mContent.adapter = WeChatAdapter(childFragmentManager,titles,fragments)
    }
}