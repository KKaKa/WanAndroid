package com.kkaka.wanandroid.system.data

import com.kkaka.wanandroid.common.article.data.Article

/**
 * @author Laizexin on 2019/12/17
 * @description
 */
data class SystemAtricleRsp (
    var curPage: Int,
    var datas: List<Article>,
    var pageCount: Int,
    var total: Int
)