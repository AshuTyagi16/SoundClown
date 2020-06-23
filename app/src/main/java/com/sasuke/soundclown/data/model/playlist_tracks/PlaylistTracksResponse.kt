package com.sasuke.soundclown.data.model.playlist_tracks

import com.google.gson.annotations.SerializedName

data class PlaylistTracksResponse(
    @SerializedName("href")
    val href: String,
    @SerializedName("items")
    val itemPlaylistTrack: ArrayList<ItemPlaylistTrack>,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("next")
    val next: Any,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("previous")
    val previous: Int,
    @SerializedName("total")
    val total: Int
)