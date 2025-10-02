package com.example.unsplash_app.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.unsplash_app.adapter.ViewPagerAdapter
import com.example.unsplash_app.base.BaseFragment
import com.example.unsplash_app.databinding.FragmentMainBinding
import com.example.unsplash_app.enums.TabInfo
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment : BaseFragment<FragmentMainBinding>() {

    companion object{
        const val TAB_KEY = "tab_key"
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }

    override fun setupView() {
        super.setupView()
        initViewPager()
    }

    private fun initViewPager(){
        binding.viewPagerGallery.adapter = ViewPagerAdapter(parentFragmentManager, lifecycle)
        TabLayoutMediator(binding.tabLayout, binding.viewPagerGallery){tabItem, position ->
            tabItem.text = TabInfo.getTabName(requireContext(), position)
        }.attach()
    }

    override fun observeData() {
        super.observeData()
    }

    override fun initListener() {
        super.initListener()



    }


}