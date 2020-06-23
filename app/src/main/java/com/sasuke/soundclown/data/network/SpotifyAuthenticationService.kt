package com.sasuke.soundclown.data.network

import com.sasuke.soundclown.data.model.network_models.Authentication
import retrofit2.Call
import retrofit2.http.*

interface SpotifyAuthenticationService {

    @FormUrlEncoded
    @POST("api/token")
    fun authenticate(
        @Field("grant_type") grant_type: String = "client_credentials"
    ): Call<Authentication>
}