package com.ly.ktmaterialdesign.activity

import android.animation.ArgbEvaluator
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ly.ktmaterialdesign.R
import com.ly.ktmaterialdesign.adapter.ViewPagerAdapter
import com.ly.ktmaterialdesign.base.BaseActivity
import kotlinx.android.synthetic.main.activity_bottom_navigation.*
import java.util.*

class BottomNavigationActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)
        initView()
    }

    private fun initView() {
        setStatusBarColor(resources.getColor(R.color.transparent))
        val toolbar =
            findViewById<Toolbar>(R.id.toolbar)
        setToolbar(toolbar)
        val view1 = layoutInflater.inflate(R.layout.item_view_pager, null)
        val view2 = layoutInflater.inflate(R.layout.item_view_pager, null)
        val view3 = layoutInflater.inflate(R.layout.item_view_pager, null)
        val view4 = layoutInflater.inflate(R.layout.item_view_pager, null)
        val viewList: MutableList<View> =
            ArrayList()
        viewList.add(view1)
        viewList.add(view2)
        viewList.add(view3)
        viewList.add(view4)
        val adapter = ViewPagerAdapter(viewList)
        view_pager_bottom_navigation.adapter = adapter
        view_pager_bottom_navigation.addOnPageChangeListener(pageChangeListener)
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val pageChangeListener: OnPageChangeListener = object : OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            val evaluator = ArgbEvaluator()
            var evaluate = resources.getColor(R.color.app_blue)
            evaluate = if (position == 0) {
                evaluator.evaluate(
                    positionOffset,
                    resources.getColor(R.color.app_blue),
                    resources.getColor(R.color.app_green)
                ) as Int
            } else if (position == 1) {
                evaluator.evaluate(
                    positionOffset,
                    resources.getColor(R.color.app_green),
                    resources.getColor(R.color.app_yellow)
                ) as Int
            } else if (position == 2) {
                evaluator.evaluate(
                    positionOffset,
                    resources.getColor(R.color.app_yellow),
                    resources.getColor(R.color.app_red)
                ) as Int
            } else {
                resources.getColor(R.color.app_red)
            }
            (view_pager_bottom_navigation!!.parent as View).setBackgroundColor(evaluate)
        }

        override fun onPageSelected(position: Int) {
            when (position) {
                0 -> bottom_navigation!!.selectedItemId = R.id.bottom_navigation_blue
                1 -> bottom_navigation!!.selectedItemId = R.id.bottom_navigation_green
                2 -> bottom_navigation!!.selectedItemId = R.id.bottom_navigation_yellow
                3 -> bottom_navigation!!.selectedItemId = R.id.bottom_navigation_red
            }
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }
    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_navigation_blue -> {
                    view_pager_bottom_navigation!!.currentItem = 0
                    return@OnNavigationItemSelectedListener true
                }
                R.id.bottom_navigation_green -> {
                    view_pager_bottom_navigation!!.currentItem = 1
                    return@OnNavigationItemSelectedListener true
                }
                R.id.bottom_navigation_yellow -> {
                    view_pager_bottom_navigation!!.currentItem = 2
                    return@OnNavigationItemSelectedListener true
                }
                R.id.bottom_navigation_red -> {
                    view_pager_bottom_navigation!!.currentItem = 3
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
}