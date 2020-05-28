package com.sasuke.soundclown.di.modules.network

import android.content.Context
import android.util.Base64
import com.sasuke.soundclown.data.event.NoInternetEvent
import com.sasuke.soundclown.data.exception.NoConnectivityException
import com.sasuke.soundclown.data.network.SpotifyAuthenticationRepository
import com.sasuke.soundclown.data.network.TokenAuthenticator
import com.sasuke.soundclown.di.qualifiers.*
import com.sasuke.soundclown.di.scopes.SoundClownScope
import com.sasuke.soundclown.util.Constants
import com.sasuke.soundclown.util.NetworkUtil
import com.sasuke.soundclown.util.SharedPreferenceUtil
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.greenrobot.eventbus.EventBus
import timber.log.Timber
import java.io.File
import java.lang.Exception
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    companion object {
        private const val CONNECTION_TIMEOUT: Long = 60
        private const val WRITE_TIMEOUT: Long = 60
        private const val READ_TIMEOUT: Long = 60
        private const val MAX_STALE: Int = 7
        private const val MAX_AGE: Int = 1
        private const val CACHE_SIZE: Long = 10 * 1000 * 1000 //10 MB CACHE
        private const val CACHE_CONTROL = "Cache-Control"
        private const val PRAGMA = "Pragma"
    }

    @Provides
    @SoundClownScope
    @SpotifyOkHttpClient
    fun okHttpClient(
        @SpotifyHeaderInterceptor spotifyHeaderInterceptor: Interceptor,
        @CacheInterceptor cacheInterceptor: Interceptor,
        @NetworkInterceptor networkInterceptor: Interceptor,
        @LoggingInterceptor loggingInterceptor: HttpLoggingInterceptor,
        @StaleIfErrorInterceptor staleIfErrorInterceptor: Interceptor,
        authenticator: TokenAuthenticator,
        cache: Cache
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addNetworkInterceptor(spotifyHeaderInterceptor)
            .addInterceptor(networkInterceptor)
            .addInterceptor(loggingInterceptor)
            .authenticator(authenticator)
            .addInterceptor(staleIfErrorInterceptor)
            .addNetworkInterceptor(cacheInterceptor)
            .cache(cache)
            .build()
    }

    @Provides
    @SoundClownScope
    @LoggingInterceptor
    fun loggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.i(message)
            }
        })
//        loggingInterceptor.redactHeader("x-auth-token")
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    @SoundClownScope
    fun cache(cacheFile: File): Cache {
        return Cache(cacheFile, CACHE_SIZE)
    }

    @Provides
    @SoundClownScope
    fun file(context: Context): File {
        return File(context.cacheDir, "okhttp-cache")
    }

    @Provides
    @SoundClownScope
    @NetworkInterceptor
    fun networkInterceptor(networkUtil: NetworkUtil, cacheControl: CacheControl): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            if (!networkUtil.isOnline()) {
                EventBus.getDefault().post(NoInternetEvent())
                request = request.newBuilder().cacheControl(cacheControl).build()
                val response = chain.proceed(request)
                if (response.cacheResponse == null)
                    throw NoConnectivityException()
            }
            return@Interceptor chain.proceed(request)
        }
    }

    @Provides
    @SoundClownScope
    @SpotifyHeaderInterceptor
    fun spotifyHeaderInterceptor(sharedPreferenceUtil: SharedPreferenceUtil): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val requestBuilder = originalRequest.newBuilder()
            requestBuilder.addHeader(
                Constants.HEADER_AUTHORIZATION,
                "${Constants.HEADER_BEARER}${sharedPreferenceUtil.getString(
                    Constants.KEY_ACCESS_TOKEN,
                    Constants.FAKE_TOKEN
                )}"
            )
            chain.proceed(requestBuilder.build())
        }
    }

    @Provides
    @SoundClownScope
    @SpotifyAuthenticatorHeaderInterceptor
    fun spotifyAuthenticatorHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val requestBuilder = originalRequest.newBuilder()
            val header =
                Base64.encodeToString(
                    "${Constants.ApiKeys.CLIENT_ID}:${Constants.ApiKeys.CLIENT_SECRET}".toByteArray(),
                    Base64.NO_WRAP
                )
            requestBuilder.header(
                Constants.HEADER_AUTHORIZATION,
                "${Constants.HEADER_BASIC}$header"
            )
            chain.proceed(requestBuilder.build())
        }
    }

    @Provides
    @SoundClownScope
    @StaleIfErrorInterceptor
    fun staleIfErrorInterceptor(cacheControl: CacheControl): Interceptor {
        return Interceptor { chain ->
            var response: Response? = null
            val request = chain.request()
            try {
                response?.close()
                response = chain.proceed(request)
                if (response.isSuccessful) response
            } catch (e: Exception) {

            }

            if (response == null || !response.isSuccessful) {
                val newRequest = request.newBuilder().cacheControl(cacheControl).build();
                try {
                    response?.close()
                    response = chain.proceed(newRequest)
                } catch (e: Exception) {
                    throw e
                }
            }
            response
        }
    }

    @Provides
    @SoundClownScope
    fun cacheControl(): CacheControl {
        return CacheControl.Builder()
            .maxStale(MAX_STALE, TimeUnit.MINUTES)
            .maxAge(MAX_AGE, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @SoundClownScope
    @CacheInterceptor
    fun cacheInterceptor(cacheControl: CacheControl): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            request = request.newBuilder()
                .header(CACHE_CONTROL, cacheControl.toString())
                .build()
            var response = chain.proceed(request)
            response = response.newBuilder()
                .removeHeader(PRAGMA)
                .removeHeader(CACHE_CONTROL)
                .header(CACHE_CONTROL, cacheControl.toString())
                .build();
            return@Interceptor response
        }
    }

    @Provides
    @SoundClownScope
    @SpotifyAuthenticatorOkHttpClient
    fun authenticatorOkHttpClient(
        @SpotifyAuthenticatorHeaderInterceptor spotifyAuthenticatorHeaderInterceptor: Interceptor,
        @NetworkInterceptor networkInterceptor: Interceptor,
        @LoggingInterceptor loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(spotifyAuthenticatorHeaderInterceptor)
            .addInterceptor(networkInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @SoundClownScope
    fun authenticator(
        sharedPreferenceUtil: SharedPreferenceUtil,
        spotifyAuthenticationRepository: SpotifyAuthenticationRepository
    ): TokenAuthenticator {
        return TokenAuthenticator(sharedPreferenceUtil, spotifyAuthenticationRepository)
    }

}