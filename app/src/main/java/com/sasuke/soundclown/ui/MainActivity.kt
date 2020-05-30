package com.sasuke.soundclown.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sasuke.soundclown.R
import com.sasuke.soundclown.data.model.Status
import com.sasuke.soundclown.ui.base.BaseActivity
import com.sasuke.soundclown.util.getViewModel
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import java.lang.Math.abs
import javax.inject.Inject

class MainActivity : BaseActivity(), PlayerFragment.OnTransitionProgressListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mainActivityViewModel: MainActivityViewModel

    private lateinit var playerFragment: PlayerFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inject()
//        getData()
        observeLiveData()
        replaceVideoFragment()
    }

    private fun inject() {
        mainActivityViewModel =
            getViewModel(this, viewModelFactory)
        playerFragment = PlayerFragment.newInstance()
        playerFragment.setOnTransitionProgressListener(this)
    }

    private fun getData() {
        mainActivityViewModel.getDemo()
    }

    private fun observeLiveData() {
        mainActivityViewModel.trackLiveData.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    Timber.i("LOADING")
                }
                Status.SUCCESS -> {
                    Timber.i("SUCCESS")
                }
                Status.ERROR -> {
                    Timber.i("ERROR")
                }
            }
        })
    }

    private fun replaceVideoFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, playerFragment)
            .commit()
    }

    override fun onTransitionProgress(progress: Float) {
        mainMotionLayout.progress = abs(progress)
    }
}
