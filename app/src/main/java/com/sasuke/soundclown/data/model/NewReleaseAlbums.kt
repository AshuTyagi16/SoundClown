package com.sasuke.soundclown.data.model

import com.google.gson.annotations.SerializedName

data class NewReleaseAlbums(
    @SerializedName("href")
    val href: String,
    @SerializedName("item")
    val itemAlbumsList: ArrayList<ItemAlbum>,
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