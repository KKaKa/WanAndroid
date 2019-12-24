package com.kkaka.wanandroid.common.adapter

import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kkaka.common.ext.loadImage
import com.kkaka.common.ext.toHtml
import com.kkaka.wanandroid.R
import com.kkaka.wanandroid.project.data.ProjectEntity



/**
 * @author Laizexin on 2019/12/20
 * @description
 */
class ProjectListAdapter(datas : List<ProjectEntity>?) : BaseMultiItemQuickAdapter<ProjectEntity,BaseViewHolder>(datas){

    private lateinit var listener : OnClickListener

    interface OnClickListener{
        fun onClick(title : String,link : String)
    }

    fun setOnClickListener(listener : OnClickListener){
        this.listener = listener
    }

    init {
        addItemType(ProjectEntity.LEFT, R.layout.item_project_left)
        addItemType(ProjectEntity.RIGHT, R.layout.item_project_right)
    }

    override fun convert(helper: BaseViewHolder, item: ProjectEntity?) {
        item?.let {
            with(helper){
                (getView(R.id.mIvPic) as ImageView).loadImage(mContext,it.project.envelopePic,width = 0,height = 0)
                setText(R.id.mTvAuthor,it.project.author)
                setText(R.id.mTvDesc,it.project.desc.toHtml())
                    setText(R.id.mTvZan,it.project.zan.toString())
                setOnClickListener(R.id.content) {
                    listener.onClick(
                        item.project.title,
                        item.project.link
                    )
                }
            }
        }
    }

}
