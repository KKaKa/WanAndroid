package com.kkaka.wanandroid

import android.support.v7.widget.Toolbar
import android.widget.FrameLayout
import com.just.agentweb.AgentWeb
import com.kkaka.common.base.BaseActivity
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : BaseActivity() {

    private lateinit var mAgentWeb : AgentWeb

    override fun getLayoutId(): Int = R.layout.activity_web

    override fun initView() {
        super.initView()
        val url = intent.getStringExtra("url")
        val title = intent.getStringExtra("title")
        setToolBar(toolbar as Toolbar,title)

        mAgentWeb = AgentWeb.with(this)
            .setAgentWebParent(mContent, FrameLayout.LayoutParams(-1,-1))
            .useDefaultIndicator()
            .createAgentWeb()
            .ready().go(url)

        val setting = mAgentWeb.agentWebSettings.webSettings
        // 设置 支持缩放
        setting.builtInZoomControls = true
    }

    override fun onBackPressed() {
        if(!mAgentWeb.back()){
            finish()
        }
    }
}
