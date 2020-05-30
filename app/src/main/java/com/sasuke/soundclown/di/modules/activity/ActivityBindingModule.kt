package com.sasuke.soundclown.di.modules.activity

import com.sasuke.soundclown.di.modules.fragment.BaseFragmentModule
import com.sasuke.soundclown.di.scopes.PerActivityScope
import com.sasuke.soundclown.ui.player.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [BaseFragmentModule::class])
abstract class ActivityBindingModule {

    @PerActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun mainActivity(): MainActivity

}