package com.kkaka.wanandroid.home.data

import com.kkaka.wanandroid.common.article.data.Article

/**
 * @author Laizexin on 2019/12/2
 * @description
 */
data class HomeArticleRsp (
    var curPage: Int,
    var datas: List<Article>,
    var offset: Int,
    var over: Boolean,
    var pageCount: Int,
    var size: Int,
    var total: Int
)