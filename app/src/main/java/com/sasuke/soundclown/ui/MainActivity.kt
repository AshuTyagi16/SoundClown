package com.sasuke.soundclown.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sasuke.soundclown.R
import com.sasuke.soundclown.data.model.Status
import com.sasuke.soundclown.ui.base.BaseActivity
import com.sasuke.soundclown.util.getViewModel
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inject()
        getData()
        observeLiveData()
    }

    private fun inject() {
        mainActivityViewModel =
            getViewModel(this, viewModelFactory)
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
}
