package com.kkaka.wanandroid.common.article.data

/**
 * author：  HyZhan
 * created： 2018/10/22 20:47
 * desc：    TODO
 */
data class Article(
        var id: Int,
        var author: String,
        var chapterName: String?,
        var desc: String,
        var link: String,
        var originId: Int,
        var title: String,
        var collect: Boolean,
        var superChapterName: String?,
        var niceDate: String,
        var niceShareDate: String,
        var shareUser: String,
        var fresh: Boolean

)