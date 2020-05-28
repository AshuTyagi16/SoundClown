package com.sasuke.soundclown.di.modules.helper

import android.content.Context
import com.sasuke.soundclown.util.NetworkUtil
import com.sasuke.soundclown.di.scopes.SoundClownScope
import dagger.Module
import dagger.Provides

@Module
class UtilsModule {

    @Provides
    @SoundClownScope
    fun networkUtil(context: Context): NetworkUtil {
        return NetworkUtil(context)
    }

}