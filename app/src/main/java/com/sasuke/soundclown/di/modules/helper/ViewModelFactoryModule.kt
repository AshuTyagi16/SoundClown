package com.sasuke.soundclown.di.modules.helper

import androidx.lifecycle.ViewModelProvider
import com.sasuke.soundclown.util.DaggerViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory
}