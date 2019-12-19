package com.kkaka.wanandroid.system.view

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import com.kkaka.wanandroid.common.article.view.ArticleFragment
import com.kkaka.wanandroid.system.viewmodel.SystemViewModel

/**
 * @author Laizexin on 2019/12/19
 * @description
 */
class SystemArticleFragment : ArticleFragment<SystemViewModel>() {

    private val uid by lazy { arguments?.getInt("uid") ?: -1 }
    private var page = 0

    companion object {
        fun newInstance(uid : Int) : Fragment {
            val bundle = Bundle()
            bundle.putInt("uid",uid)
            val fragment = SystemArticleFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun initData() {
        super.initData()
        page = 0
        mViewModel.getSystemArticles(uid,page)
    }

    override fun onRefreshData() {
        page = 0
        mViewModel.getSystemArticles(uid,page)
    }

    override fun onLoadMore() {
        mViewModel.getSystemArticles(uid,++page)
    }

    override fun dataObserver() {
        super.dataObserver()
        mViewModel.mArticleData.observe(this, Observer {
            it?.let {
                addData(it.data.datas)
            }
        })
    }
}