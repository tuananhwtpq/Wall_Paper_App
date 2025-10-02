package com.example.unsplash_app.adapter

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.unsplash_app.enums.TabInfo
import com.example.unsplash_app.ui.CollectionsFragment
import com.example.unsplash_app.ui.HomeFragment
import com.example.unsplash_app.ui.MainFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragmentList = mutableMapOf<Int, Fragment?>()

    override fun createFragment(position: Int): Fragment {
        if (fragmentList[position] != null) {
            return fragmentList[position]!!
        }

        val bundle = bundleOf(MainFragment.TAB_KEY to position)
        when (TabInfo.getTabByPosition(position)) {
            TabInfo.TabHome -> {
                if (fragmentList[position] == null) {
                    fragmentList[position] = HomeFragment().apply {
                        arguments = bundle
                    }
                }
            }

            TabInfo.TabCollection -> {
                if (fragmentList[position] == null) {
                    fragmentList[position] = CollectionsFragment().apply {
                        arguments = bundle
                    }
                }
            }
        }

        return fragmentList[position]!!
    }

    override fun getItemCount(): Int {
        return TabInfo.getTabCount()
    }


}