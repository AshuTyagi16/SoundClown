package com.sasuke.soundclown.data.model

import com.google.gson.annotations.SerializedName
import com.sasuke.soundclown.data.model.commons.Artist
import com.sasuke.soundclown.data.model.commons.ExternalUrls
import com.sasuke.soundclown.data.model.commons.Image

data class ItemAlbum(
    @SerializedName("album_type")
    val albumType: String,
    @SerializedName("artists")
    val artists: ArrayList<Artist>,
    @SerializedName("available_markets")
    val availableMarkets: ArrayList<String>,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,
    @SerializedName("href")
    val href: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val imageList: ArrayList<Image>,
    @SerializedName("name")
    val name: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("release_date_precision")
    val releaseDatePrecision: String,
    @SerializedName("total_tracks")
    val totalTracks: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("uri")
    val uri: String
)