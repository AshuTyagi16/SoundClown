package com.sasuke.soundclown.di.modules.application

import com.sasuke.soundclown.di.scopes.SoundClownScope
import com.sasuke.soundclown.R
import dagger.Module
import dagger.Provides
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import timber.log.Timber

@Module
object SoundClownModule {

    @Provides
    @SoundClownScope
    fun timberTree(): Timber.Tree {
        return Timber.DebugTree()
    }

    @Provides
    @SoundClownScope
    fun calligraphyInterceptor(): CalligraphyInterceptor {
        return CalligraphyInterceptor(
                CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/Lato.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        )
    }
}