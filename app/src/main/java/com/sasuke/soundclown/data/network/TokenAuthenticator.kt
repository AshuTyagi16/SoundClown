package com.sasuke.soundclown.data.network

import com.sasuke.soundclown.util.Constants
import com.sasuke.soundclown.util.SharedPreferenceUtil
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(
    private val sharedPreferenceUtil: SharedPreferenceUtil,
    private val spotifyAuthenticationRepository: SpotifyAuthenticationRepository
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val data = spotifyAuthenticationRepository.authenticate() ?: return null
        sharedPreferenceUtil.putStringSync(Constants.KEY_ACCESS_TOKEN, data.access_token)

        return response.request.newBuilder()
            .header(Constants.HEADER_AUTHORIZATION, data.access_token)
            .build()
    }
}