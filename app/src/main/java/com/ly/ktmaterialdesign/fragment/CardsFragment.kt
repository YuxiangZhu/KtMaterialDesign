package com.ly.ktmaterialdesign.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ly.ktmaterialdesign.R
import com.ly.ktmaterialdesign.databinding.FragmentCardsBinding
import kotlinx.android.synthetic.main.card_1.*
import kotlinx.android.synthetic.main.card_2.*

/**
 * 作者： Alex
 * 日期： 2020-06-03
 * 签名： 保持学习

 * ----------------------------------------------------------------
 *
 */
class CardsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       val binding = FragmentCardsBinding.inflate(inflater)

        Glide.with(activity!!).load(R.drawable.material_design_5)
            .apply(RequestOptions().fitCenter()).into(binding.card1.imgCard1)
        Glide.with(context!!).load(R.drawable.material_design_4)
            .apply(RequestOptions().fitCenter()).into(binding.card2.imgCard2)


        return binding.root
    }

}