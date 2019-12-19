package com.kkaka.wanandroid.system.view

import android.arch.lifecycle.Observer
import android.support.v7.widget.GridLayoutManager
import com.kkaka.wanandroid.R
import com.kkaka.wanandroid.common.adapter.SystemMenuAdapter
import com.kkaka.wanandroid.common.article.view.ArticleFragment
import com.kkaka.wanandroid.system.data.SecondMenuRsp
import com.kkaka.wanandroid.system.data.TopMenu
import com.kkaka.wanandroid.system.data.TopMenuRsp
import com.kkaka.wanandroid.system.viewmodel.SystemViewModel
import kotlinx.android.synthetic.main.fragment_article.*
import org.jetbrains.anko.support.v4.startActivity


/**
 * @author Laizexin on 2019/12/17
 * @description
 */
class SystemFragment : ArticleFragment<SystemViewModel>(){

    private val systemAdapter : SystemMenuAdapter by lazy { SystemMenuAdapter(R.layout.item_system_top_menu_content,R.layout.item_system_top_menu_head,null) }

    override fun initData() {
        super.initData()
        mViewModel.getTopMenu()
    }

    override fun initView() {
        super.initView()

        msrlRefresh.setColorSchemeResources(R.color.colorPrimaryDark)
        msrlRefresh.setOnRefreshListener {
            onRefreshData()
        }

        mRvArticle.layoutManager = GridLayoutManager(activity,4)
        mRvArticle.adapter = systemAdapter

        systemAdapter.setOnItemChildClickListener { adapter, view, position ->
            val data = (adapter.data[position] as TopMenu).childrens
            val topTitle = (adapter.data[position] as TopMenu).header
            val ids = arrayListOf<Int>()
            val titls = arrayListOf<String>()
            data.forEach {
                ids.add(it.id)
                titls.add(it.name)
            }
            startActivity<SystemArticleActivity>("ids" to ids,"titls" to titls,"topTitle" to topTitle)
        }
        //无加载更多
        systemAdapter.setEnableLoadMore(false)
    }

    override fun dataObserver() {
        super.dataObserver()
        mViewModel.mTopMuneData.observe(this, Observer {
            buildTopMenu(it?.data)
        })
    }

    private fun buildTopMenu(data: List<TopMenuRsp>?) {
        val list = arrayListOf<TopMenu>()
        data?.forEach {
            list.add(TopMenu(true,it.name,!it.children.isEmpty(),it.children))
            it.children.forEach {
                list.add(TopMenu(SecondMenuRsp(it.name,it.id)))
            }
        }
        if(msrlRefresh.isRefreshing){
            msrlRefresh.isRefreshing = false
        }
        systemAdapter.setNewData(list)
    }

    override fun onRefreshData() {
        mViewModel.getTopMenu()
    }

    override fun onLoadMore() {
        //无加载更多
    }

}