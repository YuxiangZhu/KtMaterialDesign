package com.ly.ktmaterialdesign.activity

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.CompoundButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomappbar.BottomAppBar
import com.ly.ktmaterialdesign.R
import com.ly.ktmaterialdesign.adapter.RecyclerViewAdapter
import com.ly.ktmaterialdesign.base.BaseActivity
import com.ly.ktmaterialdesign.utils.AppUtils
import kotlinx.android.synthetic.main.activity_bottom_app_bar.*

class BottomAppBarActivity : BaseActivity() {
    private var data = ArrayList<String>()

    private val adapter = RecyclerViewAdapter(this)
    var isFabAlignRight = false
    var isCutoutMarginZero = false
    var isCutoutRadiusZero = false
    var showBottomBarTitle = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_app_bar)
        initData()
        initViews()


    }

    private fun initData() {
        for (i in 1..20) {
            data.add("List item row: $i")
        }
    }

    private fun initViews(){
        setToolbar(bottom_App_bar)

        when {
            AppUtils.getScreenWidthDp(this) >= 1200 -> {
                val gridLayoutManager = GridLayoutManager(this, 3)
                recycler_view_bottom_appbar.layoutManager = gridLayoutManager
            }
            AppUtils.getScreenWidthDp(this) >= 800 -> {
                val gridLayoutManager = GridLayoutManager(this, 2)
                recycler_view_bottom_appbar.layoutManager = gridLayoutManager
            }
            else -> {
                val linearLayoutManager = LinearLayoutManager(this)
                recycler_view_bottom_appbar.layoutManager = linearLayoutManager
            }
        }

        recycler_view_bottom_appbar.adapter = adapter
        adapter.setItems(data)
        setupUiClicks()


    }

    private fun setupUiClicks() {
        position_chip.setOnCheckedChangeListener { _: CompoundButton?, b: Boolean ->
            isFabAlignRight = b
            resetBottomAppBar()
        }
        radius_chip.setOnCheckedChangeListener { _: CompoundButton?, b: Boolean ->
            isCutoutRadiusZero = b
            resetBottomAppBar()
        }
        margin_chip.setOnCheckedChangeListener { _: CompoundButton?, b: Boolean ->
            isCutoutMarginZero = b
            resetBottomAppBar()
        }
        show_title_chip.setOnCheckedChangeListener { _: CompoundButton?, b: Boolean ->
            showBottomBarTitle = b
            resetBottomAppBar()
        }
        fab_bottom_appbar.setOnClickListener {
            adapter.addItem(0, "1")
            recycler_view_bottom_appbar.smoothScrollToPosition(0)
        }
    }

    private fun resetBottomAppBar() {
        bottom_App_bar.fabAlignmentMode = if (isFabAlignRight) BottomAppBar.FAB_ALIGNMENT_MODE_END else BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
        bottom_App_bar.fabCradleMargin = if (isCutoutMarginZero) 0f else 17f//initial default value 17f
        bottom_App_bar.fabCradleRoundedCornerRadius = if (isCutoutRadiusZero) 0f else 28f //initial default value 28f
        bottom_app_bar_title.visibility = if (showBottomBarTitle) View.VISIBLE else View.GONE //By Default its not suggested to add title but this is just a method if required.
        if (showBottomBarTitle) bottom_App_bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
        bottom_App_bar.invalidate()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.popup_menu_main, menu)
        return true
    }
}
