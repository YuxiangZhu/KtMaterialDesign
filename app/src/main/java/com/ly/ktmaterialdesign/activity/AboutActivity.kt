package com.ly.ktmaterialdesign.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ly.ktmaterialdesign.R
import com.ly.ktmaterialdesign.base.BaseActivity
import com.ly.ktmaterialdesign.databinding.ActivityAboutBinding

class AboutActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityAboutBinding>(this,R.layout.activity_about)

        setToolbar(binding.toolbarAbout)
        window.navigationBarColor = resources.getColor(R.color.colorPrimary)

    }
}
