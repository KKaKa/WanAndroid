package com.kkaka.wanandroid.search.data

import com.kkaka.wanandroid.common.article.data.Article

/**
 * @author Laizexin on 2019/12/11
 * @description
 */
data class SearchResultRsp (
    var curPage: Int,
    var datas: List<Article>,
    var offset: Int,
    var over: Boolean,
    var pageCount: Int,
    var size: Int,
    var total: Int
)