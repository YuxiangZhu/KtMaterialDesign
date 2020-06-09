package com.ly.ktmaterialdesign.activity

import android.content.Intent
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ly.ktmaterialdesign.R
import com.ly.ktmaterialdesign.base.BaseActivity
import com.ly.ktmaterialdesign.utils.AppUtils
import kotlinx.android.synthetic.main.activity_scrolling.*

class ScrollingActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        //        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        val toolbar =
            findViewById<Toolbar>(R.id.toolbar)
        setToolbar(toolbar)
        fab_scrolling.setOnClickListener { view: View? ->
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, " Constant.SHARE_CONTENT")
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, getString(R.string.share_with)))
        }
        Glide.with(this).load(R.drawable.material_design_3).apply(RequestOptions().fitCenter())
            .into(image_scrolling_top)
        if (AppUtils.getScreenWidthDp(this) >= 600) {
            collapsing_toolbar_layout.setExpandedTitleTextColor(ColorStateList.valueOf(Color.TRANSPARENT))
        }
    }

    override fun onResume() {
        super.onResume()
        val configuration = resources.configuration
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        }
    }
}