package com.kkaka.wanandroid.system.data

import com.chad.library.adapter.base.entity.SectionEntity

/**
 * @author Laizexin on 2019/12/17
 * @description
 */
class TopMenu : SectionEntity<SecondMenuRsp> {

    var isMore: Boolean = false

    constructor(isHeader: Boolean, header: String, isMroe: Boolean) : super(isHeader, header) {
        this.isMore = isMroe
    }

    constructor(t: SecondMenuRsp) : super(t) {}

}