package com.kkaka.wanandroid.collect.view

import android.arch.lifecycle.Observer
import com.kkaka.wanandroid.R
import com.kkaka.wanandroid.account.data.UserContext
import com.kkaka.wanandroid.collect.vm.CollectViewModel
import com.kkaka.wanandroid.common.article.view.ArticleActivity

class CollectActivity : ArticleActivity<CollectViewModel>() {

    override fun isAddToolbar(): Boolean = true

    private var index = 0;
    private var id : Int = 0
    private var originId : Int = 0

    override fun headTitle(): Int {
        return R.string.collect
    }

    private var page = 0

    override fun initData() {
        super.initData()
        page = 0
        mViewModel.getCollectArticles(page)
    }

    override fun onRefreshData() {
        page = 0
        mViewModel.getCollectArticles(page)
    }

    override fun onLoadMore() {
        mViewModel.getCollectArticles(++page)
    }

    override fun dataObserver() {
        mViewModel.mConnectArticleData.observe(this, Observer {
            it?.let {
                it.data.datas.forEach {
                    it.collect = true
                }
                addData(it.data.datas)
            }
        })

        mViewModel.mUnConnectArticleData.observe(this, Observer {
            mArticleAdapter.remove(index)
            UserContext.instance.collectRefresh(id,originId)
        })
    }

    override fun collect(position: Int) {
        val item = mArticleAdapter.getItem(position)
        item?.let {
            index = position
            id = it.id
            originId = it.originId
            mViewModel.unCollect(it.id,it.originId)
        }
    }


}
