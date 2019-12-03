package com.kkaka.wanandroid.home.view

import android.arch.lifecycle.Observer
import com.kkaka.wanandroid.common.article.view.ArticleFragment
import com.kkaka.wanandroid.home.viewmodel.HomeViewModel

/**
 * @author Laizexin on 2019/12/2
 * @description
 */
class HomeFragment : ArticleFragment<HomeViewModel>() {
    var page = 0

    override fun initView() {
        super.initView()
    }

    override fun initData() {
        super.initData()
        page = 0
        mViewModel.getArticle(page)
    }

    override fun dataObserver() {
        super.dataObserver()

        mViewModel.mHomeArticleData.observe(this, Observer { response ->
            response?.let {
                addData(it.data.datas)
            }
        })
    }

    override fun onRefreshData() {
        page = 0
        mViewModel.getArticle(page)

    }

    override fun onLoadMore() {
        page++
        mViewModel.getArticle(page)
    }
}