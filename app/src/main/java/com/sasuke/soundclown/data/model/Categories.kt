package com.sasuke.soundclown.data.model

import com.google.gson.annotations.SerializedName

data class Categories(
    @SerializedName("href")
    val href: String,
    @SerializedName("items")
    val itemCategories: ArrayList<ItemCategories>,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("offset")
    val offSet: Int,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("total")
    val total: Int
)