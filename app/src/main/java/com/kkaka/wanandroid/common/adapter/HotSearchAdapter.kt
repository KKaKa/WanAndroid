package com.kkaka.wanandroid.common.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kkaka.wanandroid.R
import com.kkaka.wanandroid.search.data.HotSearchRsp

/**
 * @author Laizexin on 2019/12/11
 * @description
 */
class HotSearchAdapter(layoutId : Int,lables : List<HotSearchRsp>?) : BaseQuickAdapter<HotSearchRsp, BaseViewHolder>(layoutId,lables){

    override fun convert(helper: BaseViewHolder, item: HotSearchRsp?) {
        with(helper){
            setText(R.id.mLable,item?.name)
            addOnClickListener(R.id.mLable)
        }
    }
}