package com.ly.ktmaterialdesign.imp

/**
 * 用于Item左右删除，上下拖拽的回调
 */
interface OnMoveAndSwipedListener {
    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean
    fun onItemDismiss(position: Int)
}