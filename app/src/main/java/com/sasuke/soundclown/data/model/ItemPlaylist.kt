package com.sasuke.soundclown.data.model

import com.google.gson.annotations.SerializedName

data class ItemPlaylist(
    @SerializedName("collaborative")
    val collaborative: Boolean,
    @SerializedName("description")
    val description: String,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,
    @SerializedName("href")
    val href: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val imageList: ArrayList<Icon>,
    @SerializedName("name")
    val name: String,
    @SerializedName("owner")
    val owner: Owner,
    @SerializedName("primary_color")
    val primaryColor: Any,
    @SerializedName("public")
    val public: String,
    @SerializedName("snapshot_id")
    val snapshot_id: String,
    @SerializedName("tracks")
    val tracks: Tracks,
    @SerializedName("type")
    val type: String,
    @SerializedName("uri")
    val uri: String
)