package com.example.unsplash_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import com.example.unsplash_app.base.BaseActivity
import com.example.unsplash_app.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var navController: NavController

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    override fun initView() {

        setupNavController()
        setupDestinationListener()
    }

    override fun initData() {
    }

    override fun initListener() {
        handleBottomNav()
    }

    private fun handleBottomNav() {
        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(1).isEnabled = false
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false
    }

    private fun setupDestinationListener() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.mainFragment -> {
                    showBottomNav()
                }

                else -> hideBottomNav()
            }
        }
    }

    private fun setupNavController() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment

        navController = navHostFragment.navController
    }

    private fun showBottomNav() {
        binding.bottomAppBar.visibility = View.VISIBLE
        binding.fab.visibility = View.VISIBLE
        setBottomMagin(80)
    }

    private fun hideBottomNav() {
        binding.bottomAppBar.visibility = View.GONE
        binding.fab.visibility = View.GONE
        setBottomMagin(0)

    }

    /**
     * Xử lý khoảng cách bottom margin khi ẩn hiện bottom nav
     */

    private fun setBottomMagin(marginInDp: Int) {
        val param = binding.fragmentContainer.layoutParams as ViewGroup.MarginLayoutParams
        val maginInPx = (marginInDp * resources.displayMetrics.density).toInt()
        param.bottomMargin = maginInPx
        binding.fragmentContainer.layoutParams = param
    }
}