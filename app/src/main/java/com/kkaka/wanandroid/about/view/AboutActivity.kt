package com.kkaka.wanandroid.about.view

import android.arch.lifecycle.Observer
import android.support.v4.content.ContextCompat
import com.afollestad.materialdialogs.MaterialDialog
import com.kkaka.common.base.BaseActivity
import com.kkaka.common.base.LifecycleActivity
import com.kkaka.wanandroid.BuildConfig
import com.kkaka.wanandroid.R
import com.kkaka.wanandroid.WebActivity
import com.kkaka.wanandroid.about.viewmodel.AboutViewModel
import com.mikepenz.aboutlibraries.LibsBuilder
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.jetbrains.anko.startActivity

class AboutActivity : LifecycleActivity<AboutViewModel>() {

    override fun getLayoutId(): Int = R.layout.activity_about

    override fun initView() {
        super.initView()
        setToolBar(toolbar,getString(R.string.menu_about))

        mLicense.setOnClickListener {
            startActivity<LicenseActivity>()
        }

        mGithub.setOnClickListener {
            startActivity<WebActivity>("url" to getString(R.string.github_url),"title" to "KKaKa")
        }

        mJianshu.setOnClickListener {
            startActivity<WebActivity>("url" to getString(R.string.jianshu_url),"title" to "益力多不多")
        }

        mVersion.setValue(BuildConfig.VERSION_NAME)

        mCache.setOnClickListener {
            MaterialDialog.Builder(this).title(R.string.tips)
                .content(R.string.ensure_clear_cache)
                .positiveText(R.string.ensure)
                .positiveColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))
                .onPositive { _, _ ->
                    mViewModel.clearCache()
                }
                .negativeText(R.string.cancel)
                .negativeColor(ContextCompat.getColor(this,R.color.gray))
                .onNeutral { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

    }

    override fun initData() {
        super.initData()
        mViewModel.getCacheSize()
        showSuccess()
    }

    override fun dataObserver() {
        mViewModel.mCacheData.observe(this, Observer {
            it?.let {
                changeCache(it)
            }
        })
    }

    private fun changeCache(it: String) {
        mCache.setValue(it)
    }

}
