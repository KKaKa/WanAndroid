package com.kkaka.wanandroid.system.data

import com.chad.library.adapter.base.entity.SectionEntity

/**
 * @author Laizexin on 2019/12/17
 * @description
 */
class TopMenu : SectionEntity<SecondMenuRsp> {

    var isMore: Boolean = false
    lateinit var childrens: List<SecondMenuRsp>

    constructor(isHeader: Boolean, header: String, isMroe: Boolean,childrens: List<SecondMenuRsp>) : super(isHeader, header) {
        this.isMore = isMroe
        this.childrens = childrens
    }

    constructor(t: SecondMenuRsp) : super(t)

}