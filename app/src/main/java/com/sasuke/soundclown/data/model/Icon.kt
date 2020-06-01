package com.sasuke.soundclown.data.model

import com.google.gson.annotations.SerializedName

data class Icon(
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)