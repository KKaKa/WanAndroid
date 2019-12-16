package com.kkaka.wanandroid.common.adapter

import androidx.annotation.LayoutRes
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kkaka.wanandroid.R
import com.kkaka.wanandroid.search.data.db.Record

/**
 * @author Laizexin on 2019/12/16
 * @description
 */
class HistorySearchAdapter(@LayoutRes layoutId:  Int,history : List<Record>?) : BaseQuickAdapter<Record,BaseViewHolder>(layoutId,history){

    override fun convert(helper: BaseViewHolder, item: Record?) {
        with(helper){
            setText(R.id.mLable,item?.name)
            addOnClickListener(R.id.mLable)
        }
    }
}