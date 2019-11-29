package com.kkaka.wanandroid

import android.support.v7.app.ActionBarDrawerToggle
import com.kkaka.common.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_drawer_header.view.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        super.initView()
        initToolbar()
        initDrawerLayout()
        initBottomNavigationBar()
        initFloatButton()
    }

    private fun initToolbar() {
        setToolBar(toolbar,getString(R.string.app_name))
        val actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerMain, toolbar, R.string.app_name, R.string.app_name)
        drawerMain.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
    }

    private fun initDrawerLayout(){
        val headerView = navigation.getHeaderView(0)

        //TODO 登录
        headerView.tv_name.text = ""
        headerView.iv_logo.setOnClickListener {

        }

        navigation.setNavigationItemSelectedListener {

            when(it.itemId) {
                R.id.nav_menu_collect -> {
                    goCollectActivity()
                }
                R.id.nav_menu_logout -> {
                    logout()
                }
            }
            drawerMain.closeDrawers()
            true
        }
    }

    private fun logout() {

    }

    private fun goCollectActivity() {

    }

    private fun initBottomNavigationBar() {

    }

    private fun initFloatButton() {

    }

}
