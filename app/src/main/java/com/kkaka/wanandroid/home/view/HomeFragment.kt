package com.kkaka.wanandroid.home.view

import android.arch.lifecycle.Observer
import android.view.View
import com.kkaka.wanandroid.R
import com.kkaka.wanandroid.common.GlideImageLoader
import com.kkaka.wanandroid.common.article.view.ArticleFragment
import com.kkaka.wanandroid.home.data.BannerRsp
import com.kkaka.wanandroid.home.viewmodel.HomeViewModel
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.layout_home_headview.view.*

/**
 * @author Laizexin on 2019/12/2
 * @description
 */
class HomeFragment : ArticleFragment<HomeViewModel>() {

    private var page = 0
    private lateinit var mBanner: Banner
    private var bannerImgs = mutableListOf<String>()
    private var bannerTitles = mutableListOf<String>()
    private var bannerUrls = mutableListOf<String>()


    override fun initView() {
        super.initView()
        addHeadView()
    }

    private fun addHeadView() {
        val headView = View.inflate(activity, R.layout.layout_home_headview,null)
        mBanner = headView.mBanner
        mBanner
            .setImageLoader(GlideImageLoader())
            .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
            .setDelayTime(3000)
            .setBannerAnimation(Transformer.FlipHorizontal)
            .setOnBannerListener {
                //TODO 跳转
            }
        mArticleAdapter.addHeaderView(headView)
    }

    override fun initData() {
        super.initData()
        page = 0
        mViewModel.getArticle(page)
        mViewModel.getBanner()
    }

    override fun dataObserver() {
        super.dataObserver()

        mViewModel.mHomeArticleData.observe(this, Observer { response ->
            response?.let {
                addData(it.data.datas)
            }
        })

        mViewModel.mBannerData.observe(this, Observer { response ->
            response?.let {
                setBannerData(it.data)
            }
        })
    }

    private fun setBannerData(data: List<BannerRsp>) {

        bannerImgs.clear()
        bannerTitles.clear()
        bannerUrls.clear()

        data.forEach {
            bannerImgs.add(it.imagePath)
            bannerTitles.add(it.title)
            bannerUrls.add(it.url)
        }
        mBanner.setImages(bannerImgs).setBannerTitles(bannerTitles).start()
    }

    override fun onRefreshData() {
        page = 0
        mViewModel.getArticle(page)

    }

    override fun onLoadMore() {
        page++
        mViewModel.getArticle(page)
    }
}