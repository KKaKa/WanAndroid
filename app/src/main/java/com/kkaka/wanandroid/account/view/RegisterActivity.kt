package com.kkaka.wanandroid.account.view

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.kkaka.common.base.LifecycleActivity
import com.kkaka.common.ext.str
import com.kkaka.wanandroid.R
import com.kkaka.wanandroid.account.data.UserContext
import com.kkaka.wanandroid.account.viewmodel.AccountViewModel
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class RegisterActivity : LifecycleActivity<AccountViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun initView() {
        super.initView()

        mBtnRegist.setOnClickListener {
            mViewModel.regist(mTvAccount.str(),mTvPassword.str(),mTvRepassword.str())
        }

        mBtnRegist.isEnabled = false

        mTvLogin.setOnClickListener {
            startActivity<LoginActivity>()
            finish()
        }

        //TODO 好烂的办法 有没有什么好办法 不使用databinding的情况下

        mTvAccount.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                mBtnRegist.isEnabled = !mTvPassword.str().isEmpty() && !mTvRepassword.str().isEmpty() &&!s.isNullOrEmpty()
            }

        })

        mTvPassword.addTextChangedListener(object : TextWatcher{

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                mBtnRegist.isEnabled = !mTvAccount.str().isEmpty() && !mTvRepassword.str().isEmpty() && !s.isNullOrEmpty()
            }

        })

        mTvRepassword.addTextChangedListener(object : TextWatcher{

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                mBtnRegist.isEnabled = !mTvPassword.str().isEmpty() && !mTvRepassword.str().isEmpty() && !s.isNullOrEmpty()
            }

        })

        showSuccess()
    }

    override fun dataObserver() {
        mViewModel.mRegistData.observe(this, Observer {
            it?.data?.let {
                toast(R.string.regist_success)
                UserContext.instance.loginSuccess(it.username,it.collectIds)
                finish()
            }
        })
    }

    override fun onBackPressed() = finish()

}
