package com.kkaka.wanandroid.about.view

import com.kkaka.common.base.BaseActivity
import com.kkaka.wanandroid.BuildConfig
import com.kkaka.wanandroid.R
import com.kkaka.wanandroid.WebActivity
import com.mikepenz.aboutlibraries.LibsBuilder
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.jetbrains.anko.startActivity

class AboutActivity : BaseActivity() {

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

    }
}
