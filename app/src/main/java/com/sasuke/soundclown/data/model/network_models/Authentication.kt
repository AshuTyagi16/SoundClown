package com.sasuke.soundclown.data.model.network_models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.sasuke.soundclown.util.Constants

data class Authentication(
    @SerializedName("access_token")
    @Expose
    val access_token: String = Constants.FAKE_TOKEN,
    @SerializedName("expires_in")
    @Expose
    val expires_in: Int,
    @SerializedName("scope")
    @Expose
    val scope: String,
    @SerializedName("token_type")
    @Expose
    val token_type: String
)