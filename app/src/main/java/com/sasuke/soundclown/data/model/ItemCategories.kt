package com.sasuke.soundclown.data.model

import com.google.gson.annotations.SerializedName

data class ItemCategories(
    @SerializedName("href")
    val href: String,
    @SerializedName("icons")
    val iconList: ArrayList<Icon>,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)