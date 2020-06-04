package com.ly.ktmaterialdesign.activity

import android.animation.ObjectAnimator
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import com.ly.ktmaterialdesign.R
import com.ly.ktmaterialdesign.base.BaseActivity
import kotlinx.android.synthetic.main.activity_share_view.*

class ShareViewActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_view)
        toolbar_share_view.subtitle = "Shared Element Transitions"
        toolbar_share_view.setNavigationIcon(R.drawable.ic_close_white_24dp)
        setToolbar(toolbar_share_view)
        initView()
    }

    private fun initView() {
        if (intent != null) {
            when (intent.getIntExtra("color", 0)) {
                1 -> {
                    rela_round_big.backgroundTintList = ColorStateList.valueOf(
                        resources.getColor(
                            R.color.google_blue
                        )
                    )
                }
                2 -> {
                    rela_round_big.backgroundTintList = ColorStateList.valueOf(
                        resources.getColor(
                            R.color.google_green
                        )
                    )
                }
                3 -> {
                    rela_round_big.backgroundTintList = ColorStateList.valueOf(
                        resources.getColor(
                            R.color.google_yellow
                        )
                    )
                }
                4 -> {
                    rela_round_big.backgroundTintList = ColorStateList.valueOf(
                        resources.getColor(
                            R.color.google_red
                        )
                    )
                }
                else -> {
                    rela_round_big.backgroundTintList = ColorStateList.valueOf(
                        resources.getColor(
                            R.color.gray
                        )
                    )
                }
            }
        }
        val alphaAnimation = AlphaAnimation(1.0f, 0.0f)
        alphaAnimation.duration = 1500
//        alphaAnimation.startOffset = 5000
        alphaAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                tv_share_view_tip.visibility = View.GONE
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        tv_share_view_tip.startAnimation(alphaAnimation)
        val downAnim = ObjectAnimator.ofFloat(card_share_view, "translationZ", 24f)
        downAnim.duration = 200
        downAnim.interpolator = AccelerateInterpolator()
        downAnim.start()
        card_share_view.setOnTouchListener(touchListener)
    }

    private val touchListener = OnTouchListener { view, motionEvent ->
        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                val upAnim = ObjectAnimator.ofFloat(view, "translationZ", 0f)
                upAnim.duration = 200
                upAnim.interpolator = DecelerateInterpolator()
                upAnim.start()
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                val downAnim = ObjectAnimator.ofFloat(view, "translationZ", 24f)
                downAnim.duration = 200
                downAnim.interpolator = AccelerateInterpolator()
                downAnim.start()
            }
        }
        false
    }
}