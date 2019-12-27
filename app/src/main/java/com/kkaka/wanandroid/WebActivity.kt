package com.kkaka.wanandroid

import android.content.Intent
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import com.just.agentweb.AgentWeb
import com.kkaka.common.base.BaseActivity
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : BaseActivity() {

    private lateinit var mAgentWeb : AgentWeb
    private lateinit var url :String
    private lateinit var title :String

    override fun getLayoutId(): Int = R.layout.activity_web

    override fun initView() {
        super.initView()
        url = intent.getStringExtra("url")
        title = intent.getStringExtra("title")
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.web_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.web_menu_share ->{
                Intent().run{
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT,
                        getString(
                            R.string.share_type_url,
                            getString(R.string.app_name),
                            title,url
                        )
                    )
                    type = "text/plain"
                    startActivity(Intent.createChooser(this,getString(R.string.share_type_title)))
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
