package com.sasuke.soundclown.ui

import com.sasuke.soundclown.R
import com.sasuke.soundclown.ui.base.BaseFragment

class DemoFragment : BaseFragment() {

    companion object {
        fun newInstance() = DemoFragment()
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_demo
    }
}