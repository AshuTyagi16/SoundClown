package com.sasuke.soundclown.data.network

import com.sasuke.soundclown.data.model.Track
import retrofit2.Call
import retrofit2.http.GET

interface SpotifyService {

    @GET("v1/tracks/2TpxZ7JUBn3uw46aR7qd6V")
    fun getTracks(): Call<Track>
}