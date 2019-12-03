package com.kkaka.wanandroid

import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.kkaka.common.base.BaseActivity
import com.kkaka.common.constant.Constant.HOME
import com.kkaka.wanandroid.home.view.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_drawer_header.view.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class MainActivity : BaseActivity() {

    private lateinit var mCurrentFragment: Fragment
    private val homeFragment :HomeFragment by lazy { HomeFragment() }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        super.initView()
        initToolbar()
        initDrawerLayout()
        initFabButton()
        initBottomNavigationBar()
        defauleFragment()
    }

    private fun defauleFragment() {
        mCurrentFragment = homeFragment
        supportFragmentManager.beginTransaction().add(R.id.content,homeFragment).commit()
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

    private fun initFabButton() {
        fab.setOnClickListener{}
        mNavigationBar.setFab(fab)
    }

    private fun initBottomNavigationBar() {
        mNavigationBar.setMode(BottomNavigationBar.MODE_DEFAULT)
            .setActiveColor(R.color.colorPrimaryDark)
            .addItem(BottomNavigationItem(R.mipmap.navigation_home,getString(R.string.navigation_home)))
            .addItem(BottomNavigationItem(R.mipmap.navigation_wechat,getString(R.string.navigation_wechat)))
            .addItem(BottomNavigationItem(R.mipmap.navigation_system,getString(R.string.navigation_system)))
            .addItem(BottomNavigationItem(R.mipmap.navigation_navigation,getString(R.string.navigation_navigation)))
            .addItem(BottomNavigationItem(R.mipmap.nagivation_project,getString(R.string.navigation_project)))
            .setBarBackgroundColor(R.color.white)
            .setFirstSelectedPosition(HOME)
            .initialise()
        mNavigationBar.setTabSelectedListener(object :BottomNavigationBar.OnTabSelectedListener{

            override fun onTabUnselected(position: Int) {}

            override fun onTabReselected(position: Int) {}

            override fun onTabSelected(position: Int) {
                switchFragment(position)
            }

        })
    }

    private fun switchFragment(position: Int) {

    }

    private fun logout() {

    }

    private fun goCollectActivity() {

    }

}
