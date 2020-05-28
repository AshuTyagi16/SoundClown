package com.sasuke.soundclown.util

import android.content.res.Resources
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import java.util.*

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

inline fun <reified T : ViewModel> getViewModel(
    activity: FragmentActivity,
    viewModelFactory: ViewModelProvider.Factory
): T {
    return ViewModelProvider(activity, viewModelFactory)[T::class.java]
}