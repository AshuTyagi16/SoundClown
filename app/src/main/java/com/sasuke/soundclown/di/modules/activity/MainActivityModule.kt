package com.sasuke.soundclown.di.modules.activity

import androidx.lifecycle.ViewModel
import com.sasuke.soundclown.di.mapkey.ViewModelKey
import com.sasuke.soundclown.di.modules.fragment.HomeFragmentModule
import com.sasuke.soundclown.di.scopes.PerFragmentScope
import com.sasuke.soundclown.ui.DemoFragment
import com.sasuke.soundclown.ui.home.HomeFragment
import com.sasuke.soundclown.ui.player.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityModule {

    companion object {

    }

    @PerFragmentScope
    @ContributesAndroidInjector
    internal abstract fun demoFragment(): DemoFragment

    @PerFragmentScope
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    internal abstract fun homeFragment(): HomeFragment

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel
}