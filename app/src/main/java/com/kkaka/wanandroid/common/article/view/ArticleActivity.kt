package com.kkaka.wanandroid.common.article.view

import android.arch.lifecycle.Observer
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View
import com.kkaka.common.base.LifecycleActivity
import com.kkaka.common.state.collect.CollectListener
import com.kkaka.wanandroid.R
import com.kkaka.wanandroid.account.data.UserContext
import com.kkaka.wanandroid.common.adapter.ArticleAdapter
import com.kkaka.wanandroid.common.article.data.Article
import com.kkaka.wanandroid.common.article.viewmodel.ArticleViewModel
import kotlinx.android.synthetic.main.fragment_article.*

abstract class ArticleActivity<T : ArticleViewModel<*>> : LifecycleActivity<T>(),CollectListener{

    private var current = 0
    private var collectState = false

    public lateinit var mArticleAdapter :ArticleAdapter

    override fun getLayoutId(): Int = R.layout.activity_article

    override fun initView() {
        super.initView()
        initRefresh()
        initRecycleView()
        if(isAddToolbar())
            addHeadView()
    }

    private fun addHeadView() {
        val headView = View.inflate(this, R.layout.common_bar,null)
        (headView as Toolbar).let {
            it.setTitle(headTitle())
            setSupportActionBar(it)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        mArticleAdapter.addHeaderView(headView)
    }

    private fun initRefresh() {
        msrlRefresh.setColorSchemeResources(R.color.colorPrimaryDark)
        msrlRefresh.setOnRefreshListener {
            onRefreshData()
        }
    }

    private fun initRecycleView() {
        mRvArticle.layoutManager = LinearLayoutManager(this)
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
                    UserContext.instance.collect(this, position, this)
                }
            }
        }
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

    open fun headTitle() : Int{
        return R.string.app_name
    }

    abstract fun isAddToolbar() : Boolean

    abstract fun onRefreshData()

    abstract fun onLoadMore()

    override fun dataObserver() {
        mViewModel.mCollectData.observe(this, Observer { response ->
            val item = mArticleAdapter.getItem(current)
            item?.let {
                it.collect = !collectState
                mArticleAdapter.notifyItemChanged(current)
            }
        })
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
