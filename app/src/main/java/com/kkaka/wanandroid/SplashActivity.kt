package com.kkaka.wanandroid

import com.kkaka.common.base.BaseActivity
import io.reactivex.Observable
import org.jetbrains.anko.startActivity
import java.util.concurrent.TimeUnit

class SplashActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
        super.initView()
        if(BuildConfig.DEBUG){
            startActivity<MainActivity>()
            return
        }
        disposable = Observable.timer(3,TimeUnit.SECONDS).subscribe{
            startActivity<MainActivity>()
        }
    }
}
