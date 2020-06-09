package com.ly.ktmaterialdesign.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.blankj.utilcode.util.ActivityUtils
import com.google.android.material.navigation.NavigationView
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

        binding.navView.setNavigationItemSelectedListener {

            when(it.itemId){
                R.id.nav_recycler_and_swipe_refresh ->{
                    ActivityUtils.startActivity(RecyclerViewActivity::class.java)
                }
                R.id.nav_scrolling ->{
                    ActivityUtils.startActivity(ScrollingActivity::class.java)
                }
                R.id.nav_bottom_navigation ->{
                    ActivityUtils.startActivity(BottomNavigationActivity::class.java)
                }

                R.id.nav_bottom_appbar ->{
                    ActivityUtils.startActivity(BottomAppBarActivity::class.java)
                }
                R.id.nav_about ->{
                    ActivityUtils.startActivity(AboutActivity::class.java)
                }

                R.id.nav_my_apps ->{
                    ActivityUtils.startActivity(MyAppsActivity::class.java)
                }

            }

            true
        }

        /**
         * 初始化首页tab
         */
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

        binding.appBar.viewPagerMain.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {

                if(position==1){
                    binding.appBar.fabMain.show()
                }else{
                    binding.appBar.fabMain.hide()
                }

            }

        })




    }


    /**
     * 点击返回按钮时如果抽屉开着，则关闭之
     */
    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_menu_main_about -> {
                val aboutIntent = Intent(this, AboutActivity::class.java)
                startActivity(aboutIntent)
            }
            R.id.action_menu_main_my_app -> {
                val myAppsIntent = Intent(this, MyAppsActivity::class.java)
                startActivity(myAppsIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
