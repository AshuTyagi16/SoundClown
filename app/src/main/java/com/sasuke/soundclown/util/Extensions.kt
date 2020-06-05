package com.sasuke.soundclown.util

import android.content.res.Resources
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

inline fun <reified T : ViewModel> getViewModel(
    activity: FragmentActivity,
    viewModelFactory: ViewModelProvider.Factory
): T {
    return ViewModelProvider(activity, viewModelFactory)[T::class.java]
}

fun Int.dpToPx(): Int {
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}