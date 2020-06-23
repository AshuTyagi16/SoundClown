package com.sasuke.soundclown.data.model

import com.google.gson.annotations.SerializedName

data class NewReleases(
    @SerializedName("albums")
    val newReleaseAlbums: NewReleaseAlbums
)