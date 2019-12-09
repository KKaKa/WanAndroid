package com.kkaka.wanandroid.wechat.view

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import com.kkaka.wanandroid.common.article.view.ArticleFragment
import com.kkaka.wanandroid.wechat.viewmodel.WeChatViewModel

/**
 * @author Laizexin on 2019/12/9
 * @description
 */
class WeChatListFragment : ArticleFragment<WeChatViewModel>(){

    private var page = 0
    private val uid by lazy { arguments?.getInt("uid") ?: -1 }

    companion object {
        fun newInstance(uid : Int) : Fragment{
            val bundle = Bundle()
            bundle.putInt("uid",uid)
            val fragment = WeChatListFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun initData() {
        super.initData()
        page = 0
        mViewModel.getWeChatList(uid,page)
    }

    override fun onLoadMore() {
        mViewModel.getWeChatList(uid,++page)
    }

    override fun onRefreshData() {
        page = 1
        mViewModel.getWeChatList(uid,page)
    }

    override fun dataObserver() {
        super.dataObserver()

        mViewModel.mWeChatListData.observe(this, Observer {
            it?.let {
                addData(it.data.datas)
            }
        })
    }
}