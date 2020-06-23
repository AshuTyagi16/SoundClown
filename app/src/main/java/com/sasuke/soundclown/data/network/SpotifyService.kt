package com.sasuke.soundclown.data.network

import com.sasuke.soundclown.data.model.*
import com.sasuke.soundclown.data.model.playlist_tracks.PlaylistTracksResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SpotifyService {

//    @GET("v1/tracks/2TpxZ7JUBn3uw46aR7qd6V")
//    fun getTracks(): Call<Track>

    @GET("v1/browse/categories")
    fun getBrowseCategories(): Call<BrowseCategory>

    @GET("v1/browse/categories/{category_id}/playlists")
    fun getPlaylistsForCategory(@Path("category_id") category: String): Call<PlaylistResponse>

    @GET("v1/browse/categories/{category_id}")
    fun getCategoryById(@Path("category_id") categoryId: String): Call<ItemCategories>

    @GET("v1/browse/featured-playlists")
    fun getFeaturedPlaylist(): Call<FeaturedPlaylist>

    @GET("v1/browse/new-releases")
    fun getNewReleases(): Call<NewReleases>

    @GET("v1/playlists/{playlist_id}/tracks")
    fun getPlaylistTracksById(@Path("playlist_id") playlistId: String): Call<PlaylistTracksResponse>
}