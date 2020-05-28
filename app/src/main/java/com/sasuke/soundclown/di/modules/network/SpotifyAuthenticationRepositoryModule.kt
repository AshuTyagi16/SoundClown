package com.sasuke.soundclown.di.modules.network

import com.sasuke.soundclown.data.network.SpotifyAuthenticationRepository
import com.sasuke.soundclown.data.network.SpotifyAuthenticationService
import com.sasuke.soundclown.data.network.SpotifyRepository
import com.sasuke.soundclown.data.network.SpotifyService
import com.sasuke.soundclown.di.scopes.SoundClownScope
import dagger.Module
import dagger.Provides

@Module(includes = [SpotifyServiceModule::class])
class SpotifyAuthenticationRepositoryModule {

    @Provides
    @SoundClownScope
    fun spotifyAuthenticationRepository(spotifyAuthenticationService: SpotifyAuthenticationService): SpotifyAuthenticationRepository {
        return SpotifyAuthenticationRepository(spotifyAuthenticationService)
    }
}