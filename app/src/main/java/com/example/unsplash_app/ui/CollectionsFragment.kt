package com.example.unsplash_app.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.unsplash_app.base.BaseFragment
import com.example.unsplash_app.databinding.FragmentCollectionsBinding

class CollectionsFragment : BaseFragment<FragmentCollectionsBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCollectionsBinding {
        return FragmentCollectionsBinding.inflate(inflater, container, false)
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