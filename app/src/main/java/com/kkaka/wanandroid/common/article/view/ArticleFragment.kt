package com.kkaka.wanandroid.common.article.view

import android.arch.lifecycle.Observer
import android.support.v7.widget.LinearLayoutManager
import com.kkaka.common.base.LifecycleFragment
import com.kkaka.common.state.collect.CollectListener
import com.kkaka.wanandroid.R
import com.kkaka.wanandroid.account.data.UserContext
import com.kkaka.wanandroid.common.adapter.ArticleAdapter
import com.kkaka.wanandroid.common.article.data.Article
import com.kkaka.wanandroid.common.article.viewmodel.ArticleViewModel
import kotlinx.android.synthetic.main.fragment_article.*

/**
 * @author Laizexin on 2019/12/2
 * @description
 */
abstract class ArticleFragment<T : ArticleViewModel<*>> : LifecycleFragment<T>(),
    CollectListener {

    private var current = 0
    private var collectState = false

    private lateinit var mArticleAdapter :ArticleAdapter

    override fun getLayoutId(): Int = R.layout.fragment_article

    override fun initView() {
        super.initView()
        initRefresh()
        initRecycleView()
    }

    private fun initRecycleView() {
        mRvArticle.layoutManager = LinearLayoutManager(activity)
        mArticleAdapter = ArticleAdapter(R.layout.item_article,null)
        mRvArticle.adapter = mArticleAdapter

        //加载更多
        mArticleAdapter.setEnableLoadMore(true)
        mArticleAdapter.setOnLoadMoreListener({onLoadMore()},mRvArticle)

        mArticleAdapter.setOnItemClickListener { adapter, view, position -> {
            //跳转
        } }

        mArticleAdapter.setOnItemChildClickListener { adapter, view, position ->
            run {
                if (view.id == R.id.mIvCollect) {
                    UserContext.instance.collect(activity, position, this)
                }
            }
        }
    }

    abstract fun onRefreshData()

    abstract fun onLoadMore()

    private fun initRefresh() {
        msrlRefresh.setColorSchemeResources(R.color.colorPrimaryDark)
        msrlRefresh.setOnRefreshListener {
            onRefreshData()
        }
    }

    override fun dataObserver() {
        mViewModel.mCollectData.observe(this, Observer { response ->
            val item = mArticleAdapter.getItem(current)
            item?.let {
                it.collect = !collectState
                mArticleAdapter.notifyItemChanged(current)
            }
        })
    }

    fun addData(datas: List<Article>) {
        if(datas.isEmpty()){
            mArticleAdapter.loadMoreEnd()
            return
        }

        //下拉刷新 注意完成加载更多(存在加载更多时刷新的情况)
        if(msrlRefresh.isRefreshing){
            msrlRefresh.isRefreshing = false
            mArticleAdapter.setNewData(datas)
            mArticleAdapter.loadMoreComplete()
            return
        }

        //加载更多
        mArticleAdapter.addData(datas)
        mArticleAdapter.loadMoreComplete()
    }

    override fun collect(position: Int) {
        val item = mArticleAdapter.getItem(position)
        item?.let {
            current = position
            collectState = it.collect
            if(it.collect) mViewModel.unCollect(it.id) else mViewModel.collect(it.id)
        }
    }

}