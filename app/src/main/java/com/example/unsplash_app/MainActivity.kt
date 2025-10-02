package com.example.unsplash_app

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.unsplash_app.base.BaseActivity
import com.example.unsplash_app.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {



    override fun inflateViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    override fun initView() {

    }

    override fun initData() {
    }

    override fun initListener() {
        handleBottomNav()
    }

    private fun handleBottomNav(){
        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(1).isEnabled = false
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false
    }
}