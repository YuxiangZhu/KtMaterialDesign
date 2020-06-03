package com.ly.ktmaterialdesign.base

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

/**
 * 作者： Alex
 * 日期： 2020-05-19
 * 签名： 保持学习

 * ----------------------------------------------------------------
 *
 */
open class BaseActivity: AppCompatActivity() {


    open fun setToolbar(toolbar: Toolbar?) {
        if (toolbar != null) {
            setSupportActionBar(toolbar)
            if (supportActionBar != null) {
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            }
        }
    }
}