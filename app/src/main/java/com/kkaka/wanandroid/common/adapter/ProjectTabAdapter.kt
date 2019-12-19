package com.kkaka.wanandroid.common.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * @author Laizexin on 2019/12/9
 * @description
 */
class ProjectTabAdapter(manager : FragmentManager, private val titles : List<String>, private val fragemtns : List<Fragment>) : FragmentStatePagerAdapter(manager) {

    override fun getPageTitle(position: Int): CharSequence? = titles[position]

    override fun getItem(position: Int): Fragment = fragemtns[position]

    override fun getCount(): Int = fragemtns.size


}