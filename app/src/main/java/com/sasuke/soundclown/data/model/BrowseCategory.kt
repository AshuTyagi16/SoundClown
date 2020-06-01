package com.sasuke.soundclown.data.model

import com.google.gson.annotations.SerializedName

data class BrowseCategory(
    @SerializedName("categories")
    val categories: Categories
)