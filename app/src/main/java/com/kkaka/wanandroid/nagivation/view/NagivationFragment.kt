package com.kkaka.wanandroid.nagivation.view

import android.arch.lifecycle.Observer
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.google.android.flexbox.FlexboxLayoutManager
import com.kkaka.common.base.LifecycleFragment
import com.kkaka.wanandroid.MainActivity
import com.kkaka.wanandroid.R
import com.kkaka.wanandroid.WebActivity
import com.kkaka.wanandroid.common.adapter.CategoryAdapter
import com.kkaka.wanandroid.common.adapter.LabelAdapter
import com.kkaka.wanandroid.common.behavior.HideScrollListener
import com.kkaka.wanandroid.nagivation.data.LableRsp
import com.kkaka.wanandroid.nagivation.data.NagivationCategoryRsp
import com.kkaka.wanandroid.nagivation.viewmodel.NagivationViewModel
import kotlinx.android.synthetic.main.fragment_nagivation.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * @author Laizexin on 2019/12/10
 * @description
 */
class NagivationFragment : LifecycleFragment<NagivationViewModel>(){

    private lateinit var mActivity : MainActivity
    private val mCategoryAdapter: CategoryAdapter by lazy { CategoryAdapter(R.layout.item_category,null) }
    private val mLableAdapter: LabelAdapter by lazy { LabelAdapter(R.layout.item_lable,null) }
    private lateinit var mNagivationCategoryRspList: List<NagivationCategoryRsp>

    override fun getLayoutId(): Int = R.layout.fragment_nagivation

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mActivity = activity as MainActivity
    }

    override fun initView() {
        super.initView()

        mRvCategory.layoutManager = LinearLayoutManager(activity)
        mRvCategory.adapter = mCategoryAdapter

        mRvCategory.addOnScrollListener(object : HideScrollListener(){
            override fun onHide() {
                mActivity.onHide()
            }

            override fun onShow() {
                mActivity.onShow()
            }
        })

        mCategoryAdapter.setOnItemChildClickListener { adapter, view, position ->
            setSelectCategory(position)
            setSelectLables(position)
        }

        mRvLabel.layoutManager = FlexboxLayoutManager(activity)
        mRvLabel.adapter = mLableAdapter

//        mRvLabel.addOnScrollListener(object : HideScrollListener(){
//            override fun onHide() {
//                mActivity.onHide()
//            }
//
//            override fun onShow() {
//                mActivity.onShow()
//            }
//        })

        mLableAdapter.setOnItemChildClickListener { adapter, view, position ->
            startActivity<WebActivity>("url" to (adapter.data[position] as LableRsp).link,
                "title" to (adapter.data[position] as LableRsp).title)
        }
    }

    override fun initData() {
        super.initData()
        mViewModel.getCategory()
    }

    override fun dataObserver() {
        mViewModel.mCategoryData.observe(this, Observer {response ->
            response?.let {
                mNagivationCategoryRspList = it.data
                mCategoryAdapter.addData(it.data)
                setSelectCategory(0)
                setSelectLables(0)
            }
        })
    }

    private fun setSelectLables(position: Int) {
        mLableAdapter.replaceData(mNagivationCategoryRspList[position].articles)
    }

    private fun setSelectCategory(position : Int){
        mCategoryAdapter.selectPosition = position
        mCategoryAdapter.notifyDataSetChanged()
    }
}
