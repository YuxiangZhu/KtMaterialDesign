package com.ly.ktmaterialdesign.activity

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.ly.ktmaterialdesign.R
import com.ly.ktmaterialdesign.adapter.FragmentAdapter
import com.ly.ktmaterialdesign.base.BaseActivity
import com.ly.ktmaterialdesign.databinding.ActivityMainBinding
import com.ly.ktmaterialdesign.fragment.CardsFragment
import com.ly.ktmaterialdesign.fragment.DialogsFragment
import com.ly.ktmaterialdesign.fragment.WidgetsFragment
import java.util.*

/**
 * MaterialDesign
 */
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        initView()

    }


    /**
     * 初始化界面
     */
    private fun initView(){

        /**
         * 初始化抽屉
         */
        setSupportActionBar(binding.appBar.toolbar)
        val toggle = ActionBarDrawerToggle(this,binding.drawerLayout,binding.appBar.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        val titles: MutableList<String> = ArrayList()
        titles.add(getString(R.string.tab_title_main_1))
        titles.add(getString(R.string.tab_title_main_2))
        titles.add(getString(R.string.tab_title_main_3))

        //这个地方不可直接创建该类
//        binding.appBar.tabLayoutMain.addTab(TabLayout.Tab().setText(titles[0]))

        binding.appBar.tabLayoutMain.addTab(binding.appBar.tabLayoutMain.newTab().setText(titles[0]))
        binding.appBar.tabLayoutMain.addTab(binding.appBar.tabLayoutMain.newTab().setText(titles[0]))
        binding.appBar.tabLayoutMain.addTab(binding.appBar.tabLayoutMain.newTab().setText(titles[0]))


        val fragments: MutableList<Fragment> =
            ArrayList()
        fragments.add(CardsFragment())
        fragments.add(DialogsFragment())
        fragments.add(WidgetsFragment())

        val adapter = FragmentAdapter(supportFragmentManager,fragments,titles)
        binding.appBar.viewPagerMain.adapter = adapter
        binding.appBar.tabLayoutMain.setupWithViewPager(binding.appBar.viewPagerMain)




    }

}
