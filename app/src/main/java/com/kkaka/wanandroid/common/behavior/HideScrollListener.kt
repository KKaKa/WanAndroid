package com.kkaka.wanandroid.common.behavior

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * @author Laizexin on 2019/12/9
 * @description
 */
abstract class HideScrollListener : RecyclerView.OnScrollListener() {

    private val HIDE_THRESHOLD = 20
    private var scrolledDistance = 0
    private var controlsVisible = true

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val firstVisibleItemPosition =
            (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

        if (firstVisibleItemPosition == 0){
            if(!controlsVisible){
                onShow()
                controlsVisible = true
            }
        }else{
            if(scrolledDistance > HIDE_THRESHOLD && controlsVisible){
                onHide()
                controlsVisible = false
                scrolledDistance = 0
            }else if(scrolledDistance < -HIDE_THRESHOLD && !controlsVisible){
                onShow()
                controlsVisible = true
                scrolledDistance = 0
            }
            if((controlsVisible && dy>0) || (!controlsVisible && dy<0)) {
                scrolledDistance += dy;
            }
        }
    }

    public abstract fun onHide()
    public abstract fun onShow()
}