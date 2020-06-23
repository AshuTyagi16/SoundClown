package com.sasuke.soundclown.data.model.commons

import com.google.gson.annotations.SerializedName

data class ExternalUrls(
    @SerializedName("spotify")
    val spotify: String
)