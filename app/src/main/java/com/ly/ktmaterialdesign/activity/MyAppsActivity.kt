package com.ly.ktmaterialdesign.activity

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ly.ktmaterialdesign.R
import com.ly.ktmaterialdesign.adapter.MyAppsAdapter
import com.ly.ktmaterialdesign.base.BaseActivity
import com.ly.ktmaterialdesign.bean.Data

class MyAppsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_apps)
        val toolbar =
            findViewById<Toolbar>(R.id.toolbar_my_apps)
//        setToolbar(toolbar)

        val recycler_my_apps = findViewById<RecyclerView>(R.id.recycler_my_apps)
        val adapter =
            MyAppsAdapter(this, Data.getMyAppsData())
        val linearLayoutManager = LinearLayoutManager(this)
        recycler_my_apps.layoutManager = linearLayoutManager
        recycler_my_apps.adapter = adapter
    }
}