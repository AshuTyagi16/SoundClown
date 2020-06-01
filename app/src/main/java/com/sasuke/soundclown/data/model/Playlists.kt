package com.sasuke.soundclown.data.model

import com.google.gson.annotations.SerializedName

data class Playlists(
    @SerializedName("href")
    val href: String,
    @SerializedName("items")
    val playlistItemList: ArrayList<ItemPlaylist>,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("next")
    val next: Any,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("total")
    val total: Int
)