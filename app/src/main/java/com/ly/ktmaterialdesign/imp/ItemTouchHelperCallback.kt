package com.ly.ktmaterialdesign.imp

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

/**
 *
 * 用户item上下拖拽，左右删除的监听
 */
class ItemTouchHelperCallback(private val moveAndSwipedListener: OnMoveAndSwipedListener) :
    ItemTouchHelper.Callback() {
    private val TYPE_NORMAL = 1
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return if (recyclerView.layoutManager is GridLayoutManager) {
            // for recyclerView with gridLayoutManager, support drag all directions, not support swipe
            val dragFlags =
                ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            val swipeFlags = 0
            makeMovementFlags(
                dragFlags,
                swipeFlags
            )
        } else {
            // for recyclerView with linearLayoutManager, support drag up and down, and swipe lift and right
            if (viewHolder.itemViewType == TYPE_NORMAL) {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
                makeMovementFlags(
                    dragFlags,
                    swipeFlags
                )
            } else {
                0
            }
        }
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        // If the 2 items are not the same type, no dragging
        if (viewHolder.itemViewType != target.itemViewType) {
            return false
        }
        moveAndSwipedListener.onItemMove(
            viewHolder.adapterPosition,
            target.adapterPosition
        )
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        moveAndSwipedListener.onItemDismiss(viewHolder.adapterPosition)
    }

}