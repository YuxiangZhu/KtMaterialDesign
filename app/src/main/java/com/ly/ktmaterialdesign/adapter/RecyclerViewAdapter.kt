package com.ly.ktmaterialdesign.adapter

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationUtils
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.ly.ktmaterialdesign.R
import com.ly.ktmaterialdesign.activity.ShareViewActivity
import com.ly.ktmaterialdesign.imp.OnMoveAndSwipedListener
import java.util.*
import kotlin.collections.ArrayList

/**
 *
 */
class RecyclerViewAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), OnMoveAndSwipedListener {
    private val mItems = ArrayList<String>()
    private var color = 0
    private var parentView: View? = null
    private val TYPE_NORMAL = 1
    private val TYPE_FOOTER = 2
    private val TYPE_HEADER = 3
    private val FOOTER = "footer"
    private val HEADER = "header"
    fun setItems(data: List<String>?) {
        mItems.addAll(data!!)
        notifyDataSetChanged()
    }

    fun addItem(position: Int, insertData: String) {
        mItems.add(position, insertData)
        notifyItemInserted(position)
    }

    fun addItems(data: List<String>?) {
        mItems.add(HEADER)
        mItems.addAll(data!!)
        notifyItemInserted(mItems.size - 1)
    }

    fun addHeader() {
        mItems.add(HEADER)
        notifyItemInserted(mItems.size - 1)
    }

    fun addFooter() {
        mItems.add(FOOTER)
        notifyItemInserted(mItems.size - 1)
    }

    fun removeFooter() {
        mItems.removeAt(mItems.size - 1)
        notifyItemRemoved(mItems.size)
    }

    fun setColor(color: Int) {
        this.color = color
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        parentView = parent
        return when (viewType) {
            TYPE_NORMAL -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_recycler_view, parent, false)
                RecyclerViewHolder(view)
            }
            TYPE_FOOTER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_recycler_footer, parent, false)
                FooterViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_recycler_header, parent, false)
                HeaderViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        if (holder is RecyclerViewHolder) {
            val animation = AnimationUtils.loadAnimation(
                context,
                R.anim.anim_recycler_item_show
            )
            holder.mView.startAnimation(animation)
            val aa1 = AlphaAnimation(1.0f, 0.1f)
            aa1.duration = 800
            holder.rela_round.startAnimation(aa1)
            val aa = AlphaAnimation(0.1f, 1.0f)
            aa.duration = 400
            when (color) {
                1 -> {
                    holder.rela_round.backgroundTintList = ColorStateList.valueOf(
                        context.resources.getColor(R.color.google_blue)
                    )
                }
                2 -> {
                    holder.rela_round.backgroundTintList = ColorStateList.valueOf(
                        context.resources.getColor(R.color.google_green)
                    )
                }
                3 -> {
                    holder.rela_round.backgroundTintList = ColorStateList.valueOf(
                        context.resources.getColor(R.color.google_yellow)
                    )
                }
                4 -> {
                    holder.rela_round.backgroundTintList = ColorStateList.valueOf(
                        context.resources.getColor(R.color.google_red)
                    )
                }
                else -> {
                    holder.rela_round.backgroundTintList = ColorStateList.valueOf(
                        context.resources.getColor(R.color.gray)
                    )
                }
            }
            holder.rela_round.startAnimation(aa)
            holder.mView.setOnClickListener { view: View? ->
                val intent = Intent(context, ShareViewActivity::class.java)
                intent.putExtra("color", color)
                context.startActivity(
                    intent,
                    ActivityOptions.makeSceneTransitionAnimation(
                        context as Activity,
                        holder.rela_round,
                        "shareView"
                    ).toBundle()
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val s = mItems[position]
        return when (s) {
            HEADER -> TYPE_HEADER
            FOOTER -> TYPE_FOOTER
            else -> TYPE_NORMAL
        }
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        Collections.swap(mItems, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemDismiss(position: Int) {
        mItems.removeAt(position)
        notifyItemRemoved(position)
        Snackbar.make(
            parentView!!,
            context.getString(R.string.item_swipe_dismissed),
            Snackbar.LENGTH_SHORT
        )
            .setAction(
                context.getString(R.string.item_swipe_undo)
            ) { v: View? ->
                addItem(
                    position,
                    mItems[position]
                )
            }.show()
    }

    private inner class RecyclerViewHolder(val mView: View) :
        RecyclerView.ViewHolder(mView) {
        val rela_round: RelativeLayout

        init {
            rela_round = mView.findViewById(R.id.rela_round)
        }
    }

    private inner class FooterViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val progress_bar_load_more: ProgressBar

        init {
            progress_bar_load_more = itemView.findViewById(R.id.progress_bar_load_more)
        }
    }

    private inner class HeaderViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val header_text: TextView

        init {
            header_text = itemView.findViewById(R.id.header_text)
        }
    }
}