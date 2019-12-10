package com.kkaka.wanandroid

import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.kkaka.common.base.BaseActivity
import com.kkaka.common.constant.Constant
import com.kkaka.common.constant.Constant.HOME
import com.kkaka.common.state.UserState
import com.kkaka.common.state.collect.CollectState
import com.kkaka.common.state.login.LoginSucListener
import com.kkaka.common.state.login.LoginSucState
import com.kkaka.common.utils.Preference
import com.kkaka.wanandroid.account.data.UserContext
import com.kkaka.wanandroid.account.view.LoginActivity
import com.kkaka.wanandroid.home.view.HomeFragment
import com.kkaka.wanandroid.nagivation.view.NagivationFragment
import com.kkaka.wanandroid.wechat.view.WeChatFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_drawer_header.*
import kotlinx.android.synthetic.main.layout_drawer_header.view.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.jetbrains.anko.startActivity

class MainActivity : BaseActivity() ,LoginSucListener{

    private lateinit var mCurrentFragment: Fragment
    private val homeFragment :HomeFragment by lazy { HomeFragment() }
    private val weChatFragment :WeChatFragment by lazy { WeChatFragment() }
    private val nagivationFragment :NagivationFragment by lazy { NagivationFragment() }
    private lateinit var headerView : View
    private var mUsername : String by Preference(Constant.USERNAME_KEY,"未登录")

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
        LoginSucState.addListener(this)
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
        headerView = navigation.getHeaderView(0)

        headerView.tv_name.text = mUsername
        headerView.iv_logo.setOnClickListener {
            UserContext.instance.login(this)
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
        when(position){
            Constant.HOME -> {
                setToolBar(toolbar,getString(R.string.navigation_home))
                changeFragment(homeFragment)
            }

            Constant.WE_CHAT ->{
                setToolBar(toolbar,getString(R.string.navigation_wechat))
                changeFragment(weChatFragment)
            }

            Constant.NAGIVATION ->{
                setToolBar(toolbar,getString(R.string.navigation_navigation))
                changeFragment(nagivationFragment)
            }
            else -> {

            }
        }
    }

    private fun changeFragment(to : Fragment){
        if (mCurrentFragment != to) {
            val transaction = supportFragmentManager.beginTransaction()
            if (to.isAdded)
                transaction.hide(mCurrentFragment).show(to)
            else
                transaction.hide(mCurrentFragment).add(R.id.content, to)
            transaction.commit()
            mCurrentFragment = to
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home ->{
                drawerMain.openDrawer(Gravity.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun logout() {
        UserContext.instance.logoutSuccess()
    }

    private fun goCollectActivity() {
        UserContext.instance.goCollectActivity(this)
    }

    override fun loginSuccess(username: String, collectIds: List<Int>?) {
        mUsername = username
        headerView.tv_name.text = username
    }

    public fun onShow(){
        fab.animate().translationX(0f).interpolator = AccelerateDecelerateInterpolator()
        mNavigationBar.animate().translationY(0f).interpolator = AccelerateDecelerateInterpolator()
    }

    public fun onHide(){
        val layoutParams = fab.layoutParams
        val leftMargin = (layoutParams as ViewGroup.MarginLayoutParams).marginEnd
        fab.animate().translationX(fab.width.toFloat() + leftMargin).interpolator = AccelerateDecelerateInterpolator()

        mNavigationBar.animate().translationY(mNavigationBar.height.toFloat()).interpolator = AccelerateDecelerateInterpolator()
    }

    override fun onDestroy() {
        super.onDestroy()
        LoginSucState.removeListener(this)
    }

}
