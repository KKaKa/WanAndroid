package com.kkaka.wanandroid.common.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kkaka.wanandroid.R
import com.kkaka.wanandroid.common.article.data.Article

/**
 * @author Laizexin on 2019/12/2
 * @description
 */
class ArticleAdapter(layoutId : Int,datas : List<Article>?) : BaseQuickAdapter<Article,BaseViewHolder>(layoutId,datas){

    override fun convert(viewHoler: BaseViewHolder, item: Article?) {
        item?.let {
            viewHoler.setText(R.id.mTvAuthor,if(it.author.isEmpty()) it.shareUser else it.author)
                .setText(R.id.mTvTitle,it.title)
                .setText(R.id.mTvTime,if(it.niceDate.isEmpty()) it.niceDate else it.niceShareDate)
                .setText(R.id.mTvCategory,buildCategory(it))
                .setImageResource(R.id.mIvCollect,isCollect(it.collect))
                .setVisible(R.id.mIvNews,it.fresh)
                .addOnClickListener(R.id.mIvCollect)
        }
    }

    private fun isCollect(collect: Boolean): Int = if(collect) R.drawable.ic_collection else R.drawable.ic_uncollection

    private fun buildCategory(it: Article): String {
        return when{
            it.superChapterName.isNullOrEmpty() && it.chapterName.isNullOrEmpty() ->  ""
            it.superChapterName.isNullOrEmpty() ->  it.chapterName ?: ""
            it.chapterName.isNullOrEmpty() -> it.superChapterName ?: ""
            else -> "${it.superChapterName} / ${it.chapterName}"
        }
    }


}