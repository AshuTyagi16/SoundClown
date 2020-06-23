package com.sasuke.soundclown.data.model.playlist_tracks

import com.google.gson.annotations.SerializedName
import com.sasuke.soundclown.data.model.commons.ExternalUrls

data class AddedBy(
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,
    @SerializedName("href")
    val href: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("uri")
    val uri: String
)