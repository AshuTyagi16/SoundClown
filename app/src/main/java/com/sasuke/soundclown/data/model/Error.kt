package com.sasuke.soundclown.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("message")
    @Expose
    val message: String = "Some Error Occurred",

    @SerializedName("status")
    @Expose
    val status: Int = 501
)