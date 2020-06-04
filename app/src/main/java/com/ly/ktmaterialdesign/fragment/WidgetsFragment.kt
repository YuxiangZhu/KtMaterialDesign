package com.ly.ktmaterialdesign.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.LogUtils
import com.ly.ktmaterialdesign.R
import com.ly.ktmaterialdesign.databinding.FragmentWidgetsBinding
import kotlinx.android.synthetic.main.fragment_widgets.view.*

/**
 * 作者： Alex
 * 日期： 2020-06-03
 * 签名： 保持学习

 * ----------------------------------------------------------------
 *
 */
class WidgetsFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentWidgetsBinding.inflate(inflater)

        binding.toggleGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->

            when(checkedId){
                R.id.btn1 ->{

                    LogUtils.e("btn1$isChecked")

                }
            }

        }


        binding.chipgroup.setOnCheckedChangeListener { group, checkedId ->

            when(checkedId){
                R.id.chip1 ->{
                    LogUtils.e("chip1")
                }

                R.id.chip2 ->{
                    LogUtils.e("chip2")
                }

            }

        }

        return binding.root
    }
}