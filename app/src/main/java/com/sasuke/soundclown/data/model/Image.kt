package com.sasuke.soundclown.data.model

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("height")
    val height: Float,
    @SerializedName("href")
    val url: String,
    @SerializedName("width")
    val width: Float
)