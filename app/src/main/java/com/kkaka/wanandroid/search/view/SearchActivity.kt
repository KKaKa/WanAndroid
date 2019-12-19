package com.kkaka.wanandroid.search.view

import android.arch.lifecycle.Observer
import android.graphics.Color
import android.support.v7.widget.SearchView
import android.view.View
import android.widget.ImageView
import com.kkaka.common.ext.hideKeyboard
import com.kkaka.common.ext.str
import com.kkaka.wanandroid.R
import com.kkaka.wanandroid.common.MyFlexboxLayoutManager
import com.kkaka.wanandroid.common.adapter.HistorySearchAdapter
import com.kkaka.wanandroid.common.adapter.HotSearchAdapter
import com.kkaka.wanandroid.common.article.data.Article
import com.kkaka.wanandroid.common.article.view.ArticleActivity
import com.kkaka.wanandroid.search.data.HotSearchRsp
import com.kkaka.wanandroid.search.data.db.Record
import com.kkaka.wanandroid.search.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.layout_search_head.view.*

class SearchActivity : ArticleActivity<SearchViewModel>() {

    private var page = 0
    private lateinit var tv : SearchView.SearchAutoComplete
    private val mHotSearchAdapter: HotSearchAdapter by lazy { HotSearchAdapter(R.layout.item_lable,null) }
    private val mHistorySearchAdapter: HistorySearchAdapter by lazy { HistorySearchAdapter(R.layout.item_lable,null) }

    override fun isAddToolbar(): Boolean = false

    override fun getLayoutId(): Int = R.layout.activity_search

    override fun initView() {
        super.initView()
        initHotSearchView()
        initSearchView()
        initSearchHistory()
    }

    override fun initData() {
        mViewModel.getHotSearch()
        mViewModel.getHistorySearch()
    }

    private fun initHotSearchView() {
        mRvHotSearch.layoutManager = MyFlexboxLayoutManager(this)
        mRvHotSearch.adapter = mHotSearchAdapter
        mHotSearchAdapter.setOnItemChildClickListener { adapter, _, position ->
            val str = (adapter.data[position] as HotSearchRsp).name
            tv.setText(str)
            mViewModel.search(page,str)
        }
        val headView = View.inflate(this,R.layout.layout_search_head,null)
        headView.mTvHeadView.text = getString(R.string.hot_search)
        mHotSearchAdapter.addHeaderView(headView)
    }

    private fun initSearchHistory() {
        mRvHistory.layoutManager = MyFlexboxLayoutManager(this)
        mRvHistory.adapter = mHistorySearchAdapter

        mHistorySearchAdapter.setOnItemChildClickListener { adapter, _, position ->
            val str = (adapter.data[position] as Record).name
            tv.setText(str)
            tv.setSelection(str.length)
            mViewModel.search(page,str)
        }
        val headView = View.inflate(this,R.layout.layout_search_head,null)
        headView.mTvHeadView.text = getString(R.string.search_history)
        mHistorySearchAdapter.addHeaderView(headView)
    }

    private fun initSearchView() {
        tv = mSearchView.findViewById(R.id.search_src_text)
        tv.setTextColor(Color.WHITE)
        tv.setLinkTextColor(Color.WHITE)

        val ivSearch = mSearchView.findViewById<ImageView>(R.id.search_mag_icon)
        ivSearch.setImageResource(R.drawable.ic_search)

        val ivClose = mSearchView.findViewById<ImageView>(R.id.search_close_btn)
        ivClose.setImageResource(R.drawable.ic_close)
        ivClose.setOnClickListener {
            tv.setText("")
            page = 0
            showHistory()
            showHotSearch()
            hideKeyboard()
            mArticleAdapter.setNewData(null)
        }

        val ivGo = mSearchView.findViewById<ImageView>(R.id.search_go_btn)
        ivGo.setImageResource(R.drawable.ic_go)

        with(mSearchView){
            setIconifiedByDefault(false)
            isIconified = false
            isSubmitButtonEnabled = true
            setOnQueryTextListener(object : SearchView.OnQueryTextListener{

                override fun onQueryTextSubmit(str: String?): Boolean {
                    hideKeyboard()
                    submitSearch(str)
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    return true
                }

            })

            //这并不起效
            setOnCloseListener {
                false
            }
        }
    }

    private fun submitSearch(str: String?) {
        str?.let {
            mViewModel.search(page,str)
        }
    }

    private fun showHistory(){
        mRvHistory.visibility = View.VISIBLE
    }

    private fun hideHistory() {
        mRvHistory.visibility = View.GONE
    }

    private fun showHotSearch(){
        mRvHotSearch.visibility = View.VISIBLE
    }

    private fun hideHotSearch() {
        mRvHotSearch.visibility = View.GONE
    }

    override fun onRefreshData() {
        if(tv.str().isEmpty()){
            msrlRefresh.isRefreshing = false
            return
        }
        page = 0
        mViewModel.search(page,tv.str())
    }

    override fun onLoadMore() {
        mViewModel.search(++page,tv.str())
    }

    override fun dataObserver() {
        super.dataObserver()
        mViewModel.mSearchData.observe(this, Observer {
            it?.let {
                showSearchResult(it.data.datas)
            }
            addHistorySearch(tv.str())
        })

        mViewModel.mHotKeyData.observe(this, Observer {
            it?.let {
                showHotSearch(it.data)
            }
        })

        mViewModel.mSearchHistory.observe(this, Observer {
            showHistoryResult(it)
        })
    }

    private fun addHistorySearch(str: String) {
        mViewModel.addHistorySearch(str)
    }

    private fun showHistoryResult(data: List<Record>?) {
        data?.let {
            mHistorySearchAdapter.replaceData(data)
        }
    }

    private fun showHotSearch(data: List<HotSearchRsp>) {
        mHotSearchAdapter.replaceData(data)
    }

    private fun showSearchResult(datas: List<Article>) {
        addData(datas)
        hideHistory()
        hideHotSearch()
    }
}
