package com.ly.ktmaterialdesign

import android.app.Application
import android.content.Context
import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor

class MyApplication : Application() {
    private var mHandler: Handler? = null
    private var mExecutor: Executor? = null
    override fun onCreate() {
        super.onCreate()
        application = this
        mHandler = Handler()
        mExecutor = AsyncTask.THREAD_POOL_EXECUTOR
    }

    fun runOnUi(runnable: Runnable?) {
        if (mHandler != null) {
            mHandler!!.post(runnable)
        } else {
            mHandler = Handler(Looper.getMainLooper())
            mHandler!!.post(runnable)
        }
    }

    fun runOnBackground(runnable: Runnable?) {
        if (mExecutor != null) {
            mExecutor!!.execute(runnable)
        } else {
            mExecutor = AsyncTask.THREAD_POOL_EXECUTOR
            mExecutor!!.execute(runnable)
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        context = base
    }

    companion object {
        var context: Context? = null
            private set
        var application: MyApplication? = null
            private set

    }
}