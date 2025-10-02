package com.example.unsplash_app.ui.home_item_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.unsplash_app.R
import com.example.unsplash_app.base.BaseFragment
import com.example.unsplash_app.databinding.FragmentHomeItemDetailBinding

class HomeItemDetailFragment : BaseFragment<FragmentHomeItemDetailBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeItemDetailBinding {
        return FragmentHomeItemDetailBinding.inflate(inflater, container, false)
    }

    override fun setupView() {
        super.setupView()
    }

    override fun observeData() {
        super.observeData()
    }

    override fun initListener() {
        super.initListener()
    }



}