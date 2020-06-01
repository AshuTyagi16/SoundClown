package com.sasuke.soundclown.data.model

import com.google.gson.annotations.SerializedName

data class PlaylistResponse(
    @SerializedName("playlists")
    val playlists: Playlists
)