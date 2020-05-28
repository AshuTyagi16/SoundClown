package com.sasuke.soundclown.di.modules.network

import com.sasuke.soundclown.data.network.SpotifyRepository
import com.sasuke.soundclown.data.network.SpotifyService
import com.sasuke.soundclown.di.scopes.SoundClownScope
import dagger.Module
import dagger.Provides

@Module(includes = [SpotifyServiceModule::class])
class SpotifyRepositoryModule {

    @Provides
    @SoundClownScope
    fun spotifyRepository(spotifyService: SpotifyService): SpotifyRepository {
        return SpotifyRepository(spotifyService)
    }
}