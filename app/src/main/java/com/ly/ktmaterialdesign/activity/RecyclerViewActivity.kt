package com.ly.ktmaterialdesign.activity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.ly.ktmaterialdesign.R
import com.ly.ktmaterialdesign.adapter.RecyclerViewAdapter
import com.ly.ktmaterialdesign.base.BaseActivity
import com.ly.ktmaterialdesign.imp.ItemTouchHelperCallback
import com.ly.ktmaterialdesign.utils.AppUtils
import kotlinx.android.synthetic.main.activity_recycler_view.*
import java.util.*

class RecyclerViewActivity : BaseActivity() {
    private var adapter = RecyclerViewAdapter(this)
    private var color = 0
    private var data: MutableList<String>? = null
    private var insertData = "0"
    private var loading = false
    //上拉加载的次数，用于临时控制
    private var loadTimes = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        val toolbar: Toolbar = findViewById(R.id.toolbar_recycler_view)
        setToolbar(toolbar)
        initData()
        initView()
    }

    private fun initData() {
        data = ArrayList()
        for (i in 1..20) {
            data!!.add(i.toString() + "")
        }
        loadTimes = 0
    }

    private fun initView() {
        if (AppUtils.getScreenWidthDp(this) >= 1200) {
            val gridLayoutManager = GridLayoutManager(this, 3)
            recycler_view_recycler_view!!.layoutManager = gridLayoutManager
        } else if (AppUtils.getScreenWidthDp(this) >= 800) {
            val gridLayoutManager = GridLayoutManager(this, 2)
            recycler_view_recycler_view!!.layoutManager = gridLayoutManager
        } else {
            val linearLayoutManager = LinearLayoutManager(this)
            recycler_view_recycler_view!!.layoutManager = linearLayoutManager
        }

        recycler_view_recycler_view!!.adapter = adapter
        adapter.addHeader()
        adapter.setItems(data)
        adapter.addFooter()
        efab_recycler_view.setOnClickListener { view: View? ->
            val linearLayoutManager =
                recycler_view_recycler_view.layoutManager as LinearLayoutManager?
            adapter.addItem(linearLayoutManager!!.findFirstVisibleItemPosition() + 1, insertData)
        }
        val callback: ItemTouchHelper.Callback = ItemTouchHelperCallback(adapter)
        val mItemTouchHelper = ItemTouchHelper(callback)
        mItemTouchHelper.attachToRecyclerView(recycler_view_recycler_view)

        swipe_refresh_layout_recycler_view.setColorSchemeResources(
            R.color.google_blue,
            R.color.google_green,
            R.color.google_red,
            R.color.google_yellow
        )
        swipe_refresh_layout_recycler_view.setOnRefreshListener {
            Handler().postDelayed(
                {
                    if (color > 4) {
                        color = 0
                    }
                    adapter.setColor(++color)
                    swipe_refresh_layout_recycler_view.isRefreshing = false
                }, 2000
            )
        }
        recycler_view_recycler_view.addOnScrollListener(scrollListener)
    }

    var scrollListener: RecyclerView.OnScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (dy > 0) {
                efab_recycler_view.shrink()
            } else {
                efab_recycler_view.extend()
            }
            val linearLayoutManager =
                recyclerView.layoutManager as LinearLayoutManager?
            if (!loading && linearLayoutManager!!.itemCount == linearLayoutManager.findLastVisibleItemPosition() + 1) {
                loadMoreData()
                loading = true
            }
        }
    }

    private fun loadMoreData() {
        Handler().postDelayed({
            if (loadTimes <= 5) {
                adapter.removeFooter()
                loading = false
                adapter.addItems(data)
                adapter.addFooter()
                loadTimes++
            } else {
                adapter.removeFooter()
                Snackbar.make(
                    recycler_view_recycler_view,
                    getString(R.string.no_more_data),
                    Snackbar.LENGTH_SHORT
                ).setCallback(object : Snackbar.Callback() {
                    override fun onDismissed(
                        transientBottomBar: Snackbar,
                        event: Int
                    ) {
                        super.onDismissed(transientBottomBar, event)
                        loading = false
                        adapter.addFooter()
                    }
                }).show()
            }
        }, 1500)
    }
}