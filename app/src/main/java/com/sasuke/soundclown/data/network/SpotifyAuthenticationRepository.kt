package com.sasuke.soundclown.data.network

import com.sasuke.soundclown.data.model.Authentication

class SpotifyAuthenticationRepository(private val spotifyAuthenticationService: SpotifyAuthenticationService) {

    fun authenticate(): Authentication? {
        return spotifyAuthenticationService.authenticate().execute().body()
    }
}