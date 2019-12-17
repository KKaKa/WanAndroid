package com.kkaka.wanandroid.common.adapter

import android.support.v7.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kkaka.wanandroid.R
import com.kkaka.wanandroid.system.data.TopMenu
import com.kkaka.wanandroid.system.data.TopMenuRsp

/**
 * @author Laizexin on 2019/12/17
 * @description
 */
class SystemMenuAdapter(layoutId : Int,headLayoutId :Int,topMenu : List<TopMenu>?) : BaseSectionQuickAdapter<TopMenu, BaseViewHolder>(layoutId,headLayoutId,topMenu) {

    override fun convertHead(helper: BaseViewHolder, item: TopMenu?) {
        with(helper){
            setText(R.id.mTvHead,item?.header)
            addOnClickListener(R.id.mTvMore)
        }
    }

    override fun convert(helper: BaseViewHolder, item: TopMenu?) {
        val topMenuRsp = item?.t
        helper.setText(R.id.mTvSecond,topMenuRsp?.name)
    }

}