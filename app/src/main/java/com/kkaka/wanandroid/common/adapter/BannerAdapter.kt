package com.kkaka.wanandroid.common.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kkaka.wanandroid.home.data.BannerRsp

/**
 * @author Laizexin on 2019/12/6
 * @description
 */
class BannerAdapter(layoutId : Int,datas : List<BannerRsp>?) : BaseQuickAdapter<BannerRsp, BaseViewHolder>(layoutId,datas) {

    override fun convert(helper: BaseViewHolder, item: BannerRsp?) {

    }

}