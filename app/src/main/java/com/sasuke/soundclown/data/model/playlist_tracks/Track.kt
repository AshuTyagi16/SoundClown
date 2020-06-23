package com.sasuke.soundclown.data.model.playlist_tracks

import com.google.gson.annotations.SerializedName
import com.sasuke.soundclown.data.model.commons.Artist
import com.sasuke.soundclown.data.model.commons.ExternalUrls

data class Track(
    @SerializedName("album")
    val playlistTrackAlbum: PlaylistTrackAlbum,
    @SerializedName("artists")
    val artists: ArrayList<Artist>,
    @SerializedName("available_markets")
    val availableMarkets: ArrayList<String>,
    @SerializedName("disc_number")
    val discNumber: Int,
    @SerializedName("duration_ms")
    val duration_ms: Int,
    @SerializedName("episode")
    val episode: Boolean,
    @SerializedName("explicit")
    val explicit: Boolean,
    @SerializedName("external_ids")
    val externalIds: ExternalIds,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,
    @SerializedName("href")
    val href: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("is_local")
    val is_local: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("popularity")
    val popularity: Int,
    @SerializedName("preview_url")
    val preview_url: String,
    @SerializedName("track")
    val track: Boolean,
    @SerializedName("track_number")
    val track_number: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("uri")
    val uri: String
)