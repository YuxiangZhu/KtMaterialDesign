package com.ly.ktmaterialdesign.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ly.ktmaterialdesign.databinding.FragmentDialogsBinding

/**
 * 作者： Alex
 * 日期： 2020-06-03
 * 签名： 保持学习

 * ----------------------------------------------------------------
 *
 */
class DialogsFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding = FragmentDialogsBinding.inflate(inflater)
        return binding.root
    }

}