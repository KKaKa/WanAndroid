package com.kkaka.wanandroid.common

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.kkaka.common.R
import com.youth.banner.loader.ImageLoader

/**
 * @author Laizexin on 2019/12/6
 * @description
 */
class GlideImageLoader : ImageLoader() {

    override fun displayImage(context: Context, path: Any?, imageView: ImageView) {
        val options = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

        Glide.with(context)
            .load(path)
            .apply(options)
            .into(imageView)
    }
}