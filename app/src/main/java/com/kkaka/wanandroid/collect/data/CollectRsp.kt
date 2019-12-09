package com.kkaka.wanandroid.collect.data

import com.kkaka.wanandroid.common.article.data.Article

data class CollectRsp(
        var curPage: Int,
        var datas: List<Article>,
        var total: Int
)