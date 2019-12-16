package com.kkaka.wanandroid.common

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager

/**
 * @author Laizexin on 2019/12/16
 * @description
 */
class MyFlexboxLayoutManager : FlexboxLayoutManager{

    constructor(context: Context): super(context)

    constructor(context: Context, flexDirection: Int): super(context, flexDirection)

    constructor(context: Context, flexDirection: Int,flexWrap : Int): super(context,flexDirection,flexWrap)

    override fun generateLayoutParams(lp: ViewGroup.LayoutParams?): RecyclerView.LayoutParams {
        return if(lp is RecyclerView.LayoutParams){
            FlexboxLayoutManager.LayoutParams(lp)
        }else if(lp is ViewGroup.MarginLayoutParams){
            FlexboxLayoutManager.LayoutParams(lp)
        }else{
            super.generateLayoutParams(lp)
        }
    }
}