package com.ly.ktmaterialdesign.activity

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.databinding.DataBindingUtil
import com.ly.ktmaterialdesign.R
import com.ly.ktmaterialdesign.base.BaseActivity
import com.ly.ktmaterialdesign.databinding.ActivityMainBinding

/**
 * MaterialDesign
 */
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)

        initView()

    }


    /**
     * 初始化界面
     */
    private fun initView(){

        setSupportActionBar(binding.appBar.toolbar)
        val toggle = ActionBarDrawerToggle(this,binding.drawerLayout,binding.appBar.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


    }

}
