package com.sasuke.soundclown.data.model.playlist_tracks

import com.google.gson.annotations.SerializedName

data class ItemPlaylistTrack(
    @SerializedName("added_at")
    val addedAt: String,
    @SerializedName("added_by")
    val addedBy: AddedBy,
    @SerializedName("is_local")
    val isLocal: Boolean,
    @SerializedName("primary_color")
    val primaryColor: String,
    @SerializedName("track")
    val track: Track,
    @SerializedName("video_thumbnail")
    val videoThumbnail: VideoThumbnail
)