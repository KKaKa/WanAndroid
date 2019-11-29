package com.kkaka.common.base

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.kkaka.common.common.AppManager
import io.reactivex.disposables.Disposable

/**
 * @author Laizexin on 2019/11/28
 * @description
 */
abstract class BaseActivity : AppCompatActivity(){

    var disposable : Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        AppManager.instance.addActivity(this)
        initView()
        initData()
    }

    open fun initData() {
    }

    open fun initView() {
    }

    abstract fun getLayoutId(): Int

    /**
     *  设置 toolbar 标题
     */
    fun setToolBar(toolbar: Toolbar, title: String) {
        toolbar.title = title
        setSupportActionBar(toolbar)
        val supportActionBar = supportActionBar

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
        AppManager.instance.removeActivity(this)
    }

}