package com.sasuke.soundclown.di.modules.helper

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.sasuke.soundclown.di.scopes.SoundClownScope
import com.sasuke.soundclown.util.SharedPreferenceUtil
import dagger.Module
import dagger.Provides

@Module
object SharedPreferenceModule {

    @Provides
    @SoundClownScope
    fun sharedPreferenceUtil(preferences: SharedPreferences, gson: Gson): SharedPreferenceUtil {
        return SharedPreferenceUtil(preferences, gson)
    }

    @Provides
    @SoundClownScope
    fun preferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

}