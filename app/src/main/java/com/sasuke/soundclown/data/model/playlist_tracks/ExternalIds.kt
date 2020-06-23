package com.sasuke.soundclown.data.model.playlist_tracks

import com.google.gson.annotations.SerializedName

data class ExternalIds(
    @SerializedName("isrc")
    val isrc: String
)