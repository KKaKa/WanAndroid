package com.kkaka.wanandroid.common.adapter

import android.graphics.Color
import android.support.v4.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kkaka.wanandroid.R
import com.kkaka.wanandroid.nagivation.data.NagivationCategoryRsp
import kotlinx.android.synthetic.main.item_category.view.*

/**
 * @author Laizexin on 2019/12/10
 * @description
 */
class CategoryAdapter (layoutId : Int,categoryList : List<NagivationCategoryRsp>?) : BaseQuickAdapter<NagivationCategoryRsp,BaseViewHolder>(layoutId,categoryList){

    public var selectPosition = 0

    override fun convert(helper: BaseViewHolder, item: NagivationCategoryRsp?) {
        with(helper){
            setText(R.id.mCategory,item?.name)
            addOnClickListener(R.id.mCategory)

            if(adapterPosition == selectPosition){
                setBackgroundColor(R.id.mCategory,ContextCompat.getColor(mContext,R.color.lightGray))
                setTextColor(R.id.mCategory,ContextCompat.getColor(mContext,R.color.colorPrimaryDark))
            }else{
                setBackgroundColor(R.id.mCategory, 0)
                setTextColor(R.id.mCategory,Color.GRAY)
            }
        }
    }


}