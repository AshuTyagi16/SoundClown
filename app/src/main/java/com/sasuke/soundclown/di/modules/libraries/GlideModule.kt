package com.sasuke.soundclown.di.modules.libraries

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import com.sasuke.soundclown.di.scopes.SoundClownScope
import dagger.Module
import dagger.Provides

@Module
class GlideModule {

    @Provides
    @SoundClownScope
    fun glide(context: Context): RequestManager {
        return Glide.with(context).setDefaultRequestOptions(
            RequestOptions().format(DecodeFormat.PREFER_RGB_565))
    }
}