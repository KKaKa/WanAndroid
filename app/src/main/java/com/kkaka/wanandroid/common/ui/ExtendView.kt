package com.kkaka.wanandroid.common.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.view.LayoutInflater
import com.kkaka.wanandroid.R
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import org.jetbrains.anko.find




/**
 * @author Laizexin on 2019/12/23
 * @description
 */
class ExtendView : FrameLayout {

    private lateinit var mIvIcon : ImageView
    private lateinit var mTvTitle : TextView
    private lateinit var mTvValue : TextView
    private lateinit var mIvExtend : ImageView

    private var mTitle: CharSequence? = null
    private var mValue: CharSequence? = null
    private var mIcon: Drawable? = null
    private var mIconVisible: Boolean = false
    private var extendAble: Boolean = false

    constructor(context: Context) : this(context,null)

    constructor(context: Context,attrs : AttributeSet?) : this(context,attrs,0)

    constructor(context: Context,attrs : AttributeSet?,defStyleAttr :Int) : super(context,attrs,defStyleAttr){
        initView(context,attrs,defStyleAttr)
    }

    private fun initView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        val view = LayoutInflater.from(context).inflate(R.layout.item_extend_view, this)

        mIvIcon = view.find(R.id.mIcon)
        mTvTitle = view.find(R.id.mTitle)
        mTvValue = view.find(R.id.mValue)
        mIvExtend = view.find(R.id.mExtend)

        val obtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.ExtendView, defStyleAttr, 0)
        if(obtainStyledAttributes.hasValue(R.styleable.ExtendView_iconImage)){
            mIcon = obtainStyledAttributes.getDrawable(R.styleable.ExtendView_iconImage)
            mIvIcon.setImageDrawable(mIcon)
        }
        if(obtainStyledAttributes.hasValue(R.styleable.ExtendView_title)){
            mTitle = obtainStyledAttributes.getString(R.styleable.ExtendView_title)
            mTvTitle.text = mTitle
        }
        if(obtainStyledAttributes.hasValue(R.styleable.ExtendView_value)){
            mValue = obtainStyledAttributes.getString(R.styleable.ExtendView_value)
            mTvValue.text = mValue
        }
        if(obtainStyledAttributes.hasValue(R.styleable.ExtendView_value)){
            mIconVisible = obtainStyledAttributes.getBoolean(R.styleable.ExtendView_iconImageVisible,true)
            mIvIcon.visibility = if(mIconVisible) View.VISIBLE else View.GONE
        }
        if(obtainStyledAttributes.hasValue(R.styleable.ExtendView_extendAble)){
            extendAble = obtainStyledAttributes.getBoolean(R.styleable.ExtendView_extendAble,true)
            mIvExtend.visibility = if(extendAble) View.VISIBLE else View.GONE
        }
        obtainStyledAttributes.recycle()
    }

    public fun setIconVisibility(iconVisibility: Boolean) {
        mIvIcon.visibility = if (iconVisibility) View.VISIBLE else View.GONE
    }

    public fun setValue(value : CharSequence){
        mTvValue.text = value
    }

}