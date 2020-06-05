package com.sasuke.soundclown.di.modules.activity

import androidx.lifecycle.ViewModel
import com.sasuke.soundclown.di.mapkey.ViewModelKey
import com.sasuke.soundclown.di.modules.fragment.CategoryDetailsFragmentModule
import com.sasuke.soundclown.di.modules.fragment.SearchFragmentModule
import com.sasuke.soundclown.di.scopes.PerFragmentScope
import com.sasuke.soundclown.ui.DemoFragment
import com.sasuke.soundclown.ui.category_details.CategoryDetailsFragment
import com.sasuke.soundclown.ui.search.SearchFragment
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
    @ContributesAndroidInjector(modules = [SearchFragmentModule::class])
    internal abstract fun searchFragment(): SearchFragment

    @PerFragmentScope
    @ContributesAndroidInjector(modules = [CategoryDetailsFragmentModule::class])
    internal abstract fun categoryDetailsFragment(): CategoryDetailsFragment

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel
}