package com.ly.ktmaterialdesign.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ly.ktmaterialdesign.databinding.FragmentCardsBinding

/**
 * 作者： Alex
 * 日期： 2020-06-03
 * 签名： 保持学习

 * ----------------------------------------------------------------
 *
 */
class CardsFragment(): Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       val binding = FragmentCardsBinding.inflate(inflater)


        return binding.root
    }

}