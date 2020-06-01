package com.sasuke.soundclown.data.model

import com.google.gson.annotations.SerializedName

data class FeaturedPlaylist(
    @SerializedName("message")
    val message: String,
    @SerializedName("playlists")
    val playlists: Playlists
)