package com.sasuke.soundclown.data.network

import com.sasuke.soundclown.data.model.Category
import com.sasuke.soundclown.data.model.Playlist
import com.sasuke.soundclown.data.model.Track
import retrofit2.Call
import retrofit2.http.GET

interface SpotifyService {

    @GET("v1/tracks/2TpxZ7JUBn3uw46aR7qd6V")
    fun getTracks(): Call<Track>

    @GET("v1/browse/categories/edm_dance/playlists")
    fun getPlaylistsForCategory(): Call<Playlist>

    @GET("v1/browse/categories")
    fun getAllCategories(): Call<Category>
}