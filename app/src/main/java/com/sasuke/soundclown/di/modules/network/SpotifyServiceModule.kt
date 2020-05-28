package com.sasuke.soundclown.di.modules.network

import com.sasuke.soundclown.di.scopes.SoundClownScope
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sasuke.soundclown.data.network.SpotifyAuthenticationService
import com.sasuke.soundclown.data.network.SpotifyService
import com.sasuke.soundclown.di.qualifiers.SpotifyAuthenticatorOkHttpClient
import com.sasuke.soundclown.di.qualifiers.SpotifyAuthenticatorRetrofit
import com.sasuke.soundclown.di.qualifiers.SpotifyOkHttpClient
import com.sasuke.soundclown.di.qualifiers.SpotifyRetrofit
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [NetworkModule::class])
class SpotifyServiceModule {

    companion object {
        private const val BASE_URL = "https://api.spotify.com/"
        private const val BASE_URL_AUTHENTICATION = "https://accounts.spotify.com/"
    }

    @Provides
    @SoundClownScope
    fun spotifyService(@SpotifyRetrofit retrofit: Retrofit): SpotifyService {
        return retrofit.create(SpotifyService::class.java)
    }

    @Provides
    @SoundClownScope
    @SpotifyRetrofit
    fun spotifyRetrofit(@SpotifyOkHttpClient okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @SoundClownScope
    @SpotifyAuthenticatorRetrofit
    fun spotifyAuthenticatorRetrofit(
        @SpotifyAuthenticatorOkHttpClient okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .baseUrl(BASE_URL_AUTHENTICATION)
            .build()
    }

    @Provides
    @SoundClownScope
    fun gson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @SoundClownScope
    fun spotifyAuthenticationService(@SpotifyAuthenticatorRetrofit retrofit: Retrofit): SpotifyAuthenticationService {
        return retrofit.create(SpotifyAuthenticationService::class.java)
    }
}