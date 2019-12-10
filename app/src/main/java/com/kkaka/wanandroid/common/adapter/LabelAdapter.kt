package com.kkaka.wanandroid.common.adapter

import android.graphics.Color
import android.support.v4.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kkaka.wanandroid.R
import com.kkaka.wanandroid.nagivation.data.LableRsp

/**
 * @author Laizexin on 2019/12/10
 * @description
 */
class LabelAdapter (layoutId : Int,lables : List<LableRsp>?) : BaseQuickAdapter<LableRsp,BaseViewHolder>(layoutId,lables){

    override fun convert(helper: BaseViewHolder, item: LableRsp?) {
        with(helper){
            setText(R.id.mLable,item?.title)
            addOnClickListener(R.id.mLable)
        }
    }
}