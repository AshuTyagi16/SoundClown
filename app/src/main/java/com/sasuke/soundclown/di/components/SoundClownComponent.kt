package com.sasuke.soundclown.di.components

import android.content.Context
import com.bumptech.glide.RequestManager
import com.sasuke.soundclown.di.modules.activity.ActivityBindingModule
import com.sasuke.soundclown.di.modules.application.SoundClownModule
import com.sasuke.soundclown.di.modules.helper.UtilsModule
import com.sasuke.soundclown.di.modules.helper.ViewModelFactoryModule
import com.sasuke.soundclown.di.modules.network.SpotifyRepositoryModule
import com.sasuke.soundclown.di.scopes.SoundClownScope
import com.google.gson.Gson
import com.sasuke.soundclown.SoundClown
import com.sasuke.soundclown.data.network.SpotifyAuthenticationRepository
import com.sasuke.soundclown.data.network.SpotifyRepository
import com.sasuke.soundclown.di.modules.helper.SharedPreferenceModule
import com.sasuke.soundclown.di.modules.libraries.GlideModule
import com.sasuke.soundclown.di.modules.network.SpotifyAuthenticationRepositoryModule
import com.sasuke.soundclown.util.SharedPreferenceUtil
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import timber.log.Timber

@SoundClownScope
@Component(
    modules = [
        SoundClownModule::class,
        SpotifyRepositoryModule::class,
        SpotifyAuthenticationRepositoryModule::class,
        SharedPreferenceModule::class,
        GlideModule::class,
        UtilsModule::class,
        ViewModelFactoryModule::class,
        AndroidSupportInjectionModule::class,
        ActivityBindingModule::class
    ]
)
interface SoundClownComponent : AndroidInjector<SoundClown> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): SoundClownComponent
    }

    fun calligraphyInterceptor(): CalligraphyInterceptor

    fun timberTree(): Timber.Tree

    fun getGlide(): RequestManager

    fun getSpotifyRepository(): SpotifyRepository

    fun getSpotifyAuthenticationRepository(): SpotifyAuthenticationRepository

    fun getGson(): Gson

    fun getSharedPreferenceUtil(): SharedPreferenceUtil

}