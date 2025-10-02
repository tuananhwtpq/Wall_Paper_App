package com.example.unsplash_app.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewbinding.ViewBinding
import com.example.unsplash_app.R
import com.example.unsplash_app.utils.ex.finishWithSlide
import com.example.unsplash_app.utils.ex.handleBackPressed

abstract class BaseActivity<VB: ViewBinding> : AppCompatActivity() {

    //region Variable
    private var _binding: VB? = null
    val binding: VB
        get() = _binding!!

    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = inflateViewBinding(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initView()
        initData()
        initListener()
        handleBackPressed {
            onBack()
        }
    }


    open fun onBack(){
        if (supportFragmentManager.backStackEntryCount > 0){
            onBackPressedDispatcher.onBackPressed()
        } else {
            finishWithSlide()
        }
    }

    abstract fun inflateViewBinding(inflater: LayoutInflater): VB
    abstract fun initView()
    abstract fun initData()
    abstract fun initListener()

    //region Logcat

    fun logDebug(msg: String){

    }

}