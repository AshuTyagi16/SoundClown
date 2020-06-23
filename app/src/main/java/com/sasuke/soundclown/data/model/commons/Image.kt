package com.sasuke.soundclown.data.model.commons

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("height")
    val height: Int,
    @SerializedName("href")
    val url: String,
    @SerializedName("width")
    val width: Int
)