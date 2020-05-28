package com.sasuke.soundclown.di.modules.activity

import androidx.lifecycle.ViewModel
import com.sasuke.soundclown.di.mapkey.ViewModelKey
import com.sasuke.soundclown.ui.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityModule {

    companion object {

    }

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel
}