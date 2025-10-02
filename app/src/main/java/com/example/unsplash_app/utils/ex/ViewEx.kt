package com.example.unsplash_app.utils.ex

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import com.example.unsplash_app.R

fun View.clickAnimation() {
    try {
        if (!isAttachedToWindow) {
            return
        }
        context ?: return
        clickAnimation(context, this)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun clickAnimation(mContext: Context?, view: View) {
    if (mContext != null) {
        val myAnim = AnimationUtils.loadAnimation(mContext, R.anim.bounce)
        view.startAnimation(myAnim)
    }
}