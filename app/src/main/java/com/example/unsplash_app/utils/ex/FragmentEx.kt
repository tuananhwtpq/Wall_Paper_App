package com.example.unsplash_app.utils.ex

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.unsplash_app.base.BaseFragment
import kotlinx.coroutines.CoroutineScope

fun BaseFragment<*>.launchOnStarted(block: suspend CoroutineScope.() -> Unit) {
    launchCoroutine {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            block()
        }
    }
}

fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}