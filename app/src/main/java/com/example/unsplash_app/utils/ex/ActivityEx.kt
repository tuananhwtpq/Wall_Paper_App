package com.example.unsplash_app.utils.ex

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import com.example.unsplash_app.R

fun ComponentActivity.handleBackPressed(action: () -> Unit) {
    onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            action()
        }
    })
}

fun Activity.finishWithSlide(){
    finish()
    @Suppress("DEPRECATION") overridePendingTransition(
        R.anim.slide_in_right, R.anim.slide_out_left
    )

}