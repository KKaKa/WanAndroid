package com.kkaka.wanandroid.project.data

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * @author Laizexin on 2019/12/20
 * @description
 */
class ProjectEntity(var type : Int,var project : Project) : MultiItemEntity{

    companion object {
        val LEFT = 1
        val RIGHT = 2
    }

    override fun getItemType(): Int {
        return type
    }

}